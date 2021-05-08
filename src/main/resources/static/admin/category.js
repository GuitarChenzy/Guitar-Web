//查询类别
function findCategory() {
    var cid = $("#snoo").val();
    if (cid == "" || cid == null) {
        alert("查询类别编号不能为空！");
        return;
    }
    $.get("/category/get", {
            cid: cid
        },
        function (result) {
            if (result.code != 200) {
                $.messager.alert("失败", result.message);
            }
            else {
                $("#dg").datagrid("loadData", {
                    total: 0,
                    rows: []
                });
                $("#dg").datagrid("loadData", result.rows);
            }
        });
}

//查找所有类别
function findCategoryAll() {
    $("#dg").datagrid("reload");
}

//关闭窗口
function closeStudentDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

//添加窗口
function openCategoryAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "添加类别信息");
    $.post("/category/get_parent", {},
        function (data) {
            var datalist = [];
            //data = $.parseJSON(data);
            for (i in data.data) {
                datalist.push({
                    "id": data.data[i].cid,
                    "text": data.data[i].cname
                });
            }
            $("#parent").combobox({
                data: datalist,
                valueField: 'id',
                textField: 'text'
            });
        }
    );
    url = "/category/add";
}

//修改窗口
function openCategoryModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要修改的信息！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "编辑类别信息");
    $("#fm").form("load", row);
    $.post("/category/get_parent", {},
        function (data) {
            var datalist = [];
            //data = $.parseJSON(data);
            for (i in data.data) {
                datalist.push({
                    "id": data.data[i].cid,
                    "text": data.data[i].cname
                });
            }
            $("#parent").combobox({
                data: datalist,
                valueField: 'id',
                textField: 'text'
            });
        }
    );
    url = "/category/update";
}

//重置
function resetValue() {
    $("#cid").val("");
    $("#cname").val("");
}

//删除类别信息
function deleteCategory() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的类别信息！");
        return;
    }
    var strSno = [];
    for (var i = 0; i < selectedRows.length; i++) {
        strSno.push(selectedRows[i].cid);
    }
    var cid = strSno.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
        selectedRows.length + "</font>条数据吗？", function (r) {
        if (r) {
            $.post("/category/delete", {
                cid: cid
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
function saveCategory() {
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
            if (result.code == 200) {
                $.messager.alert("系统提示", result.message);
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            }
            else {
                $.messager.alert("系统提示", result.message);
            }
        }
    });
}