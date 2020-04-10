<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-10
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="编辑地区" name="title" />
    </jsp:include>
</head>
<body>

</body>
<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script>
    $(function(){
        $.ajax({
            url: "savePermission.do",
            data: JSON.stringify({start:1, length:999}),
            contentType:'application/json;charset=utf-8',
            type: 'post',
            success: function(res){
                layer.msg(res.cusMsg);
                if (res.status == 1){
                    setTimeout(function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }, 1000);
                }
            }
        });
    });
</script>
</html>
