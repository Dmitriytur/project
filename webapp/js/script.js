$(".thumb").click(function(event) {
    var target = $(event.target);
    var src = target.attr("src");
    $("#primary").attr("src",src);
    $(".thumb.active").removeClass('active');
    $(event.target).addClass("active");
});
