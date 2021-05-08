$(function () {
    var myChart1 = echarts.init(document.getElementById('main1'));
    var myChart2 = echarts.init(document.getElementById('main2'));
    var myChart3 = echarts.init(document.getElementById('main3'));
    var myChart4 = echarts.init(document.getElementById('main4'));
    var myChart5 = echarts.init(document.getElementById('main5'));
    var myChart6 = echarts.init(document.getElementById('main6'));
    var myChart7 = echarts.init(document.getElementById('main7'));
    myChart1.showLoading();
    myChart2.showLoading();
    myChart3.showLoading();
    myChart4.showLoading();
    myChart5.showLoading();
    myChart6.showLoading();
    myChart7.showLoading();

    $.ajax({
        type: "POST",
        url: "/message/getdata",
        data: {},
        success: function (data) {
            option1 = {
                title: {
                    text: '类别-销售量扇形统计图',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: data.data[0]
                },
                series: [
                    {
                        name: '商品类别',
                        type: 'pie',
                        selectedMode: 'single',
                        radius: [0, '30%'],

                        label: {
                            normal: {
                                position: 'inner'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: data.data[1]
                    },
                    {
                        name: '销售情况',
                        type: 'pie',
                        radius: ['40%', '55%'],
                        label: {
                            normal: {
                                formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                                backgroundColor: '#eee',
                                borderColor: '#aaa',
                                borderWidth: 1,
                                borderRadius: 4,
                                // shadowBlur:3,
                                // shadowOffsetX: 2,
                                // shadowOffsetY: 2,
                                // shadowColor: '#999',
                                // padding: [0, 7],
                                rich: {
                                    a: {
                                        color: '#999',
                                        lineHeight: 22,
                                        align: 'center'
                                    },
                                    // abg: {
                                    //     backgroundColor: '#333',
                                    //     width: '100%',
                                    //     align: 'right',
                                    //     height: 22,
                                    //     borderRadius: [4, 4, 0, 0]
                                    // },
                                    hr: {
                                        borderColor: '#aaa',
                                        width: '100%',
                                        borderWidth: 0.5,
                                        height: 0
                                    },
                                    b: {
                                        fontSize: 16,
                                        lineHeight: 33
                                    },
                                    per: {
                                        color: '#eee',
                                        backgroundColor: '#334455',
                                        padding: [2, 4],
                                        borderRadius: 2
                                    }
                                }
                            }
                        },
                        data: data.data[2]
                    }
                ]
            };
            myChart1.setOption(option1);
            myChart1.hideLoading();
            option2 = {
                title: {
                    text: '类别-商品扇形统计图',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    containLabel: true
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: data.data[0]
                },
                series: [
                    {
                        name: '类别比例',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: data.data[3]
                    }
                ]
            };
            myChart2.setOption(option2);
            myChart2.hideLoading();
            option3 = {
                title: {
                    text: '商品-销售量柱形统计图',
                    subtext: '统计前9个热销商品信息'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['销售量', '库存量']
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: data.data[4]
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '销售量',
                        type: 'bar',
                        data: data.data[5],
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                    {
                        name: '库存量',
                        type: 'bar',
                        data: data.data[6],
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
            myChart3.setOption(option3);
            myChart3.hideLoading();
        }
    });

    $.ajax({
        type: "Get",
        url: "/message/getAllGoods",
        success: function (result) {
            var data = result.data;
            $("#tid").empty();
            $("#tid").append("<option>" + "----请选择---- " + "</option>");
            $.each(data, function (index, item) {
                $("#tid").append("<option value=" + item.gid + ">" + item.gname + "</option>");
            });
            $("#tid").change(function () {
                var tid = $("#tid").val();
                var tname = $("#tid").find("option:selected").text();
                $.ajax({
                    type: "Post",
                    url: "/message/getGid",
                    data: {
                        gid: tid
                    },
                    success: function (result) {
                        var data = result.data[0];
                        if (data.length >= 2) {
                            myChart4.clear();
                            option4 = {
                                title: {
                                    text: tname + '销售情况折线图'
                                },
                                tooltip: {
                                    trigger: 'axis'
                                },
                                xAxis: {
                                    data: data.map(function (item) {
                                        return item['name'];
                                    })
                                },
                                yAxis: {
                                    splitLine: {
                                        show: false
                                    }
                                },
                                toolbox: {
                                    left: 'center',
                                    feature: {
                                        dataZoom: {
                                            yAxisIndex: 'none'
                                        },
                                        dataView: {show: true, readOnly: false},
                                        magicType: {show: true, type: ['line', 'bar']},
                                        restore: {},
                                        saveAsImage: {}
                                    }
                                },
                                dataZoom: [{
                                    startValue: '2014-06-01'
                                }, {
                                    type: 'inside'
                                }],
                                visualMap: {
                                    top: 10,
                                    right: 10,
                                    pieces: [{
                                        gt: 0,
                                        lte: 10,
                                        color: '#096'
                                    }, {
                                        gt: 10,
                                        lte: 20,
                                        color: '#ffde33'
                                    }, {
                                        gt: 20,
                                        lte: 30,
                                        color: '#ff9933'
                                    }, {
                                        gt: 30,
                                        lte: 50,
                                        color: '#cc0033'
                                    }, {
                                        gt: 50,
                                        lte: 100,
                                        color: '#660099'
                                    }, {
                                        gt: 200,
                                        color: '#7e0023'
                                    }],
                                    outOfRange: {
                                        color: '#999'
                                    }
                                },
                                series: {
                                    name: tname + '销售情况',
                                    type: 'line',
                                    data: data.map(function (item) {
                                        return item['value'];
                                    }),
                                    markLine: {
                                        silent: true,
                                        data: [{
                                            yAxis: 50
                                        }, {
                                            yAxis: 100
                                        }, {
                                            yAxis: 150
                                        }, {
                                            yAxis: 200
                                        }, {
                                            yAxis: 300
                                        }]
                                    }
                                }
                            };
                            myChart4.setOption(option4);
                            myChart4.hideLoading();
                        } else {
                            //alert(data.length);
                            alert("该商品销售记录不足，请选择其他商品");
                        }
                    }
                });
            });
        }
    });
});

