<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	  <meta charset="utf-8">
	  <meta name="renderer" content="webkit|ie-comp|ie-stand">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	  <meta http-equiv="Cache-Control" content="no-siteapp" />
	  <!--[if lt IE 9]>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/lib/html5.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/lib/respond.min.js"></script>
	  <![endif]-->
        <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath }/static/login/login.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/login/reset.css" />
	  <title>后台登录 - 威迈支付账单管理</title>
</head>
<body>
	<input type="hidden" id="TenantId" name="TenantId" value="" />

    <div id="particles-js">
        <div class="login">
            <img src="${pageContext.request.contextPath }/static/img/wmLOGO.png" class="logo" />
            <div class="login-top">
                登录
            </div>
            <div class="login-center clearfix">
                <div class="login-center-img"><img src="${pageContext.request.contextPath }/static/img/name.png" /></div>
                <div class="login-center-input">
                    <input type="text" name="adminName" id="adminName" value="admin" placeholder="请输入您的用户名" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户名'" />
                    <div class="login-center-input-text">用户名</div>
                </div>
            </div>
            <div class="login-center clearfix">
                <div class="login-center-img"><img src="${pageContext.request.contextPath }/static/img/password.png" /></div>
                <div class="login-center-input">
                    <input type="password" name="password"  id="password" value="123456" placeholder="请输入您的密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'" />
                    <div class="login-center-input-text">密码</div>
                </div>
            </div>
            <div class="login-button">
                登录
            </div>
            <a href="${pageContext.request.contextPath }/views/agency/login.jsp" style="text-align: center; font-size: 12px; color: #333;display: block;padding-top: 20px">
                代理用户登陆
            </a>
        </div>
        <div class="sk-rotating-plane"></div>

    </div>


	<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
  
  <script type="text/javascript">
    $(function(){

        $(".login-button").click(function(){
            $.post("${pageContext.request.contextPath}/admin/system/doLogin.do",{
                username: adminName.value,
                password: password.value
            }, function(result) {
                console.log(result);
                layer.msg(result.cusMsg);
                if (result.status == 1){
                    window.location.href = "${pageContext.request.contextPath}/admin/system/index.do";
                }
            });

        });
    });
  </script>
</body>
</html>