<%@page import="com.wmpay.bean.WmAdmin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/views/admin/common/header.jsp">
	<jsp:param value="编辑信息" name="title" />
</jsp:include>
</head>
<body style="background-color: #fff">
	<div class="wap-container">
		<div class="panel">
			<div class="panel-body">
				<form action="" method="post" class="form form-horizontal"
					id="form-admin-edit">
					<div class="row clearfix">
						<label class="form-label col-xs-4 col-sm-3"><span
							class="c-red">*</span>用户名：</label>
						<div class="form-controls col-xs-8 col-sm-9">
							<input type="text" class="input-text"
								value="<%=((WmAdmin) request.getAttribute("wmAdmin")).getUsername()%>"
								placeholder="" id="username" name="username" disabled="disabled">
						</div>
					</div>
					<div class="row clearfix">
						<label class="form-label col-xs-4 col-sm-3"><span
							class="c-red">*</span>昵称：</label>
						<div class="form-controls col-xs-8 col-sm-9">
							<input type="text" class="input-text"
								value="<%=((WmAdmin) request.getAttribute("wmAdmin")).getNickname()%>"
								placeholder="" id="nickname" name="nickname">
						</div>
					</div>
					<div class="row clearfix">
						<label class="form-label col-xs-4 col-sm-3"><span
							class="c-red">*</span>初始密码：</label>
						<div class="form-controls col-xs-8 col-sm-9">
							<input type="password" class="input-text" autocomplete="off"
								value="<%=((WmAdmin) request.getAttribute("wmAdmin")).getPassword()%>"
								placeholder="密码" id="password" name="password">
						</div>
					</div>
					<div class="row clearfix">
						<label class="form-label col-xs-4 col-sm-3"><span
							class="c-red">*</span>确认密码：</label>
						<div class="form-controls col-xs-8 col-sm-9">
							<input type="password" class="input-text" autocomplete="off"
								placeholder="确认新密码" id="password2" name="password2"
								value="<%=((WmAdmin) request.getAttribute("wmAdmin")).getPassword()%>">
						</div>
					</div>
					<div class="row clearfix">
						<label class="form-label col-xs-4 col-sm-3"><span
							class="c-red">*</span>角色：</label>
						<div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;"> <select
								class="select" name="adminRole" id="adminRole" size="1">
							</select>
							</span>
						</div>
					</div>
					<div class="row clearfix">
						<label class="form-label col-xs-4 col-sm-3"><span
							class="c-red">*</span>状态：</label>
						<div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;"> <select
								class="select" name="status" id="status" size="1" >
									<option value="1">正常</option>
									<option value="2">禁用</option>
							</select>
							</span>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
							<input class="btn btn-primary radius" type="submit"
								value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/business/js/main.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/static/business/js/common.js"></script>
<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/messages_zh.js"></script>

<script type="text/javascript">
	$(function() {
		var status = ${requestScope.wmAdmin.status};
		var wmAdminId = ${requestScope.wmAdmin.wmAdminId};

		$("#status").val(status);
		$.ajax({
			url : "getAuthGroupList.do",
			type : 'POST',
			data : {},
			success : function(data) {
				var template = "";
				$.each(data.data, function(i, obj) {
					template += "<option value="+obj.wmAuthGroupId+">" + obj.name + "</option>"
				});
				$("#adminRole").html(template);
				$.ajax({
					url: "getAdminRule.do",
					type: 'POST',
					data: {wmAdminId: wmAdminId},
					success: function(result) {
						console.log(result);
						$("#adminRole").val(result.data.wmAuthGroupId);
					}
				});
			}
		});



		$("#form-admin-edit").validate({
			rules : {
				username : {
					required : true,
				},
				nickname : {
					required : true
				},
				password : {
					required : true
				},
				password2 : {
					required : true
				},
				adminRole : {
					required : true
				},
				status : {
					required : true
				}
			},
			onkeyup : false,
			focusCleanup : true,
			success : "valid",
			submitHandler : function(form) {
				var username = $("#username").val();
				var nickname = $("#nickname").val();
				var password = $("#password").val();
				var password2 = $("#password2").val();
				var adminRole = $("#adminRole").val();
				var status = $("#status").val();
				if (password != password2){
					layer.msg("两次输入密码不一致");
					return false;
				}
				// 发送请求
				$.ajax({
					url: "editAdmin.do",
					type: 'POST',
					data: {
						wmAdminId: "<%=((WmAdmin)request.getAttribute("wmAdmin")).getWmAdminId()%>",
						username: username,
						nickname: nickname,
						password: password,
						status: status,
						ruleId: adminRole

					},
					success: function (data) {
						console.log(data);
						layer.msg(data.cusMsg);
					},
					error: function (error){
						console.log(error);
					}
				});
			}
		})
	});
</script>
</html>