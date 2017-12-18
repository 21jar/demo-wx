package com.demo.service.wx;

import java.util.List;

import com.demo.core.weixin.wxobj.result.MassSendResult;

/**
 * 微信用户
 *
 * @author hst on 2017/03/06
 **/

public interface WxFansService {


    /**
     * 拉取公众号用户的openid列表
     */
    String pullOpenIdList();

    /**
     * 补充公众号用户信息
     *
     * @param users 要补充信息的用户列表
     * @param outBegin 每次循环起始的位置 用于打印log
     * @param total 总的要拉取的用户数 用于打印log
     * @param isFromWx 是否从微信端拉取openId
     *
     * @return 返回拉取错误的个数
     */
    int supplyFansInfo(List<String> users, Integer outBegin, Integer total, boolean isFromWx);

    /**
     * 获取未补充信息粉丝总数
     */
    int getInCompleteFansSize();

    /**
     * 获取还未拉去用户信息列表
     *
     * @param begin 起始用户数
     * @param pageSize 拉去个数
     */
    List<String> queryIncompleteInfoOpenUserList(Integer begin, Integer pageSize);

    /**
     * 删除已经拉去的用户openid
     *
     * @return 删除条数
     */
    int deletePullDownOpenId();

    /**
     * 根据openId发送图文列表
     *
     * @param totalSend 发送总数
     * @param userOffset 用户发送起始排序位置
     * @param perSendSize 每次群发的用户数
     * @param mediaId 发送的图文
     * @param userSource 用户数据来源
     */
    List<MassSendResult> mpNewsMassSend(int totalSend, int userOffset, int perSendSize, String mediaId, String userSource);

    /**
     * 根据用户openid获取详情更新数据
     * @param openIds 用户openid的集合
     * @return
     */
	String updateByOpenIds(List<String> openIds);
}
