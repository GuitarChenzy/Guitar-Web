var t = $("#text_box");
var price = $("#price");
var count = $("#count");
t.val(0);
$("#add").click(function () {
    t.val(parseInt(t.val()) + 1);
    alert($("#gcount")).html();
    setTotal();
});
$("#min").click(function () {
    t.val(parseInt(t.val()) - 1);
    if (parseInt(t.val()) < 0) {
        t.val(0);
    }
    setTotal();
});
function setTotal() {
    count.text(t.val());
    $("#total").html((parseInt(t.val()) * price.text() ).toFixed(2));
}
//setTotal();