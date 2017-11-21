<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="toolbar">

	<div>
		<label for="title">标题：</label><input type="text" id="title" name="title" class="easyui-textbox" />
		<label for="cid">类别：</label>
		<select id="cid" name="cid" class="easyui-combobox">
			<option value="0">全部</option>
			<option value="76">平板电视</option>
			<option value="560">手机</option>
		</select>
		<label for="status">状态：</label>
		<select id="status" name="status" class="easyui-combobox">
			<option value="0">全部</option>
			<option value="1">正常</option>
			<option value="2">下架</option>
		</select>
		<label for="predatetime">开始时间：</label><input class="easyui-datetimebox" id="predatetime" name="predatetime"     
        data-options="showSeconds:false" style="width:150px">  

		<label for="afterdatetime">	结束时间:</label><input class="easyui-datetimebox" id="afterdatetime" name="afterdatetime"     
        data-options="showSeconds:false" style="width:150px">  
 
		<button type="button" class="easyui-linkbutton" onclick="searchForm()" data-options="iconCls:'icon-search'" >查询</button>
	</div>

	<div>
		<!-- <a href="" class="easyui-linkbutton">删除</a> -->
		<button type="button" class="easyui-linkbutton" onclick="deleteItem()" data-options="iconCls:'icon-cancel'">删除</button>
		<button onclick="upItem()" class="easyui-linkbutton" data-options="iconCls:'icon-up'">上架</button>
		<button onclick="downItem()" class="easyui-linkbutton" data-options="iconCls:'icon-down'">下架</button>
	</div>

</div>    
<table id="table"></table>  



<script>
	$(function() {

		$('#table').datagrid({
			url : 'item/list-page',
	
			fit : true,
			pagination : true,
			pageSize : 20,
			rownumbers : true,
			toolbar : '#toolbar',
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'id',
				title : '商品ID',
				sortable : true
			}, {
				field : 'title',
				title : '商品标题',
				sortable : true
			}, {
				field : 'catName',
				title : '类目',
				sortable : true
			}, {
				field : 'sellPoint',
				title : '卖点',
				sortable : true
			},
			/* {field:'price',title:'价格'}, */
			{
				field : 'price',
				title : '价格（元）',
				align : 'right',
				formatter : function(value, row, index) {
					return (value / 100).toFixed(2);
				}
			}, {
				field : 'num',
				title : '库存数量'
			}, {
				field : 'barcode',
				title : '条形码'
			}, {
				field : 'statusName',
				title : '状态',
				sortable : true
			}, {
				field : 'created',
				title : '创建日期',
				formatter : function(value, row, index) {
					return moment().format(defaultDateTimeFormat);
				}
			}, {
				field : 'updated',
				title : '更新日期',
				formatter : function(value, row, index) {
					return moment().format(defaultDateTimeFormat);
				}
			} ] ],
			onBeforeLoad : function(param) {
				if (param.sort) {
					console.log(param.sort);
					//将自定义字段名存入map
					var map = new Map();
					//将表前缀从dao的sql中移到这里处理，更灵活，可以按照关联表中的字段排序，例如商品卖点
					map['id'] = 'i.id';
					//商品名称是中文，需要在dao的sql中做特殊处理
					map['title'] = 'convert(i.title using gbk)';
					map['price'] = 'i.price';
					map['sellPoint'] = 'convert(i.sell_Point using gbk)';
					map['catName'] = 'convert(c.name using gbk)';
					map['statusName'] = 'i.status';
					//根据用户的选择获取自定义字段名
					var fieldSort = map[param.sort];
					//设置自定义字段名
					param.sort = fieldSort;

				}
			}
		});

	});

	function deleteItem() {
		doOperation('您确认想要删除记录吗？', 'item/delete?');
	}
	function upItem() {
		doOperation('您确认要上架商品吗？', 'item/up?');
	}
	function downItem() {
		doOperation('您确认要下架商品吗？', 'item/down?');
	}
	function doOperation(message, url) {

		//原来deleteItem方法重的内容

		//选没选
		var rows = $('#table').datagrid('getSelections');
		if (rows.length == 0) {
			layer.alert('请选择要執行的记录');
			return;

		}
		//询问框
		layer.confirm(message, {
			btn : [ '确定', '取消' ]
		//按钮
		}, function(index) {

			//删
			console.log(rows);

			var ids = [];
			$.each(rows, function(index, row) {

				var id = row.id;
				ids.push('ids=' + id);
			});

			var uri = url + ids.join('&');

			$.get(uri, function(result) {
				if (result.code == 0) {

					layer.alert(result.msg);
					layer.close(index);
					//刷新页面
					$('#table').datagrid('reload');
				}
			});

		});

	}

	function searchForm(){
		
		
		$('#table').datagrid('load', {    
		    title: $('#title').val(),    
		    cid: $('#cid').combobox('getValue'),    
		    status: $('#status').combobox('getValue'),
	 	 
		}); 
		
	}

</script>