<%--
  Created by IntelliJ IDEA.
  User: tanxiao
  Date: 2020/4/19
  Time: 12:13 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="订单列表" name="title"/>
    </jsp:include>
</head>
<body>
<div class="wap-container">
    <nav class="breadcrumb"
         style="background-color: #fff; padding: 0 24px">
        首页 <span class="c-gray en">/</span> 订单管理 <span class="c-gray en">/</span>
        订单列表 <a class="btn btn-success radius f-r"
                style="line-height: 1.6em; margin-top: 3px"
                href="javascript:location.replace(location.href);" title="刷新"><i
            class="Hui-iconfont">&#xe68f;</i></a>
    </nav>

    <article class="Hui-admin-content clearfix">
        <div class="panel">
            <div class="panel-body">
                <div class="text-c">
                    <form class="Huiform" method="post" action="" target="_self">
                        <input type="text" class="input-text" style="width: 250px"
                               placeholder="年级名称" id="" name="">
                        <button type="submit" class="btn btn-success" id="" name="">
                            <i class="Hui-iconfont">&#xe665;</i> 搜索
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="panel mt-20">
            <div class="panel-body">
                <div class="mt-20 clearfix">
                    <table
                            class="table table-border table-bordered table-bg table-hover table-sort"
                            id="dataTable" data-toggle="dataTable">
                        <thead>
                        <th>订单ID</th>
                        <th>下单人名称</th>
                        <th>支付类型</th>
                        <th>商户订单号</th>
                        <th>平台订单号</th>
                        <th>商品描述</th>
                        <th>下单金额</th>
                        <th>终端IP</th>
                        <th>创建时间</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </article>
</div>
</body>
<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/static/business/js/main.js"></script>
<script type="text/javascript"
        src="${ pageContext.request.contextPath }/lib/datatables/1.10.15/jquery.dataTables.min.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="${ pageContext.request.contextPath }/static/business/js/common.js?ver=1.1"></script>

<script>
    $(function () {
        var columns = [
            {
                data: 'wmOrderId', sClass: 'center', render: function(data,type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'wmPatriarchName', sClass: 'center', render: function (data,type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'payTypeName', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'outTradeNo', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'transactionId', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'body', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'totalFee', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'spbillCreateIp', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'createdTime', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            }
        ];

        var columnsDefs = [];

        initMainTable('getOrderList.do',columns,20,1,columnsDefs);


    })
</script>
</html>
