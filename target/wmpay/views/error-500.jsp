<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/views/admin/common/header.jsp">
	<jsp:param value="出错了" name="title" />
</jsp:include>
</head>
<body style="background-color:#fff"> 
	<div class="page-error text-c" >
		<p class="error-title"><i class="Hui-iconfont va-m">&#xe65b;</i>
			<span class="va-m"> 500</span>
		</p>
		<p class="error-description"><%=request.getAttribute("errorMessage") %></p>
		<p class="error-info">您可以：
			<a href="javascript:;" onclick="history.go(-1)" class="c-primary">&lt; 返回上一页</a>
			<span class="ml-20">|</span>
			<a href="/" class="c-primary ml-20">去首页 &gt;</a>
		</p>
	</div>
</body>
</html>