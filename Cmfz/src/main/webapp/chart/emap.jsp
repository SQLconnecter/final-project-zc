<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <head>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
        <script src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
    </head>

        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="width: 600px;height:400px;"></div>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

        // 使用刚指定的配置项和数据显示图表。
        $.ajax({
            url:"${pageContext.request.contextPath}/user/showusers.do",
            type:"post",
            dataType:"JSON",
            success:function (data) {
                myChart.setOption({
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : data.names,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'注册人数',
                            type:'bar',
                            barWidth: '60%',
                            data:data.values
                        }
                    ]

                })
            }
        })
        $('#mini').linkbutton({
            iconCls: 'icon-search'

        });
         $('#mini').bind('click', function(){
            alert('easyui');
        });
        </script>
<a id="mini" href="#">easyui</a>

