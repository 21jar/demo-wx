package com.demo.env;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.demo.core.utils.RedisUtil;
import com.demo.domain.wx.HdAutoReplyRule;
import com.demo.service.wx.HdAutoReplyRuleService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 微信关键词自动回复规则
 *
 * @author hst on 2016/12/13
 */
@Slf4j
@Component
public class AutoReplyRule implements InitializingBean {

    private static final String AUTO_KEY = "com.demo.env.AutoReplyRule";

    @Autowired
    private HdAutoReplyRuleService hdAutoReplyRuleService;
    @Autowired
    private RedisUtil redisUtil;

    @SuppressWarnings("unchecked")
    public HdAutoReplyRule getKeyWordRule(String keyWord) {
        if (StringUtils.isEmpty(keyWord)) {
            return null;
        }

        Object object = redisUtil.get(AUTO_KEY);
        if (Objects.isNull(object)) {
            initReplyRules();
            object = redisUtil.get(AUTO_KEY);
        }

        return ((Map<String, HdAutoReplyRule>) object).get(keyWord);
    }

    @SuppressWarnings("unchecked")
    public Map<String, HdAutoReplyRule> getAllRule() {
        return ((Map<String, HdAutoReplyRule>) redisUtil.get(AUTO_KEY));
    }

    public void refreshKeywordRules() {
        initReplyRules();
        log.info("refresh keyword {}", LocalDateTime.now());
    }

    private void initReplyRules() {
        List<HdAutoReplyRule> rules = hdAutoReplyRuleService.getAllAutoReplyRules();
        redisUtil.set(AUTO_KEY, rules.stream().collect(Collectors.toMap(HdAutoReplyRule::getReplyWord, hdAutoReplyRule -> hdAutoReplyRule)), Integer.MAX_VALUE);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initReplyRules();
    }
}
