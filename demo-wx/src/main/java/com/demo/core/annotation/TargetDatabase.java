package com.demo.core.annotation;

import java.lang.annotation.*;

/**
 * Created by 胡超云 on 2016/11/26.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDatabase {
    String name() default TargetDatabase.master;

    String master = "masterDatabase";
    String slave = "slaveDatabase";
}
