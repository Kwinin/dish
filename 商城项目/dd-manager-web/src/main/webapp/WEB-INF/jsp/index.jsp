<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天天商城后台管理系统</title>

<link rel="stylesheet" href="js/jquery-easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" href="js/jquery-easyui-1.5/themes/icon.css" />
<link rel="stylesheet" href="js/layer/skin/layer.css" />

<!-- 自定义css -->
<link rel="stylesheet" href="css/web.css" />

</head>
<body class="easyui-layout">

    <div data-options="region:'north',border:false" style="padding:5px;background-color:#3D3D3D;color:#fff; ">
        <h1 style="font-size:20px;">天天商城后台管理系统</h1>
    </div>
    
    <div data-options="region:'west'" style="width:200px;">
        <div id="menu" class="easyui-accordion" data-options="border:false">
		      <div title="商品管理" data-options="selected:true" style="padding:10px 0;">
		         	<ul class="easyui-tree"> 
		         	  <li data-options="attributes:{'href':'item-add'}">新增商品</li>
				      <li data-options="attributes:{'href':'item-list'}">查询商品</li>
				      <li data-options="attributes:{'href':'item-param-list'}">规格参数</li>  
					</ul>    
		      </div>
		      <div title="广告管理" style="padding:10px 0;">
		      		<ul class="easyui-tree">  
					      <li data-options="attributes:{'href':'content-category'}">分类管理</li>
					      <li data-options="attributes:{'href':'content'}">广告内容管理</li>
					</ul> 	         
		      </div>
		      <div title="索引库管理" style="padding:10px 0;">
		      		<ul class="easyui-tree"> 
				      	<li data-options="attributes:{'href':'index-item'}">solr索引库维护</li>
					</ul>          
		      </div>
		</div>
    </div>
    
    <div data-options="region:'south'" style="background:#F2F2F2;padding:5px;">
        	系统版本：V1.0 by Kwinin
    </div>    
    <div data-options="region:'center'">
    
    	<div id="tt" class="easyui-tabs" data-options="fit:true">   
		    <div title="主页面" style="padding:20px;display:none;">   
		        Welcome  
		    </div>    
		</div>  
		    	
    </div>

<script src="js/jquery-easyui-1.5/jquery.min.js"></script>
<script src="js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script src="js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>

<!-- moment -->
<script src="js/moment/moment.js"></script>
<script src="js/moment/zh-cn.js"></script>
<!-- layer -->
<script src="js/layer/layer.js"></script>
<!-- 百度富文本编辑器 UEditor -->
<script src="js/ueditor/ueditor.config.js"></script>
<script src="js/ueditor/ueditor.all.min.js"></script>

<script type="text/javascript">
UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage') {
        //return 'http://localhost:8080/ttshop/file/upload';
        return '${pageContext.request.contextPath}/file/upload?action=uploadimage';
    } else {
        return this._bkGetActionUrl.call(this, action);
    }
}
</script>
<!-- 自定义js -->
<script src="js/common.js"></script>



</body>
</html>