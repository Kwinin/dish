package com.cj.ttshop.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.ttshop.content.service.ContentCatService;
import com.cj.ttshop.dao.TbContentCategoryMapper;
import com.cj.ttshop.dao.TbContentCategoryMapperCustom;
import com.cj.ttshop.pojo.TbContentCategory;
import com.cj.ttshop.pojo.TbContentCategoryExample;
import com.cj.ttshop.vo.EasyUITreeNode;
import com.cj.ttshop.vo.R;

@Service
public class ContentCatServiceImpl implements ContentCatService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Autowired
	private TbContentCategoryMapperCustom contentCategoryMapperCustom;

	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {

		TbContentCategoryExample example = new TbContentCategoryExample();

		TbContentCategoryExample.Criteria criteria = example.createCriteria();

		// 设置查询条件
		criteria.andParentIdEqualTo(parentId);
		criteria.andStatusEqualTo(1);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

		// 创建返回结果:for easyui tree
		List<EasyUITreeNode> easyuiList = new ArrayList<>();
		for (TbContentCategory category : list) {
			EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			easyUITreeNode.setId(category.getId());
			easyUITreeNode.setText(category.getName());
			easyUITreeNode.setState(category.getIsParent() ? "closed" : "open");
			easyuiList.add(easyUITreeNode);

		}

		return easyuiList;
	}

	@Override
	public R create(Long parentId) {

		// 插入新的节点
		TbContentCategory cat = new TbContentCategory();
		cat.setParentId(parentId);
		cat.setName("新的节点");
		cat.setStatus(1);
		cat.setSortOrder(1);
		cat.setIsParent(false);
		Date now = new Date();
		cat.setCreated(now);
		cat.setUpdated(now);
		contentCategoryMapperCustom.insert(cat);
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKeySelective(parentNode);
		}

		return R.ok("添加成功").put("node", cat);
	}

	@Override
	public R deleteById(Long id) {

		TbContentCategory currentNode = contentCategoryMapper.selectByPrimaryKey(id);
		Long parentId = currentNode.getParentId();

		TbContentCategory node = new TbContentCategory();
		node.setStatus(2);
		node.setId(id);
		contentCategoryMapper.updateByPrimaryKeySelective(node);

		// 查询当前节点的父节点是否是其他节点的父节点，如果没有其他子节点，则isparent改为false
		// 即：当前节点的父节点如果没有其他id为1的 子节点，则将这个父节点的isParent改为false
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		criteria.andStatusEqualTo(1);
		int count = contentCategoryMapper.countByExample(example);
		if (count == 0) {
			TbContentCategory parentNode = new TbContentCategory();
			parentNode.setIsParent(false);
			parentNode.setId(parentId);
			contentCategoryMapper.updateByPrimaryKeySelective(parentNode);
		}
		return R.ok("删除成功");
	}

	@Override
	public R update(TbContentCategory cat) {

		cat.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKeySelective(cat);
		return R.ok("更新成功");
	}

}
