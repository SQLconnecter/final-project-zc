<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <script type="text/javascript" src="../js/echarts.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>


    </script>


    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        /*对消息的订阅*/
        var goEasy = new GoEasy({
            appkey: "BS-bce3079722ee4d8898cbf9af6a995754"
        });
        goEasy.subscribe({
            channel: "man",
            onMessage: function (message) {
                alert("Channel:" + message.channel + " content:" + message.content);
            }
        });

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
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
            $.post("${pageContext.request.contextPath}/user/showapp.do", function (data) {
                myChart.setOption({
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '男',
                        data: data.man
                    },
                    {
                        // 根据名字对应到相应的系列
                        name: '女',
                        data: data.woman
                    }]
                });
            }, "json")

        });
    </script>

