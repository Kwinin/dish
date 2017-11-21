<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<button id="importbtn" class="easyui-linkbutton" onclick="importItems()">一键导入商品到索引库</button>
<div id="msg" style="display:none;">正在生成索引，请您耐心等待......</div>

<script>
function importItems(){
    
    $('#msg').show();
    $('#importbtn').linkbutton('disable');
    $.post('search/item/import',function(result){
        
        if(result.code == 0){
            layer.alert(result.msg);
        }else{
            layer.alert('索引导入失败');
        }
        $('#msg').hide();
        $('#importbtn').linkbutton('enable');
    });
}
</script>