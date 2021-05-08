
function roleFormatter(value, row, index) {
    var role = "";
    for(var i = 0;i<row.roleList.length;i++){
        role += row.roleList[i].rolename + ",";
    }
    return role;
}

//关闭窗口
function closeAdminDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

//添加窗口
function openAdminAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "添加管理员信息");
    $.post("/admin/get_parent", {},
        function (data) {
            var datalist = [];
            //data = $.parseJSON(data);
            for (i in data.data) {
                datalist.push({
                    "id": data.data[i].id,
                    "text": data.data[i].text
                });
            }
            $("#cc").combotree({
                data: datalist,
                valueField: 'id',
                textField: 'text'
            });
        }
    );
    url = "/admin/add";
}

//修改窗口
function openAdminModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要修改的信息！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "编辑管理员信息信息");
    $("#fm").form("load", row);
    $.post("/admin/get_parent", {},
        function (data) {
            var datalist = [];
            //data = $.parseJSON(data);
            for (i in data.data) {
                datalist.push({
                    "id": data.data[i].id,
                    "text": data.data[i].text
                });
            }
            $("#cc").combotree({
                data: datalist,
                valueField: 'id',
                textField: 'text'
            });
        }
    );
    url = "/admin/update";
}

//重置
function resetValue() {
    $("#username").val("");
    $("#rolename").val("");
    $("#password").val("");
}

//删除类别信息
function deleteAdmin() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的管理员信息！");
        return;
    }
    var strSno = [];
    for (var i = 0; i < selectedRows.length; i++) {
        strSno.push(selectedRows[i].aid);
    }
    var aid = strSno.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
        selectedRows.length + "</font>条数据吗？", function (r) {
        if (r) {
            $.post("/admin/delete", {
                aid: aid
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
function saveAdmin() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($("#username").val() == "" || $("#password").val() == "") {
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