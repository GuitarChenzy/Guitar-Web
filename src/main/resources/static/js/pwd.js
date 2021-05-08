function send() {
    var url = $("#email").val();
    $.get("/send", {
            url: url
        }, function (data) {
            /*if (data.code != 200) {
             alert(data.message);
             } else {*/
            alert(data.message);
            //}
        }
    );
}