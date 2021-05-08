function rchildFormatter(value, row, index) {
    var role = "";
    var rows = $("#dg").datagrid("getRows");
    for (var i = 0; i < rows.length; i++) {
        if (value == rows[i].rid) {
            role = rows[i].rolename;
        }
    }
    return role;
}

//关闭窗口
function closeRoleDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

//添加窗口
function openRoleAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "添加管理员信息");
    $.post("/role/get_parent", {},
        function (data) {
            var datalist = [];
            //data = $.parseJSON(data);
            for (i in data.data) {
                datalist.push({
                    "id": data.data[i].rid,
                    "text": data.data[i].rolename
                });
            }
            $("#parent").combobox({
                data: datalist,
                valueField: 'id',
                textField: 'text'
            });
        }
    );
    $.post("/role/get_url", {},
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
    url = "/role/add";
}

//修改窗口
function openRoleModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要修改的信息！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "编辑管理员信息信息");
    $("#fm").form("load", row);
    $.post("/role/get_parent", {},
        function (data) {
            var datalist = [];
            //data = $.parseJSON(data);
            for (i in data.data) {
                datalist.push({
                    "id": data.data[i].rid,
                    "text": data.data[i].rolename
                });
            }
            $("#parent").combobox({
                data: datalist,
                valueField: 'id',
                textField: 'text'
            });
        }
    );
    $.post("/role/get_url", {},
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
    url = "/role/update";
}

//重置
function resetValue() {
    $("#rdesc").val("");
    $("#rolename").val("");
}

//删除类别信息
function deleteRole() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的角色信息！");
        return;
    }
    var strSno = [];
    var strchild = [];
    for (var i = 0; i < selectedRows.length; i++) {
        strSno.push(selectedRows[i].rid);
        strchild.push(selectedRows[i].rchild);
    }
    var rid = strSno.join(",");
    var rchild = strchild.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
        selectedRows.length + "</font>条数据吗？", function (r) {
        if (r) {
            $.post("/role/delete", {
                rid: rid,
                rchild: rchild
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
function saveRole() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($("#rolename").val() == "" || $("#parent").val() == "") {
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