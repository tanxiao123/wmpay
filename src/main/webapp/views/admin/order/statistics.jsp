<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-23
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="订单统计" name="title"/>
    </jsp:include>
</head>
<body>
<div class="wap-container">
    <nav class="breadcrumb" style="background-color:#fff;padding: 0 24px">
        首页
        <span class="c-gray en">/</span>
        订单统计
        <a class="btn btn-success radius f-r" style="line-height:1.6em;margin-top:3px"
           href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
        <article class="Hui-admin-content clearfix">
            <div class="panel">
                <div class="panel-body">
                    <div class="row clearfix">
                        <div class="col-xs-12">
                            <div class="panel">
                                <div class="panel-header">
                                    订单概况
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-12 Hui-admin-datatime-tag"
                                             style="text-align:right;padding-bottom: 20px;">
                                            <span class="active">今天</span>
                                            <span>7天</span>
                                            <span>30天</span>
                                            <span>1年</span>
                                            <span>全部</span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <div id="sumOrder" style="height:170px"></div>
                                            <div class="text-c f-14 c-666">总下单量</div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="turnover" style="height:170px"></div>
                                            <div class="text-c f-14 c-666">成交量</div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="noTurnover" style="height:170px"></div>
                                            <div class="text-c f-14 c-666">未成交量</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <article>


                <article class="Hui-admin-content clearfix">
                    <div class="panel">
                        <div class="panel-body">
                            <div class="row clearfix">
                                <div class="col-xs-12">
                                    <div class="panel">
                                        <div class="panel-header">
                                            订单金额统计
                                        </div>
                                        <div class="panel-body">
                                            <div id="totalFee-echarts" style="height: 400px"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </article>

                <article class="Hui-admin-content clearfix">
                    <div class="panel">
                        <div class="panel-body">
                            <div class="row clearfix">
                                <div class="col-xs-12">
                                    <div class="panel">
                                        <div class="panel-header">
                                            销售数量统计
                                        </div>
                                        <div class="panel-body">
                                            <div id="number-echarts" style="height: 400px"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </article>
</div>

</body>

<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript"
        src="${ pageContext.request.contextPath }/static/business/js/common.js?ver=4.0"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/echarts/4.1.0.rc2/echarts.min.js"></script>
<script>

    function initCake(sumOrderNumber, turnoverNumber, noTurnoverNumber) {
        // 设置总下单量
        var sumOrder = echarts.init(document.getElementById("sumOrder"));
        var orderOption = getEchartsCakeOption(sumOrderNumber, ['#1890ff', '#eeeeee']);
        sumOrder.setOption(orderOption);


        // 设置成交量
        var turnover = echarts.init(document.getElementById("turnover"));
        var turnoverOption = getEchartsCakeOption(turnoverNumber, ['#61be67', '#eeeeee']);
        turnover.setOption(turnoverOption);

        // 设置未成交量
        var noTurnover = echarts.init(document.getElementById("noTurnover"));
        var noTurnoverOption = getEchartsCakeOption(noTurnoverNumber, ['#E74C3C', '#eeeeee']);
        noTurnover.setOption(noTurnoverOption)
    }


    $(function () {
        // 加载饼状图接口
        $.post("getCakeStatistics.do", function(res){
            var data = res.data;
            initCake(data.countOrder, data.successOrder, data.failOrder);
        });
        // 加载线状图
        $.post("getStatisticsData.do",function(res){
            var dayNumberStatistics = res.data;
            var createTime = [], data1 = [], data2 = [];

            for(var i = 0; i< dayNumberStatistics.length; i++ ) {
                createTime.push(dayNumberStatistics[i].createTime);
                data1.push(dayNumberStatistics[i].number);
                data2.push(dayNumberStatistics[i].totalFee);
            }

            var echarts1 = echarts.init(document.getElementById("totalFee-echarts"));
            var wireOption = initEchartsWireOption(['订单金额'], createTime, {
                name: '订单金额',
                type: 'line',
                symbolSize:10,
                symbol:'circle',
                itemStyle: {
                    color: '#1bb495',
                    bborderColor: '#fff',
                    borderWidth: 2,
                },
                data: data2,
            });
            echarts1.setOption(wireOption);

            var echarts2 = echarts.init(document.getElementById("number-echarts") );
            var wireOption2 = initEchartsWireOption(['订单数量'], createTime, {
                name: '订单数量',
                type: 'line',
                symbolSize:10,
                symbol:'circle',
                itemStyle: {
                    color: '#3bb4f2',
                    bborderColor: '#fff',
                    borderWidth: 2,
                },
                data: data1,
            })
            echarts2.setOption(wireOption2);
        });

    })
</script>
</html>
