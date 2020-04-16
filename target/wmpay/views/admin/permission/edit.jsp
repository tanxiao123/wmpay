<%@page import="com.wmpay.bean.WmAuthGroup"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/views/admin/common/header.jsp">
	<jsp:param value="编辑权限" name="title" />
</jsp:include>
</head>

<body style="background-color: #fff">
	<div class="wap-container">
		<div class="panel">
			<div class="panel-body">
				<form action="savePermission.do" method="post" class="form form-horizontal"
					id="form-admin-role-add">
					<input type="hidden" name="id" value="<%=((WmAuthGroup)request.getAttribute("group")).getWmAuthGroupId() %>"  />
					<div class="row clearfix">
						<label class="form-label col-xs-4 col-sm-3"><span
							class="c-red">*</span>角色名称：</label>
						<div class="form-controls col-xs-8 col-sm-9">
							<input type="text" class="input-text" value="<%=((WmAuthGroup)request.getAttribute("group")).getName()%>" placeholder=""
								id="roleName" name="roleName" datatype="*4-16"
								nullmsg="用户账户不能为空">
						</div>
					</div>
					<div class="row clearfix">
						<label class="form-label col-xs-4 col-sm-3">网站角色：</label>
						<div class="form-controls col-xs-8 col-sm-9">
							<%=request.getAttribute("permission") %>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
							<button type="submit" class="btn btn-success radius"
								id="admin-role-save" >
								<i class="icon-ok"></i> 确定
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$(".Hui-admin-permission-list dt input:checkbox").click(function(){
				$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
			});
			
			$(".Hui-admin-permission-list2 dd input:checkbox").click(function(){
				var l =$(this).parent().parent().find("input:checked").length;
				var l2=$(this).parents(".Hui-admin-permission-list").find(".Hui-admin-permission-list2 dd").find("input:checked").length;
				if($(this).prop("checked")){
					$(this).closest("dl").find("dt input:checkbox").prop("checked",true);
					$(this).parents(".Hui-admin-permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
				}
				else{
					if(l==0){
						$(this).closest("dl").find("dt input:checkbox").prop("checked",false);
					}
					if(l2==0){
						$(this).parents(".Hui-admin-permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
					}
				}
			});
			
			// 点击编辑触发事件
			$("#form-admin-role-add").validate({
				rules:{
					roleName:{
						required:true,
					},
				},
				onkeyup:false,
				focusCleanup:true,
				success:"valid",
				submitHandler:function(form){
					var groupCheck = [];
					$("input[name='groupCheck[]']:checked").each(function(){
						console.log($(this));
						groupCheck.push($(this).attr("data-group") );
					});
					var params = {
						id: $("input[name='id']").val(),
						roleName: $("#roleName").val(),
						groupCheck: groupCheck
					};
					$.ajax({
						url: "savePermission.do",
						data: JSON.stringify(params),
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
					
				}
			});
		})
	</script>
	
</body>
</html>