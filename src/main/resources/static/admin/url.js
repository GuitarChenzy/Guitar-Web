
//关闭窗口
function closeUrlDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

//添加窗口
function openUrlAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "添加资源信息");
    url = "/url/add";
}

//修改窗口
function openUrlModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要修改的信息！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "编辑管理员信息信息");
    $("#fm").form("load", row);
    url = "/url/update";
}

//重置
function resetValue() {
    $("#fname").val("");
    $("#furl").val("");
}

//删除类别信息
function deleteUrl() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的角色信息！");
        return;
    }
    var strSno = [];
    for (var i = 0; i < selectedRows.length; i++) {
        strSno.push(selectedRows[i].fid);
    }
    var fid = strSno.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
        selectedRows.length + "</font>条数据吗？", function (r) {
        if (r) {
            $.post("/url/delete", {
                fid: fid
            }, function (result) {
                if (result.code == 200) {
                    $.messager.alert("系统提示", result.message);
                    $("#dg").datagrid("reload");
                }
                else {
                    $.messager.alert("系统提示", result.message);
                }
            });
        }
    });
}

//增加类别信息
function saveUrl() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($("#cname").val() == "" || $("#parent").val() == "") {
                $.messager.alert("系统提示", "请填写完整信息！");
                return false;
            }
            return $(this).form("validate");
        },
        success: function (result) {
            result = $.parseJSON(result);
            $.messager.alert("系统提示", result.message);
            if (result.code == 200) {
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            }
        }
    });
}