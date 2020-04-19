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
        var columns = [];

        var columnsDefs = [];

        initMainTable('getOrderList.do',columns,20,1,columnsDefs);


    })
</script>
</html>
