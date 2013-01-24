//Custom link for grid column.
function formatLink(cellvalue, options, rowObject) {
    return "<a href='#' onClick='javascript:openDialog("+rowObject.id+")'>"+rowObject.url+"</a>";
}

//Show the referred file.
function openDialog(fileID) {
	var url = webpath + '/showFileAction?fileID=' + fileID;
	url = encodeURI(url);
	var name = 'ShowFile'; // open的网页名称，可为空; 但是不能有特殊字符，甚至连空格都不能有。否则会包参数无效JS错误。
	var iWidth = 800;
	var iHeight = 600;
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;
	var properties = "height="
			+ iHeight
			+ ",width="
			+ iWidth
			+ ",top="
			+ iTop
			+ ",left="
			+ iLeft
			+ ",toolbar=no,menubar=no,scrollbars=auto,resizable=no,location=no,status=no";
	window.open(url, name, properties);
}