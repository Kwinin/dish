package com.cj.ttshop.item.service;

import java.util.List;

import com.cj.ttshop.pojo.TbItem;
import com.cj.ttshop.vo.EasyUIDataGridResult;
import com.cj.ttshop.vo.Order;
import com.cj.ttshop.vo.Pager;
import com.cj.ttshop.vo.R;
import com.cj.ttshop.vo.TbItemQuery;

public interface ItemService {

	public TbItem getItemById(Long id);
	
	public List<TbItem> getItemListAll();
	
	public EasyUIDataGridResult getItenListByPager(Pager pager,Order order,TbItemQuery query);
	
	public R deleteByIdList(List<Long> ids);
	public R upByIdList(List<Long> ids);
	public R downByIdList(List<Long> ids);


	public R save(TbItem item, String desc);
	public R getItemByItemId(Long id);
}
