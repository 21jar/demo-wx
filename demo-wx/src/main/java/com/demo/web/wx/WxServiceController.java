package com.demo.web.wx;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.utils.RedisUtil;
import com.demo.core.weixin.aes.AesException;
import com.demo.core.weixin.aes.WXBizMsgCrypt;
import com.demo.core.weixin.constant.WxEventType;
import com.demo.core.weixin.constant.WxMsgType;
import com.demo.core.weixin.msg.TransferCustomerServiceMsg;
import com.demo.core.weixin.pojo.WxMsgValidParams;
import com.demo.env.WxProperties;
import com.demo.service.wx.WxApiService;
import com.demo.service.wx.WxEventDealService;
import com.demo.service.wx.WxKeywordDealService;

/**
 * @author by hst on 2016/11/30.
 */
@Slf4j
@RestController
public class WxServiceController {

    @Autowired
    private WxProperties wxProperties;
    @Autowired
    private WxEventDealService wxEventDealService;
    @Autowired
    private WxKeywordDealService wxKeywordDealService;
    @Autowired
    private WxApiService wxApiService;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 验证接口配置信息
     * 加密/校验流程如下：
     * 将token、timestamp、nonce三个参数进行字典序排序
     * 将三个参数字符串拼接成一个字符串进行sha1加密
     * 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @GetMapping("wxReceive.do")
    public String signature(String signature, String timestamp, String nonce, String echostr) throws IOException {
        if (wxApiService.signature(signature, timestamp, nonce) && echostr != null) {
            return echostr;
        }
        return "failed";
    }

    /**
     * 处理微信消息的回调
     *
     * @param validParams 微信验证参数
     * @param xml post消息内容
     *
     * @return 消息处理结果
     *
     * @throws IOException xml读写异常
     * @throws AesException 解密异常
     * @throws DocumentException xml转换异常
     */
    @PostMapping("wxReceive.do")
    public String receiveMessage(WxMsgValidParams validParams, @RequestBody(required = false) String xml) throws IOException, AesException, DocumentException {
    	log.info(validParams.toString());
 
    	log.info(xml);
    	if (!wxApiService.signature(validParams)) {
            return "failed";
        } else {
            String replyMsg = "success";
            //判断该消息是否为加密消息
            if (validParams.isEncrypt()) {
                xml = (new WXBizMsgCrypt(wxProperties.getToken(), wxProperties.getEncodingAesKey(), wxProperties.getAppId()))
                        .DecryptMsg(validParams.getMsg_signature(), validParams.getTimestamp(), validParams.getNonce(), xml);
            }
            log.info("\n收到微信消息:\n" + xml);

            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();

            //消息发送者/接收者
            String fromUserName = root.elementTextTrim("FromUserName");
            // 公众帐号
            String toUserName = root.elementTextTrim("ToUserName");
            //Create Time
            String createTime = root.elementTextTrim("CreateTime");
            // 消息类型
            String msgType = root.elementTextTrim("MsgType");
            String msgId = root.elementTextTrim("MsgID");

            //消息key,判断是否在15秒内收到过该消息,收到过直接返回
            String msgKey = Objects.isNull(msgId) ? (fromUserName + "_" + createTime) : msgId;
            if (Objects.nonNull(redisUtil.get(msgKey))) {
                return replyMsg;
            }
            //设置20秒缓存
            redisUtil.set(msgKey, createTime, 20);

            switch (WxMsgType.getByName(msgType)) {
                //处理事件消息
                case EVENT:
                    String eventType = root.elementTextTrim("Event");
                    String eventKey = root.elementTextTrim("EventKey");
                    switch (WxEventType.getByName(eventType)) {
                        case TEMPLATE_SEND_JOB_FINISH:
                            //处理模版消息推送事件回调
                            break;
                            
                        case SCAN:
                        	//处理扫码事件
                            wxEventDealService.recordQrCodeScan(fromUserName, eventKey, Long.valueOf(createTime + "000"));
                            break;
                       
                        case SUBSCRIBE:
                        	//用户关注事件
                            eventKey = eventKey.replace("qrscene_", "");
                            wxEventDealService.saveSubscribeUser(fromUserName, eventKey, Long.valueOf(createTime + "000"));
                            break;
                            
                        case UNSUBSCRIBE:
                        	//用户取消关注事件
                            wxEventDealService.updateUnSubscribeUser(fromUserName);
                            break;
                    }
                    break; 
                case TEXT:
                	 //处理文本消息
                    replyMsg = wxKeywordDealService.genReplyByKeyWord(fromUserName, toUserName, root.elementTextTrim("Content"));
                    break;
                default:
                	//默认处理其余消息
                	replyMsg = new TransferCustomerServiceMsg(toUserName,fromUserName).toXml();
                    break;
            }
            if (validParams.equals("success")){
            	replyMsg = wxKeywordDealService.genReplyByKeyWord(fromUserName, toUserName, "subscript");
            }
            if (validParams.isEncrypt()) {
                replyMsg = new WXBizMsgCrypt(wxProperties.getToken(), wxProperties.getEncodingAesKey(), wxProperties.getAppId())
                        .EncryptMsg(replyMsg, validParams.getTimestamp(), validParams.getNonce());
            }
            return replyMsg;
        }
    }

    @ExceptionHandler(value = {IOException.class, AesException.class, DocumentException.class})
    public String dealMsgException(Exception ex, HttpServletRequest request) {
        log.error("公众号接收消息错误：" + ex.getMessage());
        try {
            log.error("收到的消息：" + StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("打印接收到的信息时，转换接收消息失败！！！！");
        }
        return "failed cause by: " + ex.getMessage();
    }
}