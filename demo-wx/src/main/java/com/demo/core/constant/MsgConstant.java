package com.demo.core.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 胡超云 on 2016/11/30.
 * 返回信息封装
 */
public class MsgConstant {

    private final static Map<String, String> msg = new HashMap<>();

    public final static String MSG_000000 = "000000";
    public final static String MSG_000001 = "000001";
    public final static String MSG_000002 = "000002";
    public final static String MSG_000003 = "000003";
    public final static String MSG_000004 = "000004";
    public final static String MSG_000005 = "000005";
    public final static String MSG_000006 = "000006";
    public final static String MSG_000007 = "000007";
    public final static String MSG_000008 = "000008";
    public final static String MSG_000009 = "000009";
    public final static String MSG_000010 = "000010";
    public final static String MSG_000011 = "000011";
    public final static String MSG_000012 = "000012";
    public final static String MSG_000013 = "000013";
    public final static String MSG_000014 = "000014";
    public final static String MSG_000015 = "000015";
    public final static String MSG_000016 = "000016";
    public final static String MSG_000017 = "000017";
    public final static String MSG_000018 = "000018";
    public final static String MSG_000019 = "000019";
    public final static String MSG_000020 = "000020";
    public final static String MSG_000021 = "000021";
    public final static String MSG_000022 = "000022";
    public final static String MSG_000023 = "000023";
    public final static String MSG_000024 = "000024";
    public final static String MSG_000025 = "000025";
    public final static String MSG_000026 = "000026";
    public final static String MSG_000027 = "000027";
    public final static String MSG_000028 = "000028";
    public final static String MSG_000029 = "000029";


    //微信接口消息，以01开头，避免混乱
    public final static String MSG_010001 = "010001";
    public final static String MSG_010002 = "010002";
    public final static String MSG_010003 = "010003";

    //代金券消息，以02开头
    public final static String MSG_020001 = "020001";
    public final static String MSG_020002 = "020002";
    public final static String MSG_020003 = "020003";
    public final static String MSG_020004 = "020004";
    public final static String MSG_020005 = "020005";
    public final static String MSG_020006 = "020006";
    public final static String MSG_020007 = "020007";
    public final static String MSG_020008 = "020008";
    public final static String MSG_020009 = "020009";
    public final static String MSG_020010 = "020010";
    public final static String MSG_020011 = "020011";
    public final static String MSG_020012 = "020012";
    public final static String MSG_020013 = "020013";
    public final static String MSG_020014 = "020014";
    
    //api接口消息
    public final static String MSG_030001 = "030001";
    
    static {
        //正常返回
        msg.put(MSG_000000, "ok");
        //系统异常
        msg.put(MSG_000001, "exception");
        msg.put(MSG_000002, "此车辆暂时无法绑定，请联系4008806622帮您处理！");
        msg.put(MSG_000003, "此车辆已经绑定过啦！");
        msg.put(MSG_000004, "解绑失败！");
        msg.put(MSG_000005, "暂无数据！");
        msg.put(MSG_000006, "请完善信息！");
        msg.put(MSG_000007, "编码错误！");
        msg.put(MSG_000008, "此车辆不属于</br>纳智捷！");
        msg.put(MSG_000009, "预约已满！");
        msg.put(MSG_000010, "车辆绑定达到上限！");
        msg.put(MSG_000011, "会员信息获取异常！");
        msg.put(MSG_000012, "openId获取异常！");
        msg.put(MSG_000013, "code获取异常！");
        msg.put(MSG_000014, "手机验证码错误！");
        msg.put(MSG_000015, "预约系统异常！");
        msg.put(MSG_000016, "无绑定车辆！");
        msg.put(MSG_000017, "未入会！");
        msg.put(MSG_000018, "入会！");
        msg.put(MSG_000019, "该微信账号已是责任人，或已被解除责任人身份！");
        msg.put(MSG_000020, "该车辆已是会员！");
        msg.put(MSG_000021, "会员信息修改失败！");
        msg.put(MSG_000022, "预约已满，请预约其他时段！");
        msg.put(MSG_000023, "请输入正确特约店地址！");
        msg.put(MSG_000024, "预约繁忙！");
        msg.put(MSG_000025, "已取消预约！");
        msg.put(MSG_000026, "此微信账号已列入黑名单，如有疑问请联系客服！");
        msg.put(MSG_000027, "此车架号已列入黑名单，如有疑问请联系客服！");
        msg.put(MSG_000028, "连接超时，请重新输入车架号和发动机号！");
        msg.put(MSG_000029, "此手机号已列入黑名单，如有疑问请联系客服！");

        //微信接口信息
        msg.put(MSG_010001, "二维码参数不能为空！");
        msg.put(MSG_010002, "scene格式错误（type_scene）");
        msg.put(MSG_010003, "非法的二维码类型！");

        //代金券消息
        msg.put(MSG_020001, "无效的代金券！");
        msg.put(MSG_020002, "代金券已过期!");
        msg.put(MSG_020003, "代金券已核销！");
        msg.put(MSG_020004, "无效的核销特约店！");
        msg.put(MSG_020005, "无效的核销员！");
        msg.put(MSG_020006, "核销员与特约店不匹配！");
        msg.put(MSG_020007, "核销失败！没有预约记录，请预约！");
        msg.put(MSG_020008, "核销失败！预约当天才能核销！");
        msg.put(MSG_020009, "核销失败！该券只能在预约特约店使用");
        msg.put(MSG_020010, "核销失败！有效期内才能使用");
        msg.put(MSG_020011, "代金券未激活！");
        msg.put(MSG_020012, "核销失败！该券只能在激活的特约店使用！");
        msg.put(MSG_020013, "请先在DMS发起核销");
        msg.put(MSG_020014, "DMS核销失败");
        
        //api接口消息
        msg.put(MSG_030001, "api接口调用失败");
    }

    public static String getMsg(String key) {
        return msg.get(key);
    }
}
