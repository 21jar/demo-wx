package com.demo.core.constant;

/**
 * Created by 胡超云 on 2016/11/17.
 * key的前缀定义
 * 所有缓存的前缀需在此定义
 * 不同用途的缓存，前缀不能一样
 */
public class KeyConstant {

    //修改手机缓存前缀key
    public final static String MODIFY_MOBILE_KEY = "modify.mobile.key.";

    //注册账号手机短信验证前缀key
    public final static String REGISTER_MOBILE_KEY = "register.mobile.key.";

    //解绑手机短信验证前缀key
    public final static String UNBIND_MOBILE_KEY = "unbind.mobile.key.";

    //注册会员短信验证前缀key
    public final static String REGISTER_MEMBER_MOBILE_KEY = "register.member.mobile.key.";

    //注册会员短信验证前缀key
    public final static String UPDATE_MEMBER_MOBILE_KEY = "update.member.mobile.key.";

    //申请管理员key
    public final static String APPLY_MANAGERS_MOBILE_KEY = "apply.managers.mobile.key.";

    //问卷调查模板key
    public final static String QUESTION_TMPL_KEY = "question_tmpl_key";

    //车辆基本信息（车牌号，车型名，车型码，车图片链接）
    public final static String CAR_INFO_BASE_KEY = "_car_info_base_key";
}
