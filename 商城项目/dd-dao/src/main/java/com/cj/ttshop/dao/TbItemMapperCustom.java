package com.cj.ttshop.dao;

import java.util.List;
import java.util.Map;

import com.cj.ttshop.vo.TbItemCustom;
import com.cj.ttshop.vo.TbSearchItemCustom;



public interface TbItemMapperCustom {
	
	
	long selectCount(Map<String, Object> map);

	List<TbItemCustom> selectByPager(Map<String, Object> map);
	
	List<TbSearchItemCustom> selectSearchItemList();

	TbSearchItemCustom getSearchItemById(Long itemId);
}