/**
 * Created by 胡超云 on 2016/12/8.
 */

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return decodeURI(r[2]);
        return null;
    },
    $.formatMessage = function (str, arges) {
        var dep = [];
        dep = $.isArray(arges) ? arges : dep.push(arges);
        $.each(dep, function (n, val) {
            str = str.replace('{' + n + '}', dep[n]);
        });

        return str;
    },
    //获取近七天的日期
    $.getWorkDate = function () {
        var workDate = [];
        var date = new Date() + '';

        for (var n = 1; n < 8; n++) {
            var myDate = this.getNextDate(date, n);
            workDate.push({
                week: this.formatDate(myDate, 'w'),
                date: this.formatDate(myDate, 'MM-dd'),
                time: this.formatDate(myDate, 't')
            });
        }

        return workDate;
    },
    $.getNextDate = function (myDate, n) {

        myDate = typeof myDate == 'object' ? myDate : new Date(myDate);

        myDate.setDate(myDate.getDate() + n);
        var y = myDate.getFullYear();
        var m = myDate.getMonth() + 1;//获取当前月份的日期
        var d = myDate.getDate();
        return y + '/' + m + '/' + d;
    },
    $.formatDate = function (date, format) {

        date = typeof date == 'object' ? date : new Date(date);

        var paddNum = function (num) {
            num += "";
            return num.replace(/^(\d)$/, "0$1");
        };
        var getWeekDay = function (d) {
            if (d == 1) {
                return "周一";
            } else if (d == 2) {
                return "周二";
            } else if (d == 3) {
                return "周三";
            } else if (d == 4) {
                return "周四";
            } else if (d == 5) {
                return "周五";
            } else if (d == 6) {
                return "周六";
            } else if (d == 0) {
                return "周日";
            }
        };
        // 指定格式字符
        var cfg = {
            yyyy: date.getFullYear(), // 年 : 4位
            yy: date.getFullYear().toString().substring(2),// 年 : 2位
            M: date.getMonth() + 1, // 月 : 如果1位的时候不补0
            MM: paddNum(date.getMonth() + 1), // 月 : 如果1位的时候补0
            d: date.getDate(), // 日 : 如果1位的时候不补0
            dd: paddNum(date.getDate()),// 日 , 如果1位的时候补0
            h: date.getHours(), // 时
            hh: paddNum(date.getHours()), // 时,如果1位的时候补0
            m: date.getMinutes(), // 分
            mm: paddNum(date.getMinutes()), // 分,如果1位的时候补0
            s: date.getSeconds(), // 秒
            ss: paddNum(date.getSeconds()), // 秒 ,如果1位的时候补0
            w: getWeekDay(date.getDay()),// 周几
            t: Date.parse(date.getFullYear() + '/' + (paddNum(date.getMonth() + 1)) + '/' + paddNum(date.getDate())) / 1000, //时间戳yyyy-MM-dd
            T: Date.parse(date) / 1000 //时间戳yyyy-MM-dd hh:mm:ss
        };
        format || (format = "yyyy-MM-dd hh:mm:ss");
        return format.replace(/([a-z])(\1)*/ig, function (m) {
            return cfg[m];
        });
    },
    $.isAndroid = function () {
        var u = navigator.userAgent;
        return u.indexOf('Android') > -1 || u.indexOf('Adr') > -1;
    },
    $.isiOS = function () {
        var u = navigator.userAgent;
        return !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
    },
    $.showToast = function (text, myclass) {
        $.toast(text, 2000, myclass || "mytoast");
    },
    $.showSuccessToast = function (text) {
        $.showToast(text, "toast-success");
    },
    //获取详细地址
    $.getAllAddr = function (position) {
        return position.formattedAddress;
    },
    $.ajax_ = function (options, callback, isAllResult) {
        $.showIndicator();
        $.ajax({
            url: options.url,
            dataType: 'json',
            async: options.async || 'true',
            data: options.data  || {},
            type: options.method || 'GET',
            contentType: options.contentType || '',
            cache: false,
            error: function () {
                $.showToast(values.getConstant('SERVER_EXCEPTION'));
            },
            success: function (result) {
                $.hideIndicator();
                if (result.errCode != '000000' && result.errCode != '000005') {
                    $.showToast(result.errMsg);
                } else {
                    if(isAllResult){
                        typeof callback === 'function' && callback(result);
                    }else{
                        typeof callback === 'function' && callback(result.data);
                    }
                }
            }
        });
    },
    $.ajaxAll_ = function (options, callback, isTip) {
        $.showIndicator();
        $.ajax({
            url: options.url,
            dataType: 'json',
            async: options.async || 'true',
            data: options.data  || {},
            type: options.method || 'GET',
            contentType: options.contentType || '',
            cache: false,
            error: function () {
                $.showToast(values.getConstant('SERVER_EXCEPTION'));
            },
            success: function (result) {
                $.hideIndicator();
                if (result.errCode != '000000' && result.errCode != '000005' && isTip) {
                    $.showToast(result.errMsg);
                }
                typeof callback === 'function' && callback(result);
            }
        });
    },
    $.fn.scrollTo = function (options) {
        var defaults = {
            toT: 0,    //滚动目标位置
            durTime: 500,  //过渡动画时间
            delay: 30,     //定时器时间
            callback: null   //回调函数
        };
        var opts = $.extend(defaults, options),
            timer = null,
            _this = this,
            curTop = _this.scrollTop(),//滚动条当前的位置
            subTop = opts.toT - curTop,    //滚动条目标位置和当前位置的差值
            index = 0,
            dur = Math.round(opts.durTime / opts.delay),
            smoothScroll = function (t) {
                index++;
                var per = Math.round(subTop / dur);
                if (index >= dur) {
                    _this.scrollTop(t);
                    window.clearInterval(timer);
                    if (opts.callback && typeof opts.callback == 'function') {
                        opts.callback();
                    }

                } else {
                    _this.scrollTop(curTop + index * per);
                }
            };
        timer = window.setInterval(function () {
            smoothScroll(opts.toT);
        }, opts.delay);
        return _this;
    },
    $.initSearch = function () {
        $(document).on("focus", ".search-input input", function () {
            if (this.value == '') {
                $(this).parent().parent().addClass('active')
            }
        });

        $(document).on("blur", ".search-input input", function () {
            if (this.value == '') {
                $(this).parent().parent().removeClass('active');
            }
        });

        $(document).on("touchstart", ".search-input .icons-close", function () {
            $(this).siblings("input").val('');
            $(this).parent().parent().removeClass('active');
        });
    };
    //获取指定名称的cookie的值
    $.getCookie = function (objname){
        var arr,reg=new RegExp("(^| )"+objname+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return arr[2];
        else
            return null;
    },
    $.parseStr = function (str) {
        if(str == undefined || str == null || str == "undefined" || str == "null" || str == ""){
            return "";
        }else{
            return str;
        }
    },
    $.isNull = function (str) {
        if(str == undefined || str == null || str == "undefined" || str == "null" || str == ""){
            return true;
        }else{
            return false;
        }
    }
})($);