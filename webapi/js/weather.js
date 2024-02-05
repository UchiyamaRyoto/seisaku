$(function(){
	$.ajax({
        url: "https://weather.tsukumijima.net/api/forecast?city=230010",
        cache: false,
        type: "GET",
        dataType: "json",
        timeout: 10000
    }).done(function (data) {
        $("#section01 h1").html(data.title);
        $.each(data.forecasts,function(key,value) {
            console.log(value);
            $("#section01 .news-list").append('<li><dl><dt>' + value.date + ':' + value.temperature.max.celsius + 
            '<dt><dd>' + value.telop + '</dd></dl></li>')
        });
    });
});