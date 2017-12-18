package com.demo.core.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 胡超云 on 2016/12/16.
 * 缓存构建
 * 存放表名
 */
public class TableNameConstant {

    public final static String T1 = "hd.answer";
    public final static String T2 = "hd.answer.template";
    public final static String T3 = "hd.auto.reply.rule";
    public final static String T4 = "hd.banner";
    public final static String T5 = "hd.car.info";
    public final static String T6 = "hd.car.type";
    public final static String T7 = "hd.fans";
    public final static String T8 = "hd.maintain.meet";
    public final static String T9 = "hd.maintain.record";
    public final static String T10 = "hd.member.activities";
    public final static String T11 = "hd.member.news";
    public final static String T12 = "hd.member.user";
    public final static String T13 = "hd.menu.config";
    public final static String T14 = "hd.need.send.msg";
    public final static String T15 = "hd.news";
    public final static String T16 = "hd.user";
    public final static String T17 = "hd.car.part";
    public final static String T18 = "hd.send.msg.record";
    public final static String T19 = "hd.send.msg.rule";
    public final static String T20 = "hd.service.question";
    public final static String T21 = "hd.service.template";
    public final static String T22 = "hd.store.admin";
    public final static String T23 = "hd.stores.info";
    public final static String T24 = "hd.stores.work";
    public final static String T25 = "hd.stores.work.tmpl";
    public final static String T26 = "hd.lottery.result";
    public final static String T27 = "hd.lottery.prizes";
    public final static String T28 = "hd.lottery.config";
    public final static String T29 = "hd.lottery.consume";
    public final static String T30 = "hd.stores.cash.coupon";
    public final static String T31 = "hd.insurance.user";
    public final static String T32 = "hd.car.code";
    public final static String T33 = "hd.car.bg";
    public final static String T34 = "hd.car.card";
    public final static String T35 = "hd.stores.insurance.admin";
    public final static String T36 = "hd.insurance.car";
    public final static String T37 = "hd.insurance.consult";
    public final static String T38 = "hd.insurance.package";
    public final static String T39 = "hd.insurance.prizes";


    public final static Map<String, String> table = new HashMap<>();

    static {
        table.put("t1", T1);
        table.put("t2", T2);
        table.put("t3", T3);
        table.put("t4", T4);
        table.put("t5", T5);
        table.put("t6", T6);
        table.put("t7", T7);
        table.put("t8", T8);
        table.put("t9", T9);
        table.put("t10", T10);
        table.put("t11", T11);
        table.put("t12", T12);
        table.put("t13", T13);
        table.put("t14", T14);
        table.put("t15", T15);
        table.put("t16", T16);
        table.put("t17", T17);
        table.put("t18", T18);
        table.put("t19", T19);
        table.put("t20", T20);
        table.put("t21", T21);
        table.put("t22", T22);
        table.put("t23", T23);
        table.put("t24", T24);
        table.put("t25", T25);
        table.put("t26", T26);
        table.put("t27", T27);
        table.put("t28", T28);
        table.put("t29", T29);
        table.put("t30", T30);
        table.put("t31", T31);
        table.put("t32", T32);
        table.put("t33", T33);
        table.put("t34", T34);
        table.put("t35", T35);
        table.put("t36", T36);
        table.put("t37", T37);
        table.put("t38", T38);
        table.put("t39", T39);
    }
}
