var sort = (function () {

    function init(citys, callback) {
        var Initials = $('.initials ul');
        var SortBox = $(".sort_box ul");
        Initials.html("");
        SortBox.html("").removeClass("no-city-ul");

        if (citys.length == 0) {
            SortBox.html("<li class='no-city'>无结果</li>");
            SortBox.addClass("no-city-ul");
            return;
        }

        Initials.append('<li>A</li><li>B</li><li>C</li><li>D</li><li>E</li><li>F</li><li>G</li><li>H</li><li>I</li><li>J</li><li>K</li><li>L</li><li>M</li><li>N</li><li>O</li><li>P</li><li>Q</li><li>R</li><li>S</li><li>T</li><li>U</li><li>V</li><li>W</li><li>X</li><li>Y</li><li>Z</li><li>#</li>');
        $.each(citys, function (index, city) {
            SortBox.append(" <li class='item-content sort_list'><div class='item-inner num_name'>" + city + "</div></li>");
        });

        initials();
        bindLetter();
        bindSortList(callback);
    }

    function initials() {
        var SortList = $(".sort_list");
        var SortBox = $(".sort_box ul");
        //按首字母排序
        SortList.sort(asc_sort).appendTo('.sort_box');
        function asc_sort(a, b) {
            return makePy($(b).find('.num_name').text().charAt(0))[0].toUpperCase() < makePy($(a).find('.num_name').text().charAt(0))[0].toUpperCase() ? 1 : -1;
        }

        var initials = [];
        var num = 0;
        SortList.each(function (i) {
            var initial = makePy($(this).find('.num_name').text().charAt(0))[0].toUpperCase();
            if (initial >= 'A' && initial <= 'Z') {
                if (initials.indexOf(initial) === -1)
                    initials.push(initial);
            } else {
                num++;
            }
        });

        $.each(initials, function (index, value) {//添加首字母标签
            SortBox.append('<li class="item-content border-top border-bottom sort_letter" id="' + value + '">' + value + '</li>');
        });
        if (num != 0) {
            SortBox.append('<li class="item-content border-top border-bottom sort_letter" id="default">#</li>');
        }

        for (var i = 0; i < SortList.length; i++) {//插入到对应的首字母后面
            var letter = makePy(SortList.eq(i).find('.num_name').text().charAt(0))[0].toUpperCase();
            switch (letter) {
                case "A":
                    $('#A').after(SortList.eq(i));
                    break;
                case "B":
                    $('#B').after(SortList.eq(i));
                    break;
                case "C":
                    $('#C').after(SortList.eq(i));
                    break;
                case "D":
                    $('#D').after(SortList.eq(i));
                    break;
                case "E":
                    $('#E').after(SortList.eq(i));
                    break;
                case "F":
                    $('#F').after(SortList.eq(i));
                    break;
                case "G":
                    $('#G').after(SortList.eq(i));
                    break;
                case "H":
                    $('#H').after(SortList.eq(i));
                    break;
                case "I":
                    $('#I').after(SortList.eq(i));
                    break;
                case "J":
                    $('#J').after(SortList.eq(i));
                    break;
                case "K":
                    $('#K').after(SortList.eq(i));
                    break;
                case "L":
                    $('#L').after(SortList.eq(i));
                    break;
                case "M":
                    $('#M').after(SortList.eq(i));
                    break;
                case "N":
                    $('#N').after(SortList.eq(i));
                    break;
                case "O":
                    $('#O').after(SortList.eq(i));
                    break;
                case "P":
                    $('#P').after(SortList.eq(i));
                    break;
                case "Q":
                    $('#Q').after(SortList.eq(i));
                    break;
                case "R":
                    $('#R').after(SortList.eq(i));
                    break;
                case "S":
                    $('#S').after(SortList.eq(i));
                    break;
                case "T":
                    $('#T').after(SortList.eq(i));
                    break;
                case "U":
                    $('#U').after(SortList.eq(i));
                    break;
                case "V":
                    $('#V').after(SortList.eq(i));
                    break;
                case "W":
                    $('#W').after(SortList.eq(i));
                    break;
                case "X":
                    $('#X').after(SortList.eq(i));
                    break;
                case "Y":
                    $('#Y').after(SortList.eq(i));
                    break;
                case "Z":
                    $('#Z').after(SortList.eq(i));
                    break;
                default:
                    $('#default').after(SortList.eq(i));
                    break;
            }
        }
        SortBox.find(".sort_letter").prev(".sort_list").find(".item-inner").addClass("last-city");
    }

    //绑定右侧字母导航点击事件
    function bindLetter() {
        var LetterBox = $('.letter');
        $(".initials ul li").click(function () {
            var _this = $(this);
            var LetterHtml = _this.html();
            LetterBox.html(LetterHtml).show();

            setTimeout(function () {
                LetterBox.hide();
            }, 1000);

            var preSort = $(".pre_sort").height() ? $(".pre_sort").height() : 0;
            var _index = _this.index();
            if (_index == 0) {
                $('.sort_box').parent().scrollTo({toT: preSort, durTime: 300});
            } else if (_index == 27) {
                var DefaultTop = $('#default').position().top;
                $('.sort_box').parent().scrollTo({toT: DefaultTop, durTime: 300});
                // $('html,body').animate({scrollTop: DefaultTop + 'px'}, 300);//点击最后一个滚到#号
            } else {
                var letter = _this.text();
                if ($('#' + letter).length > 0) {
                    var LetterTop = $('#' + letter).position().top;
                    $('.sort_box').parent().scrollTo({toT: LetterTop + preSort, durTime: 300});
                }
            }
        });
    }

    //绑定城市列表点击事件
    function bindSortList(callback) {
        $(".sort_list").click(function () {
            var city = $(this).children('.num_name').text();
            cityClick(city, callback);
        });
    }

    //点击城市
    function cityClick(city, callback) {
        setUseCitySession(city);
        closePopupCity(city);
        typeof callback === 'function' && callback(city);
    }

    //设置常用城市sessionStorage
    function setUseCitySession(city) {
        var useCity = localStorage.getItem("useCity");
        if (!$.isNull(useCity)) {
            useCity = useCity.split(",");
            for (var i = 0; i < useCity.length; i++) {
                if (useCity[i] == city) {
                    useCity.splice(i, 1);   //从start位置开始删除deleteCount项
                    break;
                }
            }
        } else {
            useCity = [];
        }

        if (useCity.unshift(city) > 3) { //添加到原数组开头，并返回数组的长度
            useCity.splice(3, useCity.length);
        }
        localStorage.setItem("useCity", useCity.join(","));
    }

    //关闭选择城市popup
    function closePopupCity(city) {
        if (city != undefined) {
            if (city == "") {
                $('.sel_city').text("选择");
            } else {
                $('.sel_city').text(city);
                sessionStorage.setItem('lastCity' ,city);
                city.length >= 4 ? $('.sel_city').css("font-size", ".6rem"): $('.sel_city').removeAttr("style");
            }
        }
        $.closeModal(".popup-city");
    }

    return {
        init: init,
        cityClick: cityClick
    }
})();
