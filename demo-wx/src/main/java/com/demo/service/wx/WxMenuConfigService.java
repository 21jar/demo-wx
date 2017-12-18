package com.demo.service.wx;

import java.util.List;

import com.demo.core.weixin.wxobj.result.BaseResult;

/**
 * @author hst on 2016/12/13
 */
public interface WxMenuConfigService {

    /**
     * 更新服务号菜单
     *
     * @return 更新结果
     */
    List<BaseResult> updateWxMenu();
}
