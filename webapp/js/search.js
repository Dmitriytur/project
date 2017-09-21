function sendSearchRequest(page) {
    var name = $("#name").val();
    var category = $("#category").val();
    var sortBy = $("#sortBy").val();
    var order = $("#order").val();
    var limit =  $("#limit").val();


    var params = {name: name, category: category, sortBy: sortBy, order:order, limit: limit, page: page};
    $.ajax({
        url: '/page/searchPartial',
        type: 'GET',
        data: jQuery.param( params ),
        success: function(response) {
             $('#searchResult').html(response);
        }
    });
}

$( "#searchBtn").click(function(event) {
    sendSearchRequest(1);
});



$(document).on('click', '.prevPage', function (event) {
    var page = parseInt($(".pagination .active a").text());
    sendSearchRequest(page - 1);
})

$(document).on('click', '.nextPage', function (event) {
    var page = parseInt($(".pagination .active a").text());
    sendSearchRequest(page + 1);
})

$(document).on('click', '.pageLink', function (event) {
    var page = parseInt($(event.target).text());
    sendSearchRequest(page);
});

$(window).on('load', function(){
     $( "#searchBtn" ).trigger( "click" );
});
