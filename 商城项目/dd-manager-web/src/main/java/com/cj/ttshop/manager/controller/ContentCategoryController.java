package com.cj.ttshop.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cj.ttshop.content.service.ContentCatService;
import com.cj.ttshop.pojo.TbContentCategory;
import com.cj.ttshop.vo.EasyUITreeNode;
import com.cj.ttshop.vo.R;

@Controller
public class ContentCategoryController {
/*
 * 内容分管理Controller
 * */
	@Autowired
	private ContentCatService contentCatService;
	
	
	@RequestMapping("/content/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(defaultValue="0", name="id") Long parentId){
	
		return   contentCatService.getContentCatList(parentId);

	}
	
	/*
	 * 添加分类节点
	 * 
	 * */
    @RequestMapping("content/cat/create")
    @ResponseBody
	public R creat(Long parentId){
    	R r = contentCatService.create(parentId);
    	System.err.println(r);
		return r;
		
		
	}
    
    /**
     * 删除分类节点
     */
    @RequestMapping("/content/cat/del")
    @ResponseBody
    public R deleteById(Long id){
        return contentCatService.deleteById(id);
    }
    /**
     * 更新分类节点
     */
    @RequestMapping("/content/cat/update")
    @ResponseBody
    public R update(TbContentCategory cat){
        return contentCatService.update(cat);
    }
}
