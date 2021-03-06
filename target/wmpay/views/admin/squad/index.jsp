<%@ page import="com.wmpay.common.AdminTypeEnum" %>
<%@ page import="com.wmpay.common.AdminCommon" %>
<%@ page import="com.wmpay.bean.WmAdditionAdmin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="班级管理" name="title" />
    </jsp:include>
</head>
<body>
<div class="wap-container">
    <nav class="breadcrumb"
         style="background-color: #fff; padding: 0 24px">
        首页 <span class="c-gray en">/</span> 学校管理 <span class="c-gray en">/</span>
        班级管理 <a class="btn btn-success radius f-r"
                 style="line-height: 1.6em; margin-top: 3px"
                 href="javascript:location.replace(location.href);" title="刷新"><i
            class="Hui-iconfont">&#xe68f;</i></a>
    </nav>

    <article class="Hui-admin-content clearfix">
        <div class="panel">
            <div class="panel-body">
                <div class="text-c">
                    <form class="Huiform" method="post" action="" target="_self">
                        <input type="text" class="input-text" style="width: 250px"
                               placeholder="管理员名称" id="" name="">
                        <button type="submit" class="btn btn-success" id="" name="">
                            <i class="Hui-iconfont">&#xe665;</i> 搜索
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="panel mt-20">
            <div class="panel-body">
                <div class="clearfix" id="add-suqad-div">
						<span class="f-l"> <a href="javascript:;"
                                              onclick="goWindow('添加班级','addSquadView.do')"
                                              class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
								添加班级</a>
						</span> <span class="f-r">共有数据：<strong>54</strong> 条
						</span>
                </div>
                <div class="mt-20 clearfix">
                    <table
                            class="table table-border table-bordered table-bg table-hover table-sort"
                            id="dataTable" data-toggle="dataTable">
                        <thead>
                        <th>ID</th>
                        <th>班级名称</th>
                        <th>所属年级</th>
                        <th>所属学校</th>
                        <th>状态</th>
                        <th>班主任名称</th>
                        <th>班主任手机号</th>
                        <th>创建时间</th>
                        <th>操作</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </article>
</div>

<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/static/business/js/main.js"></script>
<script type="text/javascript"
        src="${ pageContext.request.contextPath }/lib/datatables/1.10.15/jquery.dataTables.min.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="${ pageContext.request.contextPath }/static/business/js/common.js?version=3"></script>

<script type="text/javascript">
    var confirmTitle = "是否要禁用管理员？";

    var str = "";
    // 加载数据选项
    $.post("${pageContext.request.contextPath}/admin/auth/getGradePayAuth.do", function (res) {
        var data = res.data;
        str = data;
    });

    $(function(){
        // add-suqad-div
        // 设置管理员权限可添加操作
        if ("<%=((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE)).code%>" == "2"){
            if ("<%=((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION)).getType()%>"  == "3"){
                $("#add-suqad-div").hide();
            }

        }
        // 初始化表格信息
        var columns = [
            {data: 'wmSquadId',  sClass: 'center', render:function(data,type,row,meta){
                    return data == null ? '' : data;
                }},
            {data: 'squadName',  sClass: 'center', render:function(data,type,row,meta){
                    return data == null ? '' : data;
            }},
            {data: 'gradeName', sClass: 'center', render:function(data,type,row,meta){
                    return data == null ? '' : data;
            }},
            {data: 'schoolName', sClass: 'center', render:function(data,type,row,meta){
                    return data == null ? '' : data;
            }},
            {data: 'status',  sClass: 'center'},
            {data: 'teacherName',  sClass: 'center', render:function(data,type,row,meta){
                    return data == null ? '' : data;
                }},
            {data: 'teacherMobile', sClass: 'center', render:function(data,type,row,meta){
                    return data == null ? '' : data;
                }},
            {data: 'createdTime', sClass: 'center', render:function(data,type,row,meta){
                    return data == null ? '' : timeTransitionDate(data);
            }}
        ];

        var columnDefs = [
            {
                targets: [4],
                render: function(data, type, row) {
                    switch(row.status){
                        case '1':
                            return "正常";
                        case '2':
                            return "禁用";
                    }
                }
            },
            {
                targets: [8],
                render: function(data, type, row) {
                    console.log(row.status);
                    var statusName = "";
                    switch(row.status){
                        case "1":
                            statusName = "禁用";
                            break;
                        case "2":
                            statusName = "启用";
                            confirmTitle = "是否要启用管理员?";
                            break;
                    }
                    return str;
                }
            }
        ];

        // 初始化表格数据
        initMainTable("getSquadList.do",columns,20,1,columnDefs);

        tableCick("#edit", function(data,rows){
            goWindow("编辑班级","updateSquadView.do?wmSquadId="+data.wmSquadId)
        })


        tableCick('#account', function (data, rows) {
            // 1. 检测是否存在代理账户
            $.post("${pageContext.request.contextPath}/admin/auth/isAddition.do",{
                type: "3",
                userId: data.wmSquadId
            }, function(res){
                var code = res.status;
                switch (code) {
                    case 1:
                        layer.alert("无权限查看账户 请联系学校管理人员进行开通账户");
                        break;
                    case 2:
                        // 已开通  跳转修改界面
                        console.log("已开通  跳转修改界面");
                        goWindow('账户详情', '${pageContext.request.contextPath}/admin/system/editAdditionAdminView.do?typeId=3&userId='+data.wmSquadId);
                        break;
                    case 3:
                        layer.confirm("当前暂未开通账户，是否开通？", function (){
                            // 跳转用户开通界面
                            console.log("跳转用户开通界面");
                            goWindow('新增代理用户', '${pageContext.request.contextPath}/admin/system/getAdditionAdminView.do?typeId=3&userId='+data.wmSquadId)
                        });
                        break;
                }
            });
        });


        tableCick('#payConfig', function(data, rows){
            $.post("${pageContext.request.contextPath}/admin/auth/isAdditionPay.do",{
                type: "3",
                userId: data.wmSquadId
            }, function(res){
                var code = res.status;
                switch (code) {
                    case -12:
                        layer.alert("无权限查看账户 请联系学校管理人员进行开通账户");
                        break;
                    case 5:
                        // 已开通  跳转修改界面
                        console.log("已开通  跳转修改界面");
                        $.post("${pageContext.request.contextPath}/admin/auth/getAdditionAdminByUserId.do?userId="+data.wmSquadId+"&type=3", function(res){
                            console.log(res);
                            goWindow('支付配置', '${pageContext.request.contextPath}/admin/pay/getEditSysPayView.do?wmKeyId='+res.data.wmAdditionAdminId);
                        });
                        break;
                    case 6:
                        var index = layer.confirm("当前暂未开通支付配置，是否开通？", function (){
                            $.post("${pageContext.request.contextPath}/admin/auth/addPayConfig.do?userId="+data.wmSquadId+"&type=3", function(res) {
                                console.log(res);
                                layer.msg(res.cusMsg);
                                layer.close(index);
                            });

                        });
                        break;
                    default:
                        layer.msg('类型错误');
                }
            });
        })



    });

    /**
     * 添加管理员
     */
    function admin_add() {
        var index = layer.open({
            type: 2,
            title: "添加管理员",
            content: "addAdminView.do",
            area: ['800px','600px']
        });
        layer.full(index);
    }
</script>
</body>
</html>