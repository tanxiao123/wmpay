<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-13
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="支付配置" name="title"/>
    </jsp:include>
</head>
<body >
<div class="wap-container Hui-admin-content">

    <form class="form form-horizontal">
        <div class="row clearfix">
            <label class="form-label col-xs-4 col-sm-1"><span
                    class="c-red">*</span>当前配置：</label>
            <div class="form-controls col-xs-5 col-sm-10">
               <span class="select-box">
                  <select class="select" size="1" name="defaultConfig" id="defaultConfig">
                    <option value="1">微信支付</option>
                    <option value="2">第三方支付</option>
                  </select>
                </span>
            </div>
            <div class="form-controls col-xs-3 col-sm-1">
                <input class="btn btn-success radius" type="button" value="保存" id="saveDefaultBtn">
            </div>

        </div>
    </form>

    <div class="panel mt-20">
        <div class="panel-header">第三方配置</div>
        <div class="panel-body">
            <form action="" method="post" class="form form-horizontal"
                  id="form-third-pay-edit">
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>第三方网关：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.result.wmThirdPayConfig.apiBaseUri}" placeholder="第三方网关不可为空"
                               id="apiBaseUri" name="apiBaseUri" datatype="*4-16"
                               nullmsg="第三方网关不可为空">
                    </div>
                </div>

                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>应用ID：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.result.wmThirdPayConfig.appId}" placeholder="应用ID不可为空"
                               id="appId" name="appId" datatype="*4-16"
                               nullmsg="应用ID不可为空">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>应用密钥：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.result.wmThirdPayConfig.appKey}" placeholder="应用密钥不可为空"
                               id="appKey" name="appKey" datatype="*4-16"
                               nullmsg="应用密钥不可为空">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>设备ID：</label>
                    <div class="form-controls col-xs-5 col-sm-8">
                        <input type="text" class="input-text" value="${requestScope.result.wmThirdPayConfig.deviceId}" placeholder="设备ID不可为空"
                               id="deviceId" name="deviceId" datatype="*4-16"
                               nullmsg="设备ID不可为空">
                    </div>
                    <div class="form-controls col-xs-3 col-sm-1">
                        <input class="btn btn-secondary radius" type="button" id="makeDeviceBtn" value="生成">
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

    <div class="panel mt-20">
        <div class="panel-header">微信支付</div>
        <div class="panel-body">
            <form action="" method="post" class="form form-horizontal"
                  id="form-wechat-pay-edit" >
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>应用ID：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.result.weChatPayConfig.appId}" placeholder="请填写应用ID"
                               id="wechat_appId" name="wechat_appId" datatype="*4-16"
                               nullmsg="第三方网关不可为空">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>应用密钥：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.result.weChatPayConfig.appKey}" placeholder="请填写应用密钥"
                               id="wechat_appKey" name="wechat_appKey" datatype="*4-16"
                               nullmsg="应用ID不可为空">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>商户ID：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.result.weChatPayConfig.mchId}" placeholder="请填写商户ID"
                               id="wechat_mchId" name="wechat_mchId" datatype="*4-16"
                               nullmsg="应用密钥不可为空">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>商户密钥：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.result.weChatPayConfig.mchKey}" placeholder="请填写商户密钥"
                               id="wechat_mchKey" name="wechat_mchKey" datatype="*4-16"
                               nullmsg="设备ID不可为空">
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                        <button type="submit" class="btn btn-success radius"
                                id="" >
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
<script type="text/javascript" src="${ pageContext.request.contextPath }/static/business/js/common.js?ver=1"></script>
<script>
    $(function () {
        $("#defaultConfig").val("${requestScope.result.defaultPay}");
        $("#form-third-pay-edit").validate({
            rules: {
                schoolName: {
                    required: true
                },
                wm_area_id: {
                    required: true
                }
            },
            onkeyup : false,
            focusCleanup : true,
            success : "valid",
            submitHandler: function (form) {
                var params = {
                    apiBaseUri: $("#apiBaseUri").val(),
                    appId: $("#appId").val(),
                    appKey: $("#appKey").val(),
                    deviceId: $("#deviceId").val(),
                    wmThirdPayConfigId:"${requestScope.result.wmThirdPayConfig.wmThirdPayConfigId}"
                };
                jsonPost("editThirdPayConfig.do",params,function(res){
                    layer.msg(res.cusMsg);
                });
            }
        });


        $("#form-wechat-pay-edit").validate({
            rules: {
                schoolName: {
                    required: true
                },
                wm_area_id: {
                    required: true
                }
            },
            onkeyup : false,
            focusCleanup : true,
            success : "valid",
            submitHandler: function (form) {
                var params = {
                    appId: $("#wechat_appId").val(),
                    appKey: $("#wechat_appKey").val(),
                    mchId: $("#wechat_mchId").val(),
                    mchKey: $("#wechat_mchKey").val(),
                    wmWeChatPayConfigId:"${requestScope.result.weChatPayConfig.wmWeChatPayConfigId}"
                };
                jsonPost("editWechatPayConfig.do",params,function(res) {
                    layer.msg(res.cusMsg);
                });
            }
        });

        $("#makeDeviceBtn").click(function () {
            jsonPost("makeDeviceId.do",{}, function(res){
                console.log(res);
                $("#deviceId").val(res.data);
            })
        });

        $("#saveDefaultBtn").click(function() {
            var defaultValue = $("#defaultConfig").val();
            var params = {
                wmKeyId: "${requestScope.wmKeyId}",
                defaultId: defaultValue
            };

            $.post("editPayDefault.do", params, function(res){
                layer.msg(res.cusMsg);
            });

        });

    });
</script>
</body>
</html>
