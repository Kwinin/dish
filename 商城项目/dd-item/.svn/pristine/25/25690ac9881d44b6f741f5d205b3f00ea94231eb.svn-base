package com.cj.ttshop.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.ttshop.dao.TbItemCatMapper;
import com.cj.ttshop.item.service.ItemCatService;
import com.cj.ttshop.pojo.TbItemCat;
import com.cj.ttshop.pojo.TbItemCatExample;
import com.cj.ttshop.vo.EasyUITreeNode;



@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		// TODO Auto-generated method stub
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);

		// 创建返回结果:For easyui tree
		List<EasyUITreeNode> easyuiList = new ArrayList<EasyUITreeNode>();

		for (TbItemCat itemCat : list) {

			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent() ? "closed" : "open");

			easyuiList.add(node);
		}
		return easyuiList;

	}

}
