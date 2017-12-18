/**
 * Created by 胡超云 on 2016/12/8.
 */
var aMap = {
    //打开腾讯内置地图
    openLocation: function (location) {
        weixin.openLocation(location);
        $.hideIndicator();
    },
    //微信js-sdk获取经纬度
    getLocal: function (callback, errorCallback) {
        //隐藏
        weixin.getLocation(callback, errorCallback);
    },
    getCity: function (setSession, callback) {
        this.getLocal(function (data) {
            $.ajax({
                url: '../../map/getCityName.do',
                type: "get",
                dataType: "json",
                data: {
                    lat: data.lat,
                    lng: data.lng
                },
                success: function (result) {
                    var city = result.data;
                    setSession(city, callback);
                }
            });
        });
    },
    //先从session中获取信息,如果session中没有，再去访问API
    getSessionCity: function (callback) {
        if (!$.isNull(sessionStorage.getItem('sessionCity'))) {
            callback();
        } else {
            this.getCity(this.setSessionCity, callback);
        }
    },
    setSessionCity: function (result, callback) {
        sessionStorage.setItem("sessionCity", result);
        callback();
    },
    rad: function (d) {
        return d * Math.PI / 180.0;
    },
    /*
     *两点之间的距离
     *（lng1.lat1）地址一的经纬度
     *（lng2.lat2）地址一的经纬度
     *单位米
     */
    getDistance: function (obj1, obj2) {
        var radLat1 = this.rad(obj1.lat);
        var radLat2 = this.rad(obj2.lat);
        var a = radLat1 - radLat2;
        var b = this.rad(obj1.lng) - this.rad(obj2.lng);
        var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
};