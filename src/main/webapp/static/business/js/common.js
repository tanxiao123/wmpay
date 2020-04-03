function strToJson(str) {
	return eval('('+str+')');
}


function queryList(url, columns, sendData) {
	dataTable = $("#dataTable").dataTable({
		"bStateSave": false,
        "bPaginate": true,// 分页按钮  
        "bFilter": false,// 搜索栏  
        "bLengthChange": false,// 每行显示记录数  
        "iDisplayStart": pStart,
        "iDisplayLength": iDisplayLength,// 每页显示行数  
        "bSort": false,// 排序  
        "bInfo": true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息  
        "bWidth": false,
        "ordering":false,
        "bScrollCollapse": true,
        "sPaginationType": "full_numbers", // 分页，一共两种样式full_numbers 另一种为two_button // 是datatables默认  
        "bProcessing": false,
        "bServerSide": true,
        "bDestroy": true,
        "bSortCellsTop": true,
        "sAjaxSource": url,
        "aoColumns": strToJson(columns),
        "aoColumnDefs": strToJson(aoColumnDefs),
		
	});
}