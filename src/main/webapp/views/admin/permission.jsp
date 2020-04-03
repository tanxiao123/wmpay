<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="/views/admin/common/header.jsp">
	<jsp:param value="权限管理" name="title" />
</jsp:include>

<style>
	#dataTable tr {
		text-align: center;
	}
</style>

</head>

<body>
	<div id="app">
		<div class="wap-container">
			<nav class="breadcrumb"
				style="background-color: #fff; padding: 0 24px">
				首页 <span class="c-gray en">/</span> 管理员管理 <span class="c-gray en">/</span>
				权限管理 <a class="btn btn-success radius f-r"
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
									placeholder="权限名称" id="" name="">
								<button type="submit" class="btn btn-success" id="" name="">
									<i class="Hui-iconfont">&#xe665;</i> 搜权限节点
								</button>
							</form>
						</div>
					</div>
				</div>
				<div class="panel mt-20">
					<div class="panel-body">
						<div class="clearfix">
							<span class="f-l"> <a href="javascript:;"
								onclick="datadel()" class="btn btn-danger radius"><i
									class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;"
								onclick="admin_permission_add('添加权限节点','admin-permission-add.html','','310')"
								class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
									添加权限节点</a>
							</span> <span class="f-r">共有数据：<strong>54</strong> 条
							</span>
						</div>
						<div class="mt-20 clearfix">
							<table class="table table-border table-bordered table-bg table-hover table-sort" id="dataTable">
								
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</div>

	<jsp:include page="/views/admin/common/footer.jsp"></jsp:include>

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/lib/datatables/1.10.15/jquery.dataTables.min.js"></script> 
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/static/business/js/main.js"></script>
		
	
	<!--/请在上方写此页面业务相关的脚本-->
	
	<script type="text/javascript">
	
		layui.use(['table'], function (){
			var table = layui.table;
			
			table.render({
				elem: '#dataTable',
				url: 'permissionList.do?page=1&limit=10',
				parseData: function (res){
					console.log(res);
					return {
						"code": res.status,
						"msg": res.cusMsg,
						"count": res.data.total,
						"data": res.data.records
					}
				},
				cols: [[
					{field: 'wmAuthGroupId', title: 'ID'},
					{field: 'name', title: '权限名称'},
					{field: 'createdTime', title: '创建时间'},
					{field: 'updatedTime', title: '修改时间'}
				]]
			}); 
			
			// 全选 页面调用
			$(".table").HuicheckAll({
				checkboxAll : 'thead input[type="checkbox"]',
				checkbox : 'tbody input[type="checkbox"]'
			}, function(checkedInfo) {
				console.log(checkedInfo);
			})
		});
	</script>
</body>

</html>