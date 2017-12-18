package com.demo.core.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.demo.core.annotation.MyCacheEvict;
import com.demo.core.annotation.TargetDatabase;
import com.demo.core.constant.TableNameConstant;
import com.demo.core.utils.RedisUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 胡超云 on 2016/12/15.
 */
@Slf4j
@Aspect
@Order(3)
@Component
public class MyCacheEvictAop {

    @Autowired
    RedisUtil redisUtil;

    @AfterReturning("@annotation(com.demo.core.annotation.MyCacheEvict)")
    public void delKeys(JoinPoint point) throws Exception {
        //根据连接点所属的类实例，构造批量删除缓存key
        String[] keys = getControllerMethodDescription(point);
        if (keys.length > 0) {
            Arrays.asList(keys).forEach(key -> {
               // log.info("批量删除等key：{}", key);
                redisUtil.removePattern(key);
            });
        }
    }

    public static String[] getControllerMethodDescription(JoinPoint joinPoint) throws Exception {

        Signature signature = joinPoint.getSignature();

        //全路径类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //方法名
        String methodName = signature.getName();
        //实参
        Object[] arguments = joinPoint.getArgs();

        Class targetClass = Class.forName(targetName);

        //所有方法
        Method[] methods = targetClass.getMethods();

        MethodSignature methodSignature = (MethodSignature) signature;

        //获取形参
        String[] argumentNames = methodSignature.getParameterNames();

        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(MyCacheEvict.class).key();
                    break;
                }
            }
        }

        //构造key
        String[] _keyArray = null;
        if (StringUtils.hasText(description)) {

            String[] keyArray = description.split("\\|");
            _keyArray = new String[keyArray.length];

            for (int n = 0; n < keyArray.length; n++) {

                String pattern = "\\(([^)]+)\\)|[^\\.]*";
                Pattern reg = Pattern.compile(pattern);
                Matcher matcher = reg.matcher(keyArray[n]);

                List<String> _keys = new ArrayList<>();

                while (matcher.find()) {
                    if (matcher.group() != null && StringUtils.hasText(matcher.group()))
                        _keys.add(matcher.group());
                }

                String removeKey = _keys.get(0);

                for (int m = 1; m < _keys.size(); m++) {
                    if (null != _keys.get(m) && _keys.get(m).contains("(")) {
                        String key = _keys.get(m).substring(_keys.get(m).indexOf("(") + 1, _keys.get(m).indexOf(")"));
                        String[] keyInfo = key.replace("#", "").split("\\.");
                        for (int k = 0; k < argumentNames.length; k++) {
                            if (keyInfo[0].equals(argumentNames[k])) {
                                String keyVal = arguments[k].toString();
                                String[] keyVals = keyVal.substring(keyVal.indexOf("(") + 1, keyVal.indexOf(")")).split(",");
                                for (int p = 1; p < keyInfo.length; p++) {
                                    for (int l = 0; l < keyVals.length; l++) {
                                        String ks = keyVals[l].substring(0, keyVals[l].indexOf("="));
                                        if (ks.trim().equals(keyInfo[p])) {
                                            removeKey = removeKey + "." + keyVals[l].substring(keyVals[l].indexOf("=") + 1, keyVals[l].length());
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                    } else if (null != _keys.get(m) && _keys.get(m).contains("#")) {
                        String keyWord = _keys.get(m).replace("#", "");
                        for (int k = 0; k < argumentNames.length; k++) {
                            if (keyWord.equals(argumentNames[k])) {
                                removeKey = removeKey + "." + arguments[k].toString();
                            }
                        }
                    }
                }
                removeKey = removeKey.replaceFirst(_keys.get(0), TableNameConstant.table.get(_keys.get(0))) + "." + _keys.get(_keys.size() - 1);
                _keyArray[n] = removeKey;
            }
        }
        return _keyArray;
    }
}

