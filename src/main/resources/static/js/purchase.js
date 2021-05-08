var total = 0;
var flag = [];
setInterval(function () {
    for (var i = 0; i < $(".checkDeployInfo").length; i++) {
        var checkbox = $(".checkDeployInfo")[i];
        var price = $(".class-price")[i].innerHTML;
        var count = $(".class-count")[i].innerHTML;
        if (checkbox.checked && !flag[i]) {
            total = parseInt(price) * parseInt(count) + total;
            $("#total").val(total);
            flag[i] = true;
        }
        if (flag[i] == true && !checkbox.checked) {
            total = total - parseInt(price) * parseInt(count);
            $("#total").val(total);
            flag[i] = false;
        }
    }
}, 200);

$("#email").blur(function () {
    var email = document.getElementById("email").value;
    $.ajax({
        type: 'get',
        url: '/emailCheck?email=' + email,
        success: function (data) {
            document.getElementById("message2").innerHTML = data.message;
            if (data.code == 200) {
                $("#reg").disabled = false;
            }
        },
        error: function (err) {
            alert('出现错误了!!!');
        }
    });
});