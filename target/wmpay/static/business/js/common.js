var dataTable, aDataSet;

/**
 * 表格初始化事件
 * @param url 请求地址
 * @param columns 字段列
 * @param len 每页显示多少条
 * @param index 从第几页开始
 * @param columnDefs 需要格式化的字段列
 * @returns
 */
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
			return sPre;
		},
		fnServerData: function (sSource, aoData, fnCallback) {
			var sendData = {};

			for (var i = 0; i < aoData.length; i++) {
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
			}
			$.ajax({
				url: sSource,
				type: 'POST',
				contentType:'application/json;charset=utf-8',
				data: JSON.stringify(sendData),
				success: function (data) {
					aDataSet = data.data;
					fnCallback(data);
				}
			});
		}
	})
}

/**
 * 根据操作栏ID进行操作
 * @param idName
 * @param callback
 * @returns
 */
function tableCick(idName,callback) {
	$("#dataTable tbody").on('click',idName, function (){
		var data = $("#dataTable").DataTable().row($(this).parent()).data(); 
		callback(data, $(this).parent());
	})
}

/**
 * 刷新表格
 * @returns
 */
function tableReload() {
	$("#dataTable").dataTable().fnDraw(false);
}

/**
 * 默认表格Render
 * @param data
 * @param type
 * @param row
 * @param meta
 * @returns {*}
 */
function tableRender(data,type,row,meta) {
	return data == null ? '' : data;
}

/**
 * 发送POST请求数据  该方法针对于@RequestBody注解
 * @param url
 * @param data
 * @param callBack
 */
function jsonPost(url,data,callBack) {
	$.ajax({
		url: url,
		type: 'POST',
		contentType:'application/json;charset=utf-8',
		data: JSON.stringify(data),
		success: function (data) {
			callBack(data);
		},
		error: function (error){
			console.log(error);
		}
	});
}

/**
 * 打开一个新的窗口
 * @param title
 * @param url
 */
function openWindow(title,url) {
	return layer.open({
		type: 2,
		title: title,
		content: url,
		area: ['800px', '600px']
	});
}

function openWindowArea(title, url, area) {
	return layer.open({
		type: 2,
		title: title,
		content: url,
		area: area
	})
}

/**
 * 打开一个新的窗口  并将该窗口最大化显示
 * @param title
 * @param url
 */
function goWindow(title, url) {
	var index = layer.open({
		type: 2,
		title: title,
		content: url,
		area: ['800px', '600px']
	});
	layer.full(index);
	return index;
}


function timeTransitionDate(time) {
	var timestamp = new Date(time);
	return timestamp.toLocaleDateString().replace(/\//g, "-") + " " + timestamp.toTimeString().substr(0, 8)
}


function times(str, num){
	return num > 0 ? str += times(str, --num): '';
}

function getArea (url) {
	var array = [];
	$.ajax({
		url: url,
		data: JSON.stringify({start:1, length:999}),
		contentType:'application/json;charset=utf-8',
		type: 'POST',
		async: false,
		success: function (res) {
			var data = res.data;

			for (var i = 0; i < data.length; i++) {
				var count = 0;
				for (var j = 1; j < data.length; j++) {
					if(data[i].wmAreaId == data[j].parentId) {
						var index1 = data.indexOf(data[i])
						var index2 = data.indexOf(data[j])
						var delArr = data.splice(index2, 1)
						count++;
						// 将数排列到当前父地区数据下
						data.splice(index1 + count, 0, delArr[0])
					}
				}
			}
			for (var i = 0; i < data.length; i++) {
				var str = "<option value='"+data[i].wmAreaId+"'>";
				str += times('&nbsp;',(data[i].level) - 1) + '├ ' + data[i].name + '\r\n';
				str += "</option>";
				array.push(str);
			}
		}
	});
	return array;
}

function getSchool(url) {
	var opt_val = "";
	$.ajax({
		url: url,
		contentType:'application/json;charset=utf-8',
		type: 'POST',
		async: false,
		success: function (res) {
			var data = res.data;

			var parentArray = [], childArray = [];
			// 分割父子级关系
			for(var i = 0; i< data.length; i++) {
				if (data[i].parentId == 0){
					parentArray.push(data[i]);
				}else{
					childArray.push(data[i]);
				}
			}

			var str = "&nbsp;├";
			// 将父级与子级数组遍历进行存放
			for(var i = 0; i< parentArray.length; i++) {
				opt_val += "<option id='"+parentArray[i].wmSchoolId+"' value='"+parentArray[i].wmSchoolId+"'>"+parentArray[i].name+"</option>";
				for(var j = 0; j < childArray.length; j++) {
					if (parentArray[i].wmSchoolId == childArray[j].parentId){
						opt_val += "<option id='"+childArray[j].wmSchoolId+"' value='"+childArray[j].wmSchoolId+"'>"+str+childArray[j].name+"</option>";
					}
				}
			}
		}
	});
	return opt_val;
}


/**
 * 获取Echarts 饼状图配置信息
 * @param text
 * @param color
 */
function getEchartsCakeOption(text, color) {
	return {
		title: {
			text: text,
			x: 'center',
			y: 'center',
			textStyle: {
				fontWeight: 'normal',
				color: '#333',
				fontSize: 30
			}
		},
		color: color,
		tooltip: {
			trigger: 'item',
			formatter: "{b}: {c} ({d}%)",
		},
		label: {
			show: false,
		},
		series: [
			{
				type:'pie',
				radius: ['65%', '80%'],
				avoidLabelOverlap: false,
				hoverAnimation: false,
				label: {
					show: false,
				},
				data:[
					{value:100}
				]
			}
		],
	}
}

/**
 *
 * @param legendData 标题
 * @param xAxisData X坐标信息
 * @param series Y数据信息
 * @returns {{yAxis: {axisLine: {}, type: string, nameTextStyle: {}}, xAxis: {data: *}, legend: {data: *, x: string, y: string}, series: [{symbol: string, data: [], symbolSize: number, name: string, itemStyle: {bborderColor: string, color: string, borderWidth: number}, type: string}, {symbol: string, data: [], symbolSize: number, name: string, itemStyle: {borderColor: string, color: string, borderWidth: number}, type: string}], tooltip: {trigger: string}, title: {show: boolean}}}
 */
function initEchartsWireOption(legendData, xAxisData, series) {
	return {
		title: {
			show: false,
		},
		tooltip: {
			trigger: 'axis'
		},
		legend: {
			x : 'center',
			y : 'top',
			data: legendData
		},
		xAxis: {
			data: xAxisData,
		},
		yAxis:{
			type: 'value',
			nameTextStyle: {
				// 坐标轴名称的文字样式
			},
			axisLine: {
				// 坐标轴轴线相关设置
			}
		},
		series: series
	}
}
