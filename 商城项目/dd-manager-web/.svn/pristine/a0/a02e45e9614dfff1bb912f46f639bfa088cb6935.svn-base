/**
 * 定义通用js
 */

var defaultDateTimeFormat = 'YYYY-MM-DD HH:mm:ss';
var defaultDateFormat = 'YYYY-MM-DD';

//菜单
$('#menu .easyui-tree').tree({
	onClick: function(node){
		
		addTab(node.text, node.attributes.href);
	}
});


function addTab(text, href){
	

	$('#tt').tabs('close', 1);
	$('#tt').tabs('add', {
		title: text,
		closable:true,
		href: href,
		index: 1
	});
}
