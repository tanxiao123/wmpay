

function initMainTable(url, columns, len, index, columnDefs) {
	var iDisplayLength = 20; // 默认显示条数
	var pStart = 0; // 默认开始条数

	if (typeof (len) !== "undefined" && len != null) {
		iDisplayLength = len;
	}

	if (typeof (index) === "undefined" || index === null) {
		index = 1;
	}

	pStart = parseInt(parseInt(index) - 1) * parseInt(iDisplayLength);

	if (dataTable != null) {
		dataTable.fnClearTable(0);
	}

	dataTable = $("#dataTable").dataTable({
		bStateSave: false,
		bPaginate: true,// 分页按钮
		bFilter: false,// 搜索栏
		bLengthChange: false,// 每行显示记录数
		iDisplayStart: pStart,
		iDisplayLength: iDisplayLength,// 每页显示行数
		bSort: false,// 排序
		bInfo: true,
		bWidth: false,
		ordering: false,
		bScrollCollapse: true,
		sPaginationType: "full_numbers",
		bProcessing: false,
		bServerSide: true,
		bDestroy: true,
		bSortCellsTop: true, 
		aoColumns: columns, // 数据列字断
		serverSide: true, // 开启服务器处理模式
		sAjaxSource: url, // 请求URL地址
		columnDefs: columnDefs, // 自定义操作列
		fnInfoCallback: function (oSettings, iStart, iEnd, iMax, iTotal, sPre) {
			console.log(sPre);
			return sPre;
		},
		fnServerData: function (sSource, aoData, fnCallback) {
			var sendData = {};

			for (var i = 0; i < aoData.length; i++) {
				console.log(aoData[i]);
				var aName = aoData[i].name;
				var nValue = aoData[i].value;

				if (aName == "iDisplayStart")
					sendData.start = nValue;
				if (aName == "iDisplayLength")
					sendData.length = nValue;
			}

			if (sendData.start != null && sendData.start != 0) {
				var oTable = $("#dataTable").dataTable();
				var tableSetings = oTable.fnSettings();
				var paging_length = tableSetings._iDisplayLength;// 当前每页显示多少
				var page_start = tableSetings._iDisplayStart;// 当前页开始
				var page = (page_start / paging_length); // 得到页数值 比页码小1
				sendData.start = page + 1
				console.log(page);
			}
			$.ajax({
				url: sSource,
				type: 'GET',
				data: sendData,
				success: function (data) {
					//if (data.status == 1){
						aDataSet = data.data;
					//}
					fnCallback(data);
				}
			});
		}
	});
}