$(function(){

    $(".btn-gnavi").on("click",function(){
        var rightval = 0;
        if($(this).hasClass("open")) {
            rightval = -300;
            $(this).removeClass("open");
            $("global-navi").css("right","-300px");
        }
        else {
            $(this).addClass("open");
            $("global-navi").css("right","0px");
        }
        $("#global-navi").stop().animate({
            right: rightval
        },200);
    });
});