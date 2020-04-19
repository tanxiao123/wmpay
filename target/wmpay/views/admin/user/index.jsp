<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/views/admin/common/header.jsp">
	<jsp:param value="管理员列表" name="title" />
</jsp:include>
</head>
<body>
	<div class="wap-container">
		<nav class="breadcrumb"
			style="background-color: #fff; padding: 0 24px">
			首页 <span class="c-gray en">/</span> 管理员管理 <span class="c-gray en">/</span>
			管理员列表 <a class="btn btn-success radius f-r"
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
					<div class="clearfix">
						<span class="f-l"> <a href="javascript:;"
							onclick="admin_add()"
							class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
								添加管理员</a>
						</span> <span class="f-r">共有数据：<strong>54</strong> 条
						</span>
					</div>
					<div class="mt-20 clearfix">
						<table
							class="table table-border table-bordered table-bg table-hover table-sort"
							id="dataTable" data-toggle="dataTable">
							<thead>
								<th>ID</th>
								<th>账号</th>
								<th>昵称</th>
								<th>状态</th>
								<th>最后一次登陆时间</th>
								<th>最后一次登陆IP</th>
								<th>创建时间</th>
								<th>修改时间</th>
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
		src="${ pageContext.request.contextPath }/static/business/js/common.js"></script>

	<script type="text/javascript">
		var confirmTitle = "是否要禁用管理员？";
		$(function(){
			// 初始化表格信息
			var columns = [
				{data: 'wmAdminId',  sClass: 'center', render:function(data,type,row,meta){
					return data == null ? '' : data;
				}},
				{data: 'username',  sClass: 'center', render:function(data,type,row,meta){
					return data == null ? '' : data;
				}},
				{data: 'nickname',  sClass: 'center', render:function(data,type,row,meta){
					return data == null ? '' : data;
				} },
				{data: 'status',  sClass: 'center'},
				{data: 'logintime',  sClass: 'center', render:function(data,type,row,meta){
					return data == null ? '' : data;
				}},
				{data: 'loginip', sClass: 'center', render:function(data,type,row,meta){
					return data == null ? '' : data;
				}},
				{data: 'createdTime', sClass: 'center', render:function(data,type,row,meta){
					return data == null ? '' : data;
				}},
				{data: 'updatedTime', sClass: 'center', render:function(data,type,row,meta){
					return data == null ? '' : data;
				}}
			];
			
			var columnDefs = [
				{
					targets: [3],
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
						console.log(statusName);
						return `
							<a style="text-decoration:none;margin:0 5px;" id="editInfo" href="javascript:;" title="编辑">编辑信息</a>
							<a style="text-decoration:none;margin:0 5px;" id="forbiddenAdmin" href="javascript:;" title="\${statusName}">\${statusName}</a>
							<a style="text-decoration:none;margin:0 5px;" id="deleteAdmin" href="javascript:;" title="删除">\删除</a>
						`;
					}
				}
			];
			
			// 初始化表格数据
			initMainTable("adminList.do",columns,20,1,columnDefs);
			
			// 编辑信息
			tableCick("#editInfo", function(data,rows){
				console.log(data);
				var index = layer.open({
					type: 2,
					title: "编辑信息",
					content: "editAdminView.do?wmAdminId="+data.wmAdminId,
					area: ['800px','600px']
				});
				layer.full(index);
			});
			
			// 启用禁用用户
			tableCick("#forbiddenUser", function(data,rows){
				var index = layer.confirm(confirmTitle, function(){
					$.ajax({
						url: "forbiddenAdmin.do",
						type: 'POST',
						data: {wmAdminId: data.wmAdminId},
						success: function (data) {
							console.log(data);
							layer.msg(data.cusMsg);
							tableReload();
						},
						error: function (error){
							console.log(error);
						}
					});
					layer.close(index);
				});
			});

			tableCick("#deleteAdmin", function(data,rows){
				var index = layer.confirm("是否要删除该管理员？", function () {
					$.ajax({
						url: "deleteAdmin.do",
						type: 'POST',
						data: {wmAdminId: data.wmAdminId},
						success: function (data) {
							console.log(data);
							layer.msg(data.cusMsg);
							tableReload();
						},
						error: function (error){
							console.log(error);
						}
					});
				});
			});
			

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