$(function () {
    // process message bar
    var prefix = "user";
    var msgBar = $("#message-bar");
    if (msgBar.length > 0) {
        var content = Cookies.get(prefix + "_messageContent");
        var type = Cookies.get(prefix + "_messageType");
        if (content !== undefined && type !== undefined) {
            $("#message-content").text(content);
            msgBar.addClass(type);
            msgBar.collapse("show");
        }
    }
    Cookies.remove(prefix + "_messageContent");
    Cookies.remove(prefix + "_messageType");
});

// bind button confirm action
function showConfirm(text, url) {
    $("#confirm-modal-body").text(text);
    $("#confirm-modal-btn").attr("href", url);
    $("#confirm-modal").modal("show");
}

function show() {

    var value = document.getElementById("response5").style.display;

    if (value == "none") {

        document.getElementById("response5").style.display = "block";

    } else

        document.getElementById("response5").style.display = "none";
}


function show1() {

    alert("您还没有登录，请登录后再进行操作！");
}

function show404() {

    alert("您由于发表不当言论，现已被禁言，请联系管理员之后进行操作！");
}

var b = 0;
$(document).on("click", "#response2", function () {
    var value = document.getElementById("response6").style.display;
    var name = $(this).attr("res-name");
    var id = $(this).attr("res-id");
    var themeId = $(this).attr("theme-id");
    var url = "/responseToResponse/new/" + id + "/" + themeId;
    if (value == "none") {
        if (b == 0) {
            document.getElementById("form").setAttribute("action", url);
            $("#textarea1").prepend('<label  for="theme-content">回复 @<span id="label1" class="badge-light">' + name + '</span></label>');

        } else {
            document.getElementById("label1").innerText = name;
        }
        document.getElementById("response6").style.display = "block";


    } else

        document.getElementById("response6").style.display = "none";
    b = b + 1;
});


$(document).on("click", "#response3", function () {
    var value = document.getElementById("response6").style.display;
    var name = $(this).attr("res-name");
    var responseToresponseId = $(this).attr("res-id");
    var themeId = $(this).attr("theme-id");
    var url = "/responseToResponse1/new/" + responseToresponseId + "/" + themeId;
    if (value == "none") {
        if (b == 0) {
            document.getElementById("form").setAttribute("action", url);
            $("#textarea1").prepend('<label  for="theme-content">回复 @<span id="label1" class="badge-light">' + name + '</span></label>');

        } else {
            document.getElementById("label1").innerText = name;
        }
        document.getElementById("response6").style.display = "block";


    } else

        document.getElementById("response6").style.display = "none";
    b = b + 1;
});


var a = 0;

function good(response) {
    var dataid = $(response).attr("data-id");
    var dataid2 = $(response).attr("data-id2");
    var c = dataid + "";
    var b = dataid2 + "";
    if (a % 2 == 0) {
        document.getElementById(c).style.color = "#FF0000";

        var zan1 = $(response).attr("goods");
        var zan = parseInt(zan1);
        zan = zan + 1;
        zan1 = zan;
        var url = 'http://localhost:8080/response/goodsLike/' + dataid;
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'json'
        });

        document.getElementById(b).innerHTML = zan1;

    } else {
        document.getElementById(c).style.color = "#A9A9A9";
        var zan1 = $(response).attr("goods");
        var url = 'http://localhost:8080/response/goodsNotLike/' + dataid;
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'json'
        });
        document.getElementById(b).innerHTML = zan1;
    }

    a = a + 1;

}


function themeGood(response) {
    var dataid = $(response).attr("data-id");
    var dataid2 = $(response).attr("data-id2");
    var dataid3 = $(response).attr("data-idtheme");
    var c = dataid + "";
    var b = dataid2 + "";
    if (a % 2 == 0) {
        document.getElementById(c).style.color = "#FF0000";

        var zan1 = $(response).attr("goods");
        var zan = parseInt(zan1);
        zan = zan + 1;
        zan1 = zan;
        var url = 'http://localhost:8080/theme/goodsLike/' + dataid3;
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'json'
        });

        document.getElementById(b).innerHTML = zan1;

    } else {
        document.getElementById(c).style.color = "#A9A9A9";
        var zan1 = $(response).attr("goods");
        var url = 'http://localhost:8080/theme/goodsNotLike/' + dataid3;
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'json'
        });
        document.getElementById(b).innerHTML = zan1;
    }

    a = a + 1;

}


function ResponseToResgood(response) {
    var dataid = $(response).attr("data-id");
    var dataid2 = $(response).attr("data-id2");
    var dataid3 = $(response).attr("data-idRes");
    var c = dataid + "";
    var b = dataid2 + "";
    if (a % 2 == 0) {
        document.getElementById(c).style.color = "#FF0000";

        var zan1 = $(response).attr("goods");
        var zan = parseInt(zan1);
        zan = zan + 1;
        zan1 = zan;
        var url = 'http://localhost:8080/responseToRes/goodsLike/' + dataid3;
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'json'
        });

        document.getElementById(b).innerHTML = zan1;

    } else {
        document.getElementById(c).style.color = "#A9A9A9";
        var zan1 = $(response).attr("goods");
        var url = 'http://localhost:8080/responseToRes/goodsNotLike/' + dataid3;
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'json'
        });
        document.getElementById(b).innerHTML = zan1;
    }

    a = a + 1;

}

