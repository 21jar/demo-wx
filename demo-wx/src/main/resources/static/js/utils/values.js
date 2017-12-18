/**
 * Created by 胡超云 on 2016/5/16.
 */
var values = (function () {

    //常量定义
    var constants = {
        'SERVER_EXCEPTION': '服务器异常',
        
        'VIN_NUM_EMPTY': "请输入车辆识别代号",
        'VIN_NUM_EXCEPTION': "车辆识别代号格式有误，请重新输入",
        'ENGINE_NUM_EMPTY': "请至少输入发动机号后7位",
        'ENGINE_NUM_EXCEPTION': "发动机号后4位格式有误，请重新输入",
        'ENGINE_NUM_EXCEPTION_7': "发动机号后7位格式有误，请重新输入",
        'ENGINE_NUM_FULL_EMPTY': "请输入发动机号",
        'NAME_EMPTY': "请输入姓名",
        'NAME_EXCEPTION': "请输入真实姓名",
        'MOBILE_EMPTY': "请输入手机号",
        'MOBILE_EXCEPTION': "手机号码格式有误，请重新输入",
        'SEX_EMPTY': "请选择性别",
        'CARD_ID_EMPTY': "请输入身份证号",
        'CARD_ID_EXCEPTION': "身份证号格式有误，请重新输入",
        'CAR_NUM_EMPTY': "请输入车牌号",
        'CAR_NUM_EXCEPTION': "车牌号格式有误，请重新输入",
        'CAR_SERIES': "请选择车型",
        'TRANSFER_TIME': "请选择过户时间",
        'REGISTER_TIME': "请选择行驶证注册时间",
        'SAFES_TIME': "请选择保险到期时间",
        'MOBILE_CODE_EMPTY': '请输入正确验证码',
        'SEND_SUCCESS': '验证码发送成功！',
        'BIND_SUCCESS': '绑定成功！',
        'SAVE_SUCCESS': '保存成功！',
        'UPDATE_SUCCESS': '修改成功！',
        'UNBIND_SUCCESS': '解绑成功！',
        'DELETE_SUCCESS': '删除成功！',
        
        'ORDER_TIME_EXCEPTION': '请选择预约时间',
        'ORDER_CAR_EXCEPTION': '请选择爱车',
        'TASTE_EMPTY': '请输入服务体验',
        'ORDER_SUCCESS': '预约成功！',
        'ORDER_CANCEL_SUCCESS': '预约取消成功！',
        'SUBMIT_SUCCESS': '提交成功！',
        
        'CONTACT_NAME_EMPTY': "请输入联络人姓名",
        'CONTACT_NAME_EXCEPTION': "请输入联络人真实姓名",
        'CONTACT_MOBILE_EMPTY': "请输入联络人手机号",
        'CONTACT_MOBILE_EXCEPTION': "联络人手机号码格式有误，请重新输入",
        'CONTACT_SEX_EMPTY': "请选择联络人性别",
        'APPLY_SUCCESS': '申请成功！',

        'STORE_EMPTY': "请选择特约店",
        'ADDRESS_EMPTY': "请输入详细地址",
        'RESCUE_MOBILE_EMPTY': "请输入救援电话",
        'RESCUE_MOBILE_EXCEPTION': "救援电话格式有误，请重新输入",
        'SERVICE_MOBILE_EMPTY': "请输入服务电话",
        'SERVICE_MOBILE_EXCEPTION': "服务电话格式有误，请重新输入",

        'NUM_EMPTY': "请输入参与人数",
        'NUM_EXCEPTION': "参与人数格式有误，请重新输入",
        'SIGN_SUCCESS': '报名成功！',
        
        'SCAN_FAILURE': "照片无法识别，请重新拍照",
        'SCAN_SUCCESS': "识别成功!",
        	
        'NO_MORE': '没有更多了'
    };

    //险种
    var insuranceType = {
        '911': '车损险',
        '912': '三者险',
        '913': '司机险',
        '914': '乘客险',
        '915': '盗抢险',
        '91A': '玻璃险',
        '91B': '自燃险',
        '91C': '划痕险',
        '91E': '涉水险',
        '91G': '不计免赔-车损',
        '91Q': '不计免赔-三者',
        '91R': '不计免赔-司机',
        '91S': '不计免赔-乘客',
        '91H': '不计免赔-盗抢',
        '91I': '不计免赔-自燃',
        '91J': '不计免赔-划痕',
        '91K': '不计免赔-涉水'
    };

    var val = {};

    //获取常量的方法
    val.getConstant = function (key) {
        return constants[key];
    };

    //获取险种的方法
    val.getInsuranceType = function (key) {
        return insuranceType[key];
    };

    //获取保额的方法
    val.getInsuranceMoney = function (key, value) {
        if($.isNull(value)){
            return "已投保";
        }else if(key == "912" || key == "913") {
            return value / 10000 + "万";
        }else if(key == "914"){
            return value / 10000 + "万/座";
        }else if(key == "91A"){
            if(value == 0) {
                return "国产玻璃";
            }else if(value == 1){
                return "进口玻璃";
            }
        }else if(key == "911" || key == "915" || key == "91C" ){
            return value + "元";
        }

        return value;
    };
    
    return val;
})();