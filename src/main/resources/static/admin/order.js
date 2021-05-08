$('#dg').datagrid({
    remoteSort: false,
    singleSelect: true,
    nowrap: false,
    fitColumns: true,
    autoRowHeight: true,
    pagination: true,
    pageNumber: 1,
    pageList: [5, 10, 15, 20, 25],
    beforePageText: '第',
    afterPageText: '页    共 {pages} 页',
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
    pageSize: 10,
    url: '/order/getAll',
    columns: [[
        {
            field: 'oid', formatter: function formatOid(value, rows, index) {
            return rows.order.oid;
        },
            title: '订单编号', width: 10, align: 'center'
        },
        {
            field: 'realname',
            title: '用户姓名', width: 10, align: 'center'
        },
        {
            field: 'oadress', formatter: function formatAddress(value, rows, index) {
            return rows.order.oadress;
        },
            title: '订单地址', width: 20, align: 'center'
        },
        {
            field: 'oprice', formatter: function formatPrice(value, rows, index) {
            return parseInt(rows.order.oprice);
        },
            title: '订单金额', width: 10, align: 'center', sortable: true
        },
        {
            field: 'paytype', formatter: function formatPayType(value, rows, index) {
            return rows.order.paytype;
        },
            title: '支付方式', width: 10, align: 'center'
        },
        {
            field: 'odatetime', formatter: function formatDatetime(value, rows, index) {
            return rows.order.odatetime;
        },
            title: '订单时间', width: 20, align: 'center', sortable: true
        },
        {
            field: 'updatetime', formatter: function formatUpdate(value, rows, index) {
            return rows.order.updatetime;
        },
            title: '结算时间', width: 20, align: 'center', sortable: true
        },
        {
            field: 'state',
            styler: function stylerState(value, rows, index) {
                value = rows.order.state;
                if (value == 0) {
                    return 'color:red;';
                } else if (value == 1) {
                    return 'color:green;';
                } else if (value == 2) {
                    return 'color:grey;';
                } else {
                    return 'color:black;';
                }
            }, formatter: function formatState(value, rows, index) {
            value = rows.order.state;
            if (value == 0) {
                return "待支付";
            } else if (value == 1) {
                return "已支付";
            } else if (value == 2) {
                return "已取消";
            } else {
                return "已隐藏";
            }
        },
            title: '订单状态', width: 10, align: 'center'
        }
    ]],
    view: detailview,
    detailFormatter: function (rowIndex, rowData) {
        var html = '<table><th>商品名称</th><th>商品图片</th><th>购买数量</th><th>购买单价</th>';
        for (var i = 0, total = rowData.order.orderInfoList.length; i < total; i++) {
            html += '<tr><td style="border:0">' +
                '<p>' + rowData.order.orderInfoList[i].goods.gname + '</p></td>' +
                ' <td><img src="' + rowData.order.orderInfoList[i].goods.gimage + '" ' +
                'style="height:50px;width: 100px"> </td> ' +
                ' <td align="center" ><p>' + rowData.order.orderInfoList[i].ofcount + '</p></td> ' +
                ' <td align="center" ><p>' + rowData.order.orderInfoList[i].ofprice + '</p></td> ' +
                '</tr>';
        }
        html += '</table>';
        return html;
    }
});

var datalist = [];
datalist.push({"id": "", "text": "-------"},
    {"id": "0", "text": "待支付"}, {"id": "1", "text": "已支付"},
    {"id": "2", "text": "已取消"}, {"id": "3", "text": "已删除"});
$("#state").combobox({
    data: datalist,
    valueField: 'id',
    textField: 'text'
});

function deleteOrder() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的订单信息！");
        return;
    }
    var strSno = [];
    for (var i = 0; i < selectedRows.length; i++) {
        strSno.push(selectedRows[i].order.oid);
    }
    var oid = strSno.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
        selectedRows.length + "</font>条数据吗？", function (r) {
        if (r) {
            $.post("/order/delete", {
                oid: oid
            }, function (result) {
                $.messager.alert("系统提示", result.message);
                if (result.code == 200) {
                    $("#dg").datagrid("reload");
                }
            });
        }
    });
}

function findOrder(value, name) {
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var state = $("#state").combobox('getValue');
    /*if (value == "" || value == null) {
     alert("查询条件不能为空！");
     return;
     }*/
    $.get("/order/get", {
            name: name,
            value: value,
            state: state,
            startTime: startTime,
            endTime: endTime
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