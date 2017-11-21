package com.cj.ttshop.item.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.ttshop.dao.TbItemDescMapper;
import com.cj.ttshop.dao.TbItemMapper;
import com.cj.ttshop.dao.TbItemMapperCustom;
import com.cj.ttshop.item.service.ItemService;
import com.cj.ttshop.item.vo.Item;
import com.cj.ttshop.pojo.TbItem;
import com.cj.ttshop.pojo.TbItemDesc;
import com.cj.ttshop.pojo.TbItemExample;
import com.cj.ttshop.utils.IDUtils;
import com.cj.ttshop.vo.EasyUIDataGridResult;
import com.cj.ttshop.vo.Order;
import com.cj.ttshop.vo.Pager;
import com.cj.ttshop.vo.R;
import com.cj.ttshop.vo.TbItemCustom;
import com.cj.ttshop.vo.TbItemQuery;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemMapperCustom itemMapperCustom;

	@Override
	public TbItem getItemById(Long id) {
		System.out.println("service:" + id);
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbItem> getItemListAll() {
		return itemMapper.selectByExample(null);
	}

	@Override
	public EasyUIDataGridResult getItenListByPager(Pager pager, Order order, TbItemQuery query) {

		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("order", order);
		map.put("query", query);
		// 设置分页信息
		// 计算分页参数
		pager.setPageParams();
		List<TbItemCustom> list = itemMapperCustom.selectByPager(map);

		// 取总记录数
		long count = itemMapperCustom.selectCount(map);

		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(count);
		result.setRows(list);

		return result;
	}

	@Override
	public R deleteByIdList(List<Long> ids) {
		// TODO Auto-generated method stub
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);

		TbItem record = new TbItem();

		record.setStatus((byte) 3);
		int count = itemMapper.updateByExampleSelective(record, example);
		return R.ok("恭喜!删除成功").put("count", count);
	}

	@Override
	public R upByIdList(List<Long> ids) {
		// TODO Auto-generated method stub
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);

		TbItem record = new TbItem();

		record.setStatus((byte) 1);
		int count = itemMapper.updateByExampleSelective(record, example);
		return R.ok("恭喜!上架成功").put("count", count);
	}

	@Override
	public R downByIdList(List<Long> ids) {
		// TODO Auto-generated method stub
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);

		TbItem record = new TbItem();

		record.setStatus((byte) 2);
		int count = itemMapper.updateByExampleSelective(record, example);
		return R.ok("恭喜!下架成功").put("count", count);
	}

	@Override
	public R save(TbItem item, String desc) {
		// TODO Auto-generated method stub

		long itemId = IDUtils.genItemId();
		Date now = new Date();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(now);
		item.setUpdated(now);

		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(now);
		itemDesc.setUpdated(now);
		itemDescMapper.insert(itemDesc);
		int count = itemMapper.insert(item);
		return R.ok("添加成功").put("count", count).put("itemId",itemId);

	}

	@Override
	public R getItemByItemId(Long id) {
		// TODO Auto-generated method stub
		TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		
		System.out.println(tbItem);
		
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
		
		Item item =new Item(tbItem,itemDesc.getItemDesc());
		

		
		return R.ok().put("item", item);
	}

}
