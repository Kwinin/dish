package com.cj.ttshop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cj.ttshop.content.service.ContentService;
import com.cj.ttshop.vo.R;

@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;
	
	//@Value("${CONTENT_CAROUSEL_ID}")
	//private Long CONTENT_CAROUSEL_ID;
	
	@RequestMapping("/")
	public String showIndex(Model model) {
		
		R r = contentService.getContentListByCid(89L);
		
		model.addAttribute("adCarouselList", r);
		System.out.println("IndexController"+r);
		return "index";
	}
}
