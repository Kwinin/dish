package com.cj.ttshop.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.cj.ttshop.item.service.ItemService;
import com.cj.ttshop.pojo.TbItem;
import com.cj.ttshop.vo.R;

@Controller
public class IndexController {

	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId, Model model) {
		// 返回逻辑视图
		R r= itemService.getItemByItemId(itemId);
		model.addAttribute("item",r.get("item"));
		return "item";
	}
	
	

}
