package com.demo.mapper.wx;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demo.core.weixin.wxobj.OpenUser;
import com.demo.domain.wx.HdFans;

import java.util.List;

@Mapper
public interface HdFansMapper {

    int deleteByPrimaryKey(String openId);

    int insert(HdFans record);

    HdFans selectByPrimaryKey(String openId);

    List<HdFans> selectAll();

    int updateByPrimaryKey(HdFans record);

    int updateUnSubscribeUser(HdFans fans);

    /**
     * 更新用户的带参二维码值
     */
    void updateUserActionInfo(String openId, String actionInfo);

    /*
    * 保存用户的openId
    * */
    int saveOpenIdList(@Param("openIds") List<String> openIds);
    

    /**
     * 保存取消关注用户的信息
     * @param openIds
     * @return
     * @author wangjingze
     * @date 2017年9月6日
     * @see
     */
    int saveUnsubscribeOpenUser(OpenUser openUser);

    /**
     * 新增或修改用户的信息
     * @param openIds
     * @return
     * @author wangjingze
     * @date 2017年9月6日
     * @see
     */
    int saveOrUpdateOpenUser(OpenUser openUser);
    
    /*
    * 批量保存用户的信息
    * */
    int saveOpenUserList(@Param("openUsers") List<OpenUser> openUsers);

    /*
    * 保存获取失败的用户的id
    * */
    int saveErrorOpenIdList(@Param("openIds") List<String> openIds);

    /*
    * 保存获取失败的用户的id
    * */
    int saveErrorOpenId(@Param("openId") String openId);

    /*
    * 获取未补充信息粉丝总数
    * */
    int getInCompleteFansSize();

    /**
     * 获取未补充信息粉丝openid列表
     */
    List<String> queryIncompleteInfoOpenUserList();

    /**
     * 删除已经拉取的用户openid
     */
    int deletePullDownOpenId();

    /**
     * 查询群发用户
     */
    List<String> queryMassSendUser();

    /**
     * 查询群发用户openid
     */
    List<String> queryMassSendOpenId();
    
    /**
     * 删除更新完成的openid
     * @param openId
     */
	void deleteByOpenId(String openId);


}