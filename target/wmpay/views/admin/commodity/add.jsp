<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-27
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="添加商品" name="title"/>
    </jsp:include>
</head>
<body>
<div class="wap-container">
    <div class="panel">
        <div class="panel-body">
            <form action="" method="post" class="form form-horizontal"
                  id="form-commodity-edit">
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>商品标题：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="" placeholder="请输入商品标题"
                               id="title" name="title" datatype="*4-16"
                               nullmsg="请输入商品标题">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>商品描述：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="" placeholder="请输入商品描述"
                               id="description" name="description" datatype="*4-16"
                               nullmsg="请输入商品描述">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>商品金额：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="number" class="input-text" value="" placeholder="请输入商品金额"
                               id="total" name="total" datatype="*4-16"
                               nullmsg="请输入商品金额">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>状态：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;">
                                <select
                                        class="select" name="status" id="status" size="1" >
                                    <option value="1">正常</option>
                                    <option value="2">禁用</option>
							</select>
							</span>
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
<script type="text/javascript" src="${ pageContext.request.contextPath }/static/business/js/common.js?ver=1"></script>
<script>
    $(function(){
        $("#form-commodity-edit").validate({
            rules: {
                title: {
                    required: true
                },
                description: {
                    required: true
                },
                total: {
                    required: true
                },
                status: {
                    required: true
                }
            },
            onkeyup : false,
            focusCleanup : true,
            success : "valid",
            submitHandler: function (form) {
                console.log(form);
                $.post("addCommodity.do",{
                    title: title.value,
                    description: description.value,
                    total: total.value,
                    status: status.value
                }, function (result) {
                    console.log(result);
                    layer.msg(result.cusMsg);
                });
            }
        })
    });
</script>

</body>
</html>
