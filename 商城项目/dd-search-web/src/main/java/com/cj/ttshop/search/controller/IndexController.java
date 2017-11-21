package com.cj.ttshop.search.controller;

import java.io.UnsupportedEncodingException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cj.ttshop.search.service.SearchItemService;
import com.cj.ttshop.vo.R;



@Controller
public class IndexController {

	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/")
	public String showIndex(String keyword, @RequestParam(defaultValue="1") Integer page, Model model) throws Exception {

		try {
			keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");

			
			System.out.println(keyword);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(keyword != null) {
			R result = searchItemService.search(keyword, page, 60);
			model.addAttribute("result", result);
			model.addAttribute("keyword", keyword);
			model.addAttribute("page", page);
		}
		
		return "search";
	}
}
