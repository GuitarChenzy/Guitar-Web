//页面载入
$.post("/goods/get_parent", {},
    function (data) {
        var datalist = [];
        var dataprice = [];
        dataprice.push({"id": "", "text": "-------"},
            {"id": "0-1000", "text": "0-1000"}, {"id": "1000-2000", "text": "1000-2000"},
            {"id": "2000-5000", "text": "2000-5000"}, {"id": "5000-10000", "text": "5000-10000"},
            {"id": "10000-20000", "text": "10000-20000"}, {"id": "20000", "text": "20000以上"});
        datalist.push({
            "id": "", "text": "-------"
        });
        for (i in data.data) {
            datalist.push({
                "id": data.data[i].cid,
                "text": data.data[i].cname
            });
        }
        $("#category").combobox({
            data: datalist,
            valueField: 'id',
            textField: 'text'
        });
        $("#price").combobox({
            data: dataprice,
            valueField: 'id',
            textField: 'text'
        })
    }
);

//查询商品
function findGoods(value, name) {
    var category = $("#category").combobox('getValue');
    var checkState = $("input:radio[name='checkState']:checked").val();
    var price = $("#price").combobox('getValue');
    $.get("/goods/get", {
            name: name,
            value: value,
            category: category,
            checkState: checkState,
            price: price
        },
        function (result) {
            if (result.code != 200) {
                $.messager.alert("失败", result.message);
            }
            else {
                $("#dg").datagrid("loadData", {
                    total: result.total,
                    rows: result.rows
                });
            }
        });
}

//关闭窗口
function closeStudentDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

//添加窗口
function openGoodsAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "添加商品信息");
    $("#picture").filebox({
        buttonText: '选择图片',
        buttonAlign: 'right'
    });
    $.post("/goods/get_parent", {},
        function (data) {
            var datalist = [];
            var datastate = [];
            datastate.push({"id": 1, "text": "上架"}, {"id": 0, "text": "下架"});
            for (i in data.data) {
                datalist.push({
                    "id": data.data[i].cid,
                    "text": data.data[i].cname
                });
            }
            $("#gtypeid").combobox({
                data: datalist,
                valueField: 'id',
                textField: 'text'
            });
            $("#gstate").combobox({
                data: datastate,
                valueField: 'id',
                textField: 'text'
            });
        }
    );
    url = "/goods/add";
}

//修改窗口
function openGoodsModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要修改的信息！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "编辑商品信息");
    $("#fm").form("load", row);
    $("#picture").filebox({
        buttonText: '选择图片',
        buttonAlign: 'right'
    });
    $.post("/goods/get_parent", {},
        function (data) {
            var datalist = [];
            var datastate = [];
            datastate.push({"id": 1, "text": "上架"}, {"id": 0, "text": "下架"});
            for (i in data.data) {
                datalist.push({
                    "id": data.data[i].cid,
                    "text": data.data[i].cname
                });
            }
            $("#gtypeid").combobox({
                data: datalist,
                valueField: 'id',
                textField: 'text'
            });
            $("#gstate").combobox({
                data: datastate,
                valueField: 'id',
                textField: 'text'
            });
        }
    );
    url = "/goods/update";
}

//重置
function resetValue() {
    $("#gid").val("");
    $("#gname").val("");
    $("#gdesc").val("");
}

//删除商品信息
function deleteGoods() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的商品信息！");
        return;
    }
    var strSno = [];
    for (var i = 0; i < selectedRows.length; i++) {
        strSno.push(selectedRows[i].gid);
    }
    var gid = strSno.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
        selectedRows.length + "</font>条数据吗？", function (r) {
        if (r) {
            $.post("/goods/delete", {
                gid: gid
            }, function (result) {
                $.messager.alert("系统提示", result.message);
                if (result.code == 200) {
                    $("#dg").datagrid("reload");
                }
            });
        }
    });
}

//增加商品信息
function saveGoods() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            if ($("#gname").val() == "" || $("#gprice").val() == "" ||
                $("#gcount").val() == "") {
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

//格式化图片
function imgFormatter(value, row, index) {
    if ('' != value && null != value) {
        var strs = new Array(); //定义一数组
        if (value.substr(value.length - 1, 1) == ",") {
            value = value.substr(0, value.length - 1)
        }
        strs = value.split(","); //字符分割
        var rvalue = "";
        for (i = 0; i < strs.length; i++) {
            rvalue += "<img onclick=download(\"" + strs[i] + "\") " +
                "style='width:66px; height:60px;margin-left:3px;' src='.." +
                strs[i] + "' title='点击查看图片'/>";
        }
        return rvalue;
    }
}

//格式化描述
function descFormatter(value) {
    var rvalur = "<textarea readonly='readonly'>" + value + "</textarea>";
    return rvalur;
}

//样式化状态
function gstateStyler(value, row, index) {
    if (value == '上架') {
        return 'color:green;';
    }
    if (value == '下架') {
        return 'color:red;';
    }
}

//样式化库存
function gcountStyler(value, row, index) {
    if (value < 20) {
        return 'background-color:#ffee00;color:red;';
    }
    if (value > 180) {
        return 'background-color:#ffee00;color:red;';
    }
}

//图片预览
function download(img) {
    $('#dlg2').dialog({
        title: '预览',
        width: 650,
        height: 350,
        resizable: true,
        closed: false,
        cache: false,
        modal: true
    });
    $("#simg").attr("src", img);

}