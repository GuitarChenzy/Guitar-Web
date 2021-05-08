var rs = 0;
$(function () {
    $('#bodylayout').layout();
    $("#munes ul").tree({
        onClick: function (node) {
            var text = node.text;
            var href = null;
            href = node.text;
            //判断当前右边是否已有相应的tab
            if ($("#tt").tabs("exists", text)) {
                $("#tt").tabs("select", text);
            }
            else if (href == "角色管理") {
                //如果没有则创建一个新的tab，否则切换到当前tag
                var src = "/admin/role";
                if (TabCheck(src) != 200) {
                    return;
                }
                $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
                    }
                );
                href = null;
            }
            else if (href == "用户管理") {
                var src = "/admin/admin";
                if (TabCheck(src) != 200) {
                    return;
                }
                $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
                    }
                );
                href = null;
            }
            else if (href == "角色管理") {
                var src = "/admin/role";
                if (TabCheck(src) != 200) {
                    return;
                }
                $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
                    }
                );
                href = null;
            }
            else if (href == "资源管理") {
                var src = "/admin/url";
                if (TabCheck(src) != 200) {
                    return;
                }
                $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
                    }
                );
                href = null;
            }
            else if (href == "类别管理") {
                var src = "/admin/category";
                if (TabCheck(src) != 200) {
                    return;
                }
                $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
                    }
                );
                href = null;
            }
            else if (href == "商品管理") {
                var src = "/admin/goods";
                if (TabCheck(src) != 200) {
                    return;
                }
                $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
                    }
                );
                href = null;
            }
            else if (href == "订单管理") {
                var src = "/admin/order";
                if (TabCheck(src) != 200) {
                    return;
                }
                $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
                    }
                );
                href = null;
            }
            else if (href == "销售统计") {
                var src = "/admin/log";
                if (TabCheck(src) != 200) {
                    return;
                }
                $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
                    }
                );
                href = null;
            }
            else {
                console.log("无效的tab");
            }
        }
    });
});

function TabCheck(url) {
    $.ajaxSettings.async = false;
    $.post("/admin/check",
        {
            src: url
        },
        function (data) {
            if (data.code != 200) {
                $.messager.alert("系统提示", data.message);
                rs = data.code;
            } else {
                console.log(data.code);
                rs = data.code;
            }
        }
    );
    return rs;
}

