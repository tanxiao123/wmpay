<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-13
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/admin/common/header.jsp">
        <jsp:param value="学校列表" name="title"/>
    </jsp:include>

    <style type="text/css">
        .center{
            text-align:center!important;
        }

        .ml-5{
            color:#7A67EE;
        }
    </style>
</head>
<body>
<div class="wap-container">
    <nav class="breadcrumb"
         style="background-color: #fff; padding: 0 24px">
        首页 <span class="c-gray en">/</span> 学校管理 <span class="c-gray en">/</span>
        学校列表 <a class="btn btn-success radius f-r"
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
                               placeholder="学校名称" id="" name="">
                        <button type="submit" class="btn btn-success" id="" name="">
                            <i class="Hui-iconfont">&#xe665;</i> 搜索
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="panel mt-20">
            <div class="panel-body">
                <div class="clearfix">
						<span class="f-l"> <a href="javascript:;"
                                              onclick="openWindowArea('新增学校','addSchoolView.do',[400,300]);"
                                              class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
								添加学校</a>
						</span> <span class="f-r">共有数据：<strong>54</strong> 条
						</span>
                </div>
                <div class="mt-20 clearfix">
                    <table
                            class="table table-border table-bordered table-bg table-hover table-sort"
                            id="dataTable" data-toggle="dataTable">
                        <thead class="text-c">
                            <th width="15%">ID</th>
                            <th width="15%">所在地区</th>
                            <th width="15%">学校名称</th>
                            <th width="15%">状态</th>
                            <th width="15%">创建时间</th>
                            <th width="10%">操作</th>
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
        src="${ pageContext.request.contextPath }/static/business/js/common.js?version=1"></script>

<script>

    $(function () {
        var columns = [
            {
                data: 'wmSchoolId', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'areaName', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'schoolName', sClass: 'center', render: function (data, type, row, meta) {
                    return data == null ? '' : data;
                }
            },
            {
                data: 'status', sClass: 'center'
            },
            {
                data: 'createdTime', sClass: 'center',  render: function (data, type, row, meta) {
                    return data == null ? '' : timeTransitionDate(data);
                }
            },{
                sClass: 'center'
            }
        ];

        var columnDefs = [
            {
                targets: [3],
                render: function (data, type, row) {
                    switch (row.status) {
                        case '1':
                            return '正常';
                        case '2':
                            return '禁用';
                    }
                }
            },
            {
                targets: [5],
                render: function (data, type, row) {
                    return `
                                <a  id="edit" style="text-decoration:none;" class="ml-5" href="javascript:;" title="编辑">
                                    <i class="Hui-iconfont"></i>编辑
                                </a>
                                <a  id="del" style="text-decoration:none;" class="ml-5" href="javascript:;" title="删除">
                                    <i class="Hui-iconfont"></i>删除
                                </a><br />
                                <a   style="text-decoration:none;"
                                    onclick="goWindow('分校区管理','pointSchoolView.do?wmSchoolId=\${row.wmSchoolId}')"
                                    class="ml-5" href="javascript:;" title="分校区管理">
                                    <i class="Hui-iconfont Hui-iconfont-user"></i>分校区管理
                                </a>
                                <a  id="payConfig" style="text-decoration:none;" class="ml-5" href="javascript:;" title="支付配置"
                                    onclick="goWindow('支付配置','${pageContext.request.contextPath}/admin/pay/getEditSysPayView.do?wmKeyId=\${row.wmSchoolId}')">
                                    <i class="Hui-iconfont Hui-iconfont-system"></i>支付配置
                                </a><br />
                                <a  id="addAdditionUser" style="text-decoration:none;" class="ml-5" href="javascript:;" title="添加用户"
                                    onclick="goWindow('添加校区用户','${pageContext.request.contextPath}/admin/system/getAdditionAdminView.do?typeId=1')">
                                    <i class="Hui-iconfont Hui-iconfont-user-add" ></i>添加校区用户
                                </a><br />
                                <a  id="addAdditionUser" style="text-decoration:none;" class="ml-5" href="javascript:;" title="添加用户">
                                    <i class="Hui-iconfont Hui-iconfont-tags"></i>年级管理
                                </a>
                                <a  id="addAdditionUser" style="text-decoration:none;" class="ml-5" href="javascript:;" title="添加用户">
                                    <i class="Hui-iconfont Hui-iconfont-tags"></i>年级管理
                                </a>
					`;

                }
            }
        ];

        // 初始化表格信息
        initMainTable('getSchoolList.do', columns, 20, 1, columnDefs);

        tableCick('#del', function (data, rows) {
            layer.confirm('是否要删除该学校？', function () {
                $.post('delSchool.do', {
                    wmSchoolId: data.wmSchoolId
                }, function (result) {
                    layer.msg(result.cusMsg);
                    tableReload();
                });
            })
        });

        // 编辑信息
        tableCick('#edit', function (data, rows) {
            var index = goWindow('编辑学校', 'editSchoolView.do?wmSchoolId=' + data.wmSchoolId);
            layer.full(index);
        })
    });

</script>

</body>
</html>
