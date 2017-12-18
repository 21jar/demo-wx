package com.demo.service.wx.impl;

import com.alibaba.fastjson.JSON;
import com.demo.core.annotation.TargetDatabase;
import com.demo.core.weixin.WxApi;
import com.demo.core.weixin.constant.KeyButtonType;
import com.demo.core.weixin.wxobj.AccessToken;
import com.demo.core.weixin.wxobj.ConditionMenu;
import com.demo.core.weixin.wxobj.Menu;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.demo.core.weixin.wxobj.result.CreateMenuResult;
import com.demo.domain.wx.HdMenuConfig;
import com.demo.mapper.wx.HdMenuConfigMapper;
import com.demo.service.wx.WxMenuConfigService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 服务服务号菜单配置
 *
 * @author hst on 2016/12/13
 */
@Slf4j
@Service
public class WxMenuConfigServiceImpl implements WxMenuConfigService {

    @Autowired
    private HdMenuConfigMapper hdMenuConfigMapper;
    @Autowired
    private WxApi wxApi;

    @Override
    @Transactional
    @TargetDatabase(name = TargetDatabase.master)
    public List<BaseResult> updateWxMenu() {
        String deleteResult = wxApi.deleteWxMenu();
        List<HdMenuConfig> menusConfigs = hdMenuConfigMapper.selectAll();
        List<BaseResult> results = new ArrayList<>();
        if (Objects.nonNull(menusConfigs)) {
            //菜单分组
            Map<Integer, List<HdMenuConfig>> configMap = new HashMap<>();
            for (HdMenuConfig menuConfig : menusConfigs) {
                List<HdMenuConfig> singMenuConfig = configMap.get(menuConfig.getLocalMenuId());
                if (Objects.isNull(singMenuConfig)) {
                    singMenuConfig = new ArrayList<>();
                    singMenuConfig.add(menuConfig);
                    configMap.put(menuConfig.getLocalMenuId(), singMenuConfig);
                } else {
                    singMenuConfig.add(menuConfig);
                }
            }

            //生成菜单对象,根据menu_local_id排序
            Set<Integer> keys = configMap.keySet();
            for (Integer key : keys) {
                Map<String, Menu.Button> buttonsMap = new HashMap<>();

                configMap.get(key).forEach(mc -> {
                    //一级菜单且有子菜单
                    if (mc.getButtonLevel() == 1 && mc.getHasSubButton() == 1) {
                        buttonsMap.put(mc.getButton(), new Menu.ParentButton(mc.getName(), mc.getKey(), mc.getOrder()));
                        //一级菜单没有子菜单
                    } else if (mc.getButtonLevel() == 1) {
                        buttonsMap.put(mc.getButton(), getButton(mc));
                        //二级菜单
                    } else {
                        ((Menu.ParentButton) buttonsMap.get(mc.getButton())).getSubButton().add(getButton(mc));
                    }
                });

                Menu wxMenu;
                String matchRule = configMap.get(key).get(0).getMatchRuleGroup();
                List<Menu.Button> buttons = new ArrayList<>(buttonsMap.values());
                buttons.sort(Comparator.comparingInt(Menu.Button::getOrder));

                wxMenu = Objects.isNull(matchRule) ? new Menu(buttons) : new ConditionMenu(buttons, new ConditionMenu.MatchRule(matchRule));

                log.info("generator menu： " + JSON.toJSONString(wxMenu));
                CreateMenuResult result = wxApi.createMenu(wxMenu);
                if (Objects.nonNull(result.getMenuId())) {
                    hdMenuConfigMapper.updateWxMenuId(key, result.getMenuId());
                }
                results.add(result);
            }
        }
        return results;
    }

    /*生成view类型button，或其余带key类型button*/
    private Menu.Button getButton(HdMenuConfig config) {
        if (KeyButtonType.VIEW.getName().equals(config.getType())) {
            return new Menu.ViewButton(config.getName(), config.getUrl(), config.getOrder());
        } else {
            return new Menu.KeyButton(KeyButtonType.getByName(config.getType()), config.getName(), config.getKey(), config.getOrder());
        }
    }
}