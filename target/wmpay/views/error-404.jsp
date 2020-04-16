<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/views/admin/common/header.jsp">
	<jsp:param value="找不到页面" name="title" />
</jsp:include>
</head>
<body>
<div class="page-error text-c">
		<p class="error-title"><i class="Hui-iconfont va-m">&#xe688;</i>
			<span class="va-m"> 404</span>
		</p>
		<p class="error-description">不好意思，您访问的页面不存在~</p>
<!-- 		<p class="error-info">您可以：
			<a href="javascript:;" onclick="history.go(-1)" class="c-primary">&lt; 返回上一页</a>
			<span class="ml-20">|</span>
			<a href="/" class="c-primary ml-20">去首页 &gt;</a>
		</p> -->
	</div>
</body>
</html>