package com.cj.ttshop.item.service;

import java.util.List;

import com.cj.ttshop.vo.EasyUITreeNode;

public interface ItemCatService {

	 List<EasyUITreeNode> getItemCatList(Long parentId);
}
