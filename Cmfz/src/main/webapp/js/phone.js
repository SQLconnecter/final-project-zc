$(function () {
    var windowHeight = $(window).height();
    $('.J_phoneNav').css('height', windowHeight);


    var J_phoneNav_listH = windowHeight - $('.J_phoneNav_top').outerHeight() - $('.J_phoneNav_bot').outerHeight();

    $('.J_phoneNav_list').css('height', J_phoneNav_listH)
    var J_phoneNav_listLength = $('.J_phoneNav_list li.J_phoneNav_listli').length;

    var J_phoneNav_listliHeight = (J_phoneNav_listH / J_phoneNav_listLength) - 1;
    var J_phoneNav_listliLineHeight = (J_phoneNav_listH / J_phoneNav_listLength) + 'px';
    $('.J_phoneNav_listli').css('height', J_phoneNav_listliHeight);
    $('.J_phoneNav_listli').css('lineHeight', J_phoneNav_listliLineHeight);


    $('.J_phone_icon').click(function () {
        if ($('.J_phoneNav').css('display') == 'block') {
            $('.J_phoneNav').fadeOut(500);
            $('body').css('overflow', 'auto');
        } else {
            $('.J_phoneNav').fadeIn(500);
            $('body').css('overflow', 'hidden');
        }
    })
    $('.J_phoneNavClose').click(function () {
        $('.J_phoneNav').fadeOut(500);
        $('body').css('overflow', 'auto');
    })


    var w = document.documentElement ? document.documentElement.clientWidth : document.body.clientWidth;//先获取窗口宽度
    var h = document.documentElement ? document.documentElement.clientHeight : document.body.clientHeight;
    if (w >= 320 && w < 1024) {
        var J_J_svg_bolangpadding = $('.J_svg_bolang').height() + 20;
        $('.J_partOne3').css('paddingTop', J_J_svg_bolangpadding);
        window.onscroll = function () {

            var gunT = document.documentElement.scrollTop || document.body.scrollTop;
            var scrollt = document.documentElement.scrollTop || document.body.scrollTop;

            if (gunT > 60 && h > 1000) {
                $('.J_phone_iconw').addClass('fixed');
            } else {
                $('.J_phone_iconw').removeClass('fixed');
            }
        }
    }
    if (w >= 320 && w < 768) {
        var J_loginHeight = $(document).height() - $('.J_partfw').outerHeight() - $('.phone_icon').outerHeight();
        $('.J_login').css('min-height', J_loginHeight);

        var J_partOne1Height = h - $('.J_phone_iconw').height();

        $('.J_partOne1c_l').css({
            'top': 'initial',
            'opacity': '0',
            height: "auto",
            bottom: '-60px'
        });
        $(".J_partOne1c_l").animate({
            bottom: '55px',
            opacity: 1,
            top: 'initial',
            height: 'auto'
        }, 800, 'linear');
    }


})