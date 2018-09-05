﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="../js/echarts.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>
</head>
<body class="easyui-layout">
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        function randomData() {
            return Math.round(Math.random() * 1000);
        }

        option = {
            title: {
                text: '持名法州APP用户分布图',
                subtext: '2017年6月15日 最新数据',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            // 说明
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['男', '女']
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            // 工具箱
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []
                },
                {
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []
                }
            ]
        };
        myChart.setOption(option);

        $(function () {
            $.post("/cmfzms_gaozhy/statistics/distribution1", function (data) {
                console.log(data);
                myChart.setOption({
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '男',
                        data: data
                    }]
                });
            }, "json");

            $.post("/cmfzms_gaozhy/statistics/distribution2", function (data) {
                console.log(data);
                myChart.setOption({
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '女',
                        data: data
                    }]
                });
            }, "json");
        });
    </script>

</body>
</html>