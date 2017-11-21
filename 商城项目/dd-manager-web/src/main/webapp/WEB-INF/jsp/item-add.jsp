<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="myform" id="myform" method="post">
	<table>
		<tr>
			<td class="label">商品类目：</td>
			<td>
				<!-- <select data-options="url:'item/cat/list'"
                 id="cid" name="cid" style="width: 200px;" class="easyui-combotree" ></select>   -->
				<!--  <ul id="tt" class="easyui-tree" ></ul>   --> <input id="cid"
				name="cid" style="width: 200px;">
			</td>
		</tr>
		<tr>
			<td class="label">商品标题：</td>
			<td><input class="easyui-textbox" name="title" id="title"
				style="width: 100%;"
				data-options="required: true, validateOnCreate: false, missingMessage: '请输入商品标题'">
			</td>
		</tr>
		<tr>
			<td class="label">商品卖点：</td>
			<td><input class="easyui-textbox" name="sellPoint"
				id="sellPoint" style="width: 100%; height: 60px;"
				data-options="multiline: true, validType: 'length[0, 200]', invalidMessage: '最多输入200个字符'">
			</td>
		</tr>
		<tr>
			<td class="label">商品价格：</td>
			<td><input class="easyui-numberbox" name="priceView"
				id="priceView"
				data-options="min: 0, precision:2, required: true, validateOnCreate: false, missingMessage: '请输入商品价格'">
				<input type="hidden" name="price" id="price"></td>
		</tr>
		<tr>
			<td class="label">库存数量：</td>
			<td><input class="easyui-numberbox" name="num" id="num"
				data-options="min: 0, precision:0, required: true, validateOnCreate: false">
			</td>
		</tr>
		<tr>
			<td class="label">商品条形码：</td>
			<td><input class="easyui-textbox" name="barcode" id="barcode"
				data-options="validType: 'length[1, 30]', invalidMessage: '最多输入30个字符'">
			</td>
		</tr>
		<tr>
			<td class="label">标题图片</td>
			<td>
				<button type="button" id="upload_img_btn" class="easyui-linkbutton">图片上传</button>
				<ul id="upload_img_wrap"></ul> <input type="text" name="image"
				id="image" style="display: none" /> <!--加载编辑器的容器  --> <script
					id="uploadImg" type="text/plain" style="display: none"></script>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- 富文本编辑器 --> <!-- 加载编辑器的容器 --> <script id="container" name="desc"
					type="text/plain"></script>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="formBtn">
				<button class="easyui-linkbutton" type="button"
					onclick="submitForm()" data-options="iconCls:'icon-ok'">保存</button>

				<button class="easyui-linkbutton" type="button"
					onclick="clearForm()" data-options="iconCls:'icon-undo'">重置</button>
			</td>
		</tr>
	</table>
</form>

<script>
	var ue;
	var uploadEditor;
	$(function() {

		//初始化tree
		$('#cid').combotree({
			url : 'item/cat/list',
			required : true,
			validateOnCreate : false,
			onBeforeSelect : function(node) {
				var r = $('#cid').tree('isLeaf', node.target);
				if (!r) {
					layer.alert('请选择最终节点');
				}
				return r;
			}
		});

		//实例化编辑器
		UE.delEditor('container');
		ue = UE.getEditor('container', {
			initialFrameWidth : '100%',
			initialFrameHeight : '400',
			serverUrl : 'file/upload'
		});

		//实例化编辑器(多图上传按钮)
		UE.delEditor('uploadImg');
		uploadEditor = UE.getEditor('uploadImg', {
			initialFrameWidth : '100%',
			toolbars : [ [ 'insertimage' ] ],
			serverUrl : 'file/upload'
		});

		$('#upload_img_btn').click(function() {

			var dialog = uploadEditor.getDialog('insertimage');
			dialog.title = '图片上传';
			dialog.render();
			dialog.open();
		});

		uploadEditor.ready(function() {
			console.log('ready');

			uploadEditor.addListener('beforeInsertImage', function(t, result) {
				console.log('beforeInsertImage');
				console.log(t);
				console.log(result);

				var imgHtml = '';
				var imgArr = [];
				for (i in result) {
					var img = result[i];
					var src = img.src;
					imgHtml += '<li><img src="' + src + '"></li>';
					imgArr.push(src);
				}
				$('#upload_img_wrap').html(imgHtml);
				$('#image').val(imgArr.join(','));
			});

		});

	});

	function submitForm() {

		$('#myform').form('submit', {
			url : 'item/save',
			onSubmit : function() {
				// do some check   

				$('#price').val($('#priceView').val() * 100);

				// return false to prevent submit;  
				var r = $('#myform').form('validate');
				return r;
			},
			success : function(result) {

				result = JSON.parse(result);
				if (result.code == 0) {
					layer.alert(result.msg, function(index) {
						addTab('查询商品', 'item-list');
						layer.close(index);
					});

				}
			}
		});
		// submit the form    
		//$('#myform').submit();  

	}
	//重置表单
	function clearForm() {
		$('#myform').form('reset');
		ue.setContent('');
		uploadEditor.setContent('');
	}
</script>