<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-10
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="编辑地区" name="title" />
    </jsp:include>
</head>
<body style="background-color: #fff">
<div class="wap-container">
    <div class="panel">
        <div class="panel-body">
            <form action="savePermission.do" method="post" class="form form-horizontal"
                  id="form-admin-role-add">
                <input type="hidden" name="wmAreaId" value="${requestScope.area.wmAreaId}"  />
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>地区名称：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.area.name}" placeholder=""
                               id="name" name="roleName" datatype="*4-16"
                               nullmsg="地区名称不可为空">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>地区Code：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.area.code}" placeholder=""
                               id="code" name="code" datatype="*4-16"
                               nullmsg="地区Code不可为空">
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>上级地区：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
							<span class="select-box" style="width: 150px;"> <select
                                    class="select" name="parent_id" id="parent_id" size="1" >
									<option value="1">正常</option>
									<option value="2">禁用</option>
							</select>
							</span>
                    </div>
                </div>
                <div class="row clearfix">
                    <label class="form-label col-xs-4 col-sm-3"><span
                            class="c-red">*</span>地区等级：</label>
                    <div class="form-controls col-xs-8 col-sm-9">
                        <input type="text" class="input-text" value="${requestScope.area.level}" placeholder=""
                               id="level" name="level" datatype="*4-16"
                               nullmsg="地区等级不可为空">
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
</body>
<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script>
    $(function(){
        $.ajax({
            url: "savePermission.do",
            data: JSON.stringify({start:1, length:999}),
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
    });
</script>
</html>
