<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-14
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="学校列表" name="title"/>
    </jsp:include>
</head>
<body>
<div class="wap-container">
    <div class="panel">
        <div class="panel-body">
            <form action="" method="post" class="form form-horizontal"
                  id="form-grade-add">
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>学校：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;">
                                <select
                                        class="select" name="wmSchoolId" id="wmSchoolId" size="1" >
							</select>
							</span>
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>年级名称：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="" placeholder="请输入年级名称"
                               id="wmGradeName" name="wmGradeName" datatype="*4-16"
                               nullmsg="年级名称不可为空">
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
                                id="grade-save" >
                            <i class="icon-ok"></i> 确定
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/static/business/js/common.js?version=3"></script>
<script>
    $(function () {

        var schoolStr = getSchool('${pageContext.request.contextPath }/admin/school/getSchoolListNoPage.do');
        $("#wmSchoolId").html(schoolStr);
        // 点击编辑触发事件
        $("#form-grade-add").validate({
            rules:{
                wmSchoolId:{
                    required:true,
                },
                wmGradeName: {
                    required:true
                },
                status:{
                    required:true
                }
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                var params = {
                    wmSchoolId: $("#wmSchoolId").val(),
                    name: $("#wmGradeName").val(),
                    status: $("#status").val()
                };
                jsonPost("addGrade.do",params,function (res) {
                    layer.msg(res.cusMsg);
                    if (res.status == 1){
                        setTimeout(function(){
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }, 1000);
                    }
                });
            }
        });
    })
</script>
</html>
