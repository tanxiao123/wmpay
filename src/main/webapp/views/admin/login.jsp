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
	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/h-ui/css/H-ui.min.css" />
	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/h-ui.admin.pro.iframe/css/H-ui.login.min.css" />
	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/lib/Hui-iconfont/1.0.9/iconfont.css" />
	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/business/css/style.css" />
	  <!--[if IE 6]>
	  <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	  <script>DD_belatedPNG.fix('*');</script><![endif]-->
	  <title>后台登录 - 威迈支付账单管理</title>
	  <meta name="keywords" content="h-ui.admin.pro v1.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
	  <meta name="description" content="h-ui.admin.pro v1.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<input type="hidden" id="TenantId" name="TenantId" value="" />
  <!-- <div class="header"></div> -->
  <div class="loginWraper">
    <div class="loginBox">
      <form id="form-admin-login" class="form form-horizontal" action="" method="post">
        <div class="row clearfix">
          <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
          <div class="form-controls col-xs-7">
            <input id="adminName" name="adminName" type="text" placeholder="账户" class="input-text size-L">
          </div>
        </div>
        <div class="row clearfix">
          <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
          <div class="form-controls col-xs-7">
            <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
          </div>
        </div>
        <!-- <div class="row clearfix">
          <div class="form-controls col-xs-7 col-xs-offset-3">
            <input id="verificationCode" name="verificationCode" class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="" style="width:150px;">
            <img src="images/VerifyCode.aspx.png" />
            <a id="kanbuq" href="javascript:;">看不清，换一张</a>
          </div>
        </div> -->
        <!-- <div class="row clearfix">
          <div class="form-controls col-xs-7 col-xs-offset-3">
            <label for="online">
              <input type="checkbox" name="online" id="online" value="">
          使我保持登录状态
            </label>
          </div>
        </div> -->
        <div class="row clearfix">
          <div class="form-controls col-xs-8 col-xs-offset-3">
            <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
            <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="footer">Copyright 威迈智能科技有限公司 by 威迈支付 v1.0</div>

	<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
  
  <script type="text/javascript">
    $(function(){
      /* 表单验证，提交 */
      $("#form-admin-login").validate({
        rules: {
          adminName: {
            required:true,
          },
          password: {
            required:true,
            rangelength: [6, 16]
          },
          verificationCode: {
            required:true,
          }
        },
        messages: {
          adminName: {
            required: "账户不能为空"
          },
          adminName: {
            required: "密码不能为空",
            rangelength: "密码长度6到16个字符"
          },
        },
        onkeyup: false,
        focusCleanup: false,
        submitHandler: function(form) {
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
        }
      });
    });
  </script>
</body>
</html>