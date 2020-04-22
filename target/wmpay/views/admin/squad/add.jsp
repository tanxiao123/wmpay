<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-22
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="添加班级" name="title" />
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
                            class="c-red">*</span>学校：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;">
                                <select
                                    class="select" name="wmSchoolId" id="wmSchoolId" size="1" >
                                <option value="">请选择学校</option>
							</select>
							</span>
                    </div>
                </div>

                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>年级：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;">
                                <select
                                        class="select" name="wmGradeId" id="wmGradeId" size="1" >
                                    <option value="">请选择年级</option>
							</select>
							</span>
                    </div>
                </div>

                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>班级名称：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text"
                               value=""
                               placeholder="" id="name" name="name" >
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>班主任姓名：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text"
                               value=""
                               placeholder="" id="teacherName" name="teacherName">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>班主任电话：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" autocomplete="off"
                               value=""
                               placeholder="班主任电话" id="teacherMobile" name="teacherMobile">
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
        src="${ pageContext.request.contextPath }/static/business/js/common.js?version=3"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/messages_zh.js"></script>

<script type="text/javascript">
    $(function() {

        // 加载学校
        var schoolStr = getSchool('${pageContext.request.contextPath }/admin/school/getSchoolListNoPage.do');

        $("#wmSchoolId").append(schoolStr);

        $("#wmSchoolId").change(function(){
            // 动态加载年级
            $.post("${pageContext.request.contextPath }/admin/grade/getGradeBySchoolId.do", {
                wmSchoolId: parseInt($("#wmSchoolId").val())
            }, function(result){
                console.log(result)
                var data = result.data;
                var str = "<option value=''>请选择年级</option>";
                if (data.length > 0){
                    str = "";
                    for(var i = 0; i< data.length; i++){
                        str += "<option value='"+data[i].wmGradeId+"'>"+data[i].name+"</option>";
                    }
                }else{

                }

                $("#wmGradeId").html(str);
            });
        });


        $("#form-admin-edit").validate({
            rules : {
            },
            onkeyup : false,
            focusCleanup : true,
            success : "valid",
            submitHandler : function(form) {
                var status = $("#status").val();
                // 发送请求
                $.ajax({
                    url: "addSquad.do",
                    type: 'POST',
                    data: {
                        name: $("#name").val(),
                        teacherName: $("#teacherName").val(),
                        teacherMobile: $("#teacherMobile").val(),
                        status: status,

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
