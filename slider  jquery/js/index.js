$(function() {
    var imgList = [
        "images/img01.jpg",
        "images/img02.jpg",
        "images/img03.jpg",
        "images/img04.jpg",
        "images/img05.jpg",
        "images/img06.jpg",
        "images/img07.jpg",
        "images/img08.jpg",
        "images/img09.jpg"
    ];
    for (var i = 0; i < imgList.length; i++) {
        var li = $("<li>");
        li.html("<img src='" + imgList[i] + "'>");
        if (i == 0) li.addClass("show");
        $(".slider-inner").eq(0).append(li);

        var navLi = $("<li>");
        navLi.attr("data-nav-index", i);
        if (i == 0) navLi.addClass("current");
        $(".nav").eq(0).append(navLi);
    }

    var idx = 0;
    var isChanging = false;

    $("#arrow-next").click(function() {
        var index = idx + 1;
        if (index == imgList.length) index = 0;
        sliderSlide(index);
    });

    $("#arrow-prev").click(function() {
        var index = idx - 1;
        if (index < 0) index = imgList.length - 1;
        sliderSlide(index);
    });

    for (var i = 0; i < dotNavigation.length; i++) {
        $(".nav li").eq(i).click(function() {
            var index = Number($(this).attr('data-nav-index'));
            sliderSlide(index);
        });
    }

    function sliderSlide(val) {
        if (isChanging === true) {
            return false;
        }
        $(".slider-inner li").removeClass('show');
        $(".nav li").removeClass('current');
    
        idx = val;
    
        $(".slider-inner li").eq(idx).addClass("show");
        $(".nav li").eq(idx).addClass("current");
    
        isChanging = true;
    
        setTimeout(function() {
            isChanging = false;
        }, 600);
    }
});