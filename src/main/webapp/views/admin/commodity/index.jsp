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
        <jsp:param value="商品列表" name="title"/>
    </jsp:include>
</head>
<body>
    <div class="wap-container">
        <nav class="breadcrumb"
             style="background-color: #fff; padding: 0 24px">
            首页 <span class="c-gray en">/</span> 商品管理 <span class="c-gray en">/</span>
            商品列表 <a class="btn btn-success radius f-r"
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
                                   placeholder="商品名称" id="" name="">
                            <button type="submit" class="btn btn-success" id="" name="">
                                <i class="Hui-iconfont">&#xe665;</i> 搜索
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="panel mt-20">
                <div class="panel-body">

                    <div class="clearfix" id="add-admin-div" style="display: none;">
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
                            <th width="15%">商品标题</th>
                            <th width="15%">商品描述</th>
                            <th width="15%">价格</th>
                            <th width="15%">当前状态</th>
                            <td width="15%">创建时间</td>
                            <td width="15%">修改时间</td>
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
        $(function (){
            var columns = [
                {
                    data: 'wmCommodityId', sClass: 'center', render: function (data, type, row, meta) {
                        return data == null ? '' : data;
                    }
                },
                {
                    data: 'title', sClass: 'center', render: function (data, type, row, meta) {
                        return data == null ? '' : data;
                    }
                },
                {
                    data: 'description', sClass: 'center', render: function (data, type, row, meta) {
                        return data == null ? '' : data;
                    }
                },
                {
                    data: 'total', sClass: 'center', render: function (data, type, row, meta) {
                        return data == null ? '' : data * 100;
                    }
                },
                {
                    data: 'status', sClass: 'center'
                },{
                    data: 'createdTime', sClass: 'center', render: function (data, type, row, meta) {
                        return data == null ? '': timeTransitionDate(data)
                    }
                },{
                    data: 'updatedTime', sClass: 'center', render: function (data, type, row, meta) {
                        return data == null ? '': timeTransitionDate(data)
                    }
                }
            ];

            var columnDefs = [
                {
                    targets: [4],
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
                    targets: [7],
                    render: function (data, type, row) {
                        return `
                                <a  id="edit" style="text-decoration:none;" class="ml-5" href="javascript:;" title="编辑">
                                    <i class="Hui-iconfont"></i>编辑
                                </a>
                                <a  id="del" style="text-decoration:none;" class="ml-5" href="javascript:;" title="删除">
                                    <i class="Hui-iconfont"></i>删除
                                </a><br />
					`;

                    }
                }
            ];

            // 初始化表格信息
            initMainTable('getCommodityList.do', columns, 20, 1, columnDefs);
        })
    </script>

</body>
</html>
