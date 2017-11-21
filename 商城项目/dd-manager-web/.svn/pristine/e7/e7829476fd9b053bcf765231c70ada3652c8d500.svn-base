package com.cj.ttshop.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cj.ttshop.search.service.SearchItemService;
import com.cj.ttshop.vo.R;

@Controller
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/search/item/import")
	@ResponseBody
	public R importItemList() throws Exception {

		return searchItemService.importAllItems();
	}
}
