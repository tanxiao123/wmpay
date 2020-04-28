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
        <jsp:param value="编辑学校" name="title"/>
    </jsp:include>
</head>
<body style="background-color: #fff">
<div class="wap-container">
    <div class="panel">
        <div class="panel-body">
            <form action="" method="post" class="form form-horizontal"
                  id="form-area-edit">
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>学校名称：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.school.name}" placeholder="请输入学校名称"
                               id="schoolName" name="schoolName" datatype="*4-16"
                               nullmsg="学校名称不可为空">
                    </div>
                </div>

                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>地区：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;">
                                <select
                                        class="select" name="wm_area_id" id="wm_area_id" size="1" >
							</select>
							</span>
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
    $(function () {
        var areaArray = getArea('/wmpay/admin/area/getAreaList.do');
        var selectHTML = "";
        for(var i=0; i<areaArray.length; i++){
            selectHTML += areaArray[i]
        }
        $("#wm_area_id").html(selectHTML);

        $("#status").val("${requestScope.area.status}");
        $("#wm_area_id").val("${requestScope.area.wmAreaId}");

        $("#form-area-edit").validate({
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
                    name: $("#schoolName").val(),
                    wmAreaId: $("#wm_area_id").val(),
                    parentId: 0,
                    status: $("#status").val(),
                    wmSchoolId: "${requestScope.school.wmSchoolId}"
                };
                jsonPost("editSchool.do",params,function(res){
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
    });
</script>
</body>
</html>
