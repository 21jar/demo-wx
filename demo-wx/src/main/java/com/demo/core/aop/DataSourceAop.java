package com.demo.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.demo.core.annotation.MyCacheEvict;
import com.demo.core.annotation.TargetDatabase;
import com.demo.core.utils.mybatis.DatabaseContextHolder;
import com.demo.core.utils.mybatis.DatabaseType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * aop 拦截 进行切换数据源
 * 如果service层 增加了@Transactional ，导致数据源MyAbstractRoutingDataSource的determineCurrentLookupKey()方法会在执行DataSourceAop拦截之前就进行全局事务绑定
 * 从而导致获取 DataSourceHolder.getJdbcType(); 一直都是空值
 */
@Aspect
@Component
@Order(1)
public class DataSourceAop {

    @Before("@annotation(com.demo.core.annotation.TargetDatabase)")
    public void setDataSourceKey(JoinPoint point) throws Exception {
        //根据连接点所属的类实例，动态切换数据源
        String str = getControllerMethodDescription(point);
        if (TargetDatabase.master.equals(str)) {
            DatabaseContextHolder.setDatabaseType(DatabaseType.masterDatabase);
        } else if (TargetDatabase.slave.equals(str)) {
            DatabaseContextHolder.setDatabaseType(DatabaseType.slaveDatabase);
        }
    }

    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {

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
                    description = method.getAnnotation(TargetDatabase.class).name();
                    break;
                }
            }
        }

        return description;
    }
}
