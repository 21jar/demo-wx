/**
 * Created by 胡超云 on 2016/12/21.
 */
$(function () {
    var openId = $.getCookie('open_id');
    var getOpenIdUrl = '@h5.getOpenIdUrl@';
    var toUrl = window.location.href;
    if ($.isNull(openId)) {
        getOpenIdUrl += '?toUrl=' + encodeURIComponent(toUrl);
        var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=@wx.appId@&redirect_uri=' + encodeURIComponent(getOpenIdUrl) + '&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect';
        window.location.href = url;
    }
});