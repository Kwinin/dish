<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul id="cid"></ul>

<!-- 右键菜单定义如下： -->
<div id="mm" class="easyui-menu" style="width: 120px;">
	<div onclick="append()" data-options="iconCls:'icon-add'">追加</div>
	<div onclick="edit()" data-options="iconCls:'icon-edit'">重命名</div>
	<div onclick="remove()" data-options="iconCls:'icon-remove'">移除</div>
</div>


<script>
	$(function() {

		//初始化tree
		$('#cid').tree({
			url : 'content/cat/list'
		});

		//右键点击节点并显示快捷菜单
		$('#cid').tree({
			onContextMenu : function(e, node) {
				e.preventDefault();
				// 查找节点
				$('#cid').tree('select', node.target);
				// 显示快捷菜单
				$('#mm').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onAfterEdit : function(node) {

				var data = {
					id : node.id,
					name : node.text
				};
				$.post('content/cat/update', data);
			}
		});
	});

	function append() {
		// 追加若干个节点并选中他们
		var selected = $('#cid').tree('getSelected');
		var parentId = selected.id;
		var data = {
			parentId : parentId
		};
		$.post('content/cat/create', data, function(result) {

			//追加若干个节点并选中他们
			$('#cid').tree('append', {
				parent : selected.target,
				data : [ {
					id : result.node.id,
					text : result.node.name

				} ]
			});

		});

	}
	function edit() {
		console.log('edit');
		var selected = $('#cid').tree('getSelected');
		$('#cid').tree('beginEdit', selected.target);
	}
	function remove() {
		//询问框
		layer.confirm('是否要删除当前类别', {
			btn : [ '确定', '取消' ]
		//按钮
		}, function(index) {

			var selected = $('#cid').tree('getSelected');
			uri = 'content/cat/del?id=' + selected.id;
			//console.log(uri);
			$.get(uri, function(result) {
				if (result.code == 0) {
					layer.alert(result.msg, function(index) {
						$('#cid').tree('remove', selected.target);
					//	addTab('分类管理', 'content-category');
						layer.close(index);
					});
				}
			});
		});
	}
</script>