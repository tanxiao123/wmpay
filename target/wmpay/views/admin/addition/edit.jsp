<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-26
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="修改代理用户" name="title"/>
    </jsp:include>
</head>
<body>
<div class="wap-container">
    <div class="panel">
        <div class="panel-body">
            <form action="" method="post" class="form form-horizontal"
                  id="form-agency-admin-edit">
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>用户名：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text"
                               value="${requestScope.admin.username}"
                               placeholder="" id="username" name="username" >
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>昵称：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text"
                               value="${requestScope.admin.nickname}"
                               placeholder="" id="nickname" name="nickname">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>初始密码：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="password" class="input-text" autocomplete="off"
                               value="${requestScope.admin.password}"
                               placeholder="密码" id="password" name="password">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>确认密码：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="password" class="input-text" autocomplete="off"
                               placeholder="确认新密码" id="password2" name="password2"
                               value="${requestScope.admin.password}">
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
                                    class="select" name="status" id="status" size="1">
									<option value="1">正常</option>
									<option value="2">禁用</option>
							</select>
							</span>
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>状态：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;"> <select
                                    class="select" name="isDefaultPay" id="isDefaultPay" size="1">
									<option value="0">未开通</option>

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
<script type="text/javascript"
        src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script>
    $(function(){
        // 初始化权限
        $.ajax({
            url : "getAuthGroupList.do",
            type : 'POST',
            data : {},
            success : function(data) {
                var template = "";
                $.each(data.data, function(i, obj) {
                    template += "<option value="+obj.wmAuthGroupId+">" + obj.name + "</option>"
                });
                console.log("管理员权限为${requestScope.groupId}");
                $("#adminRole").html(template);
                $("#adminRole").val("${requestScope.groupId}");
            }
        });

        var payTemplate = `
            <option value="1">微信官方</option>
            <option value="2">第三方配置</option>
        `

        isDefaultPay.value = "${requestScope.admin.isDefaultPay}";
        if (isDefaultPay.value != '0'){
            isDefaultPay.innerHTML +=  payTemplate;
        }

        $("#form-agency-admin-edit").validate({
            onkeyup : false,
            focusCleanup : true,
            success : "valid",
            submitHandler : function(form) {
                if (password.value != password2.value){
                    layer.msg("两次输入密码不一致");
                    return false;
                }
                // 发送请求
                $.ajax({
                    url: "editAdditionAdmin.do",
                    type: 'POST',
                    data: {
                        wmAdditionAdminId: "${requestScope.admin.wmAdditionAdminId}",
                        username: username.value,
                        nickname: nickname.value,
                        password: password.value,
                        status: status.value,
                        ruleId: adminRole.value

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
        });
    })
</script>
</html>
