package com.cj.ttshop.content.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cj.ttshop.content.service.ContentService;
import com.cj.ttshop.dao.TbContentMapper;
import com.cj.ttshop.pojo.TbContent;
import com.cj.ttshop.pojo.TbContentExample;
import com.cj.ttshop.utils.JsonUtils;
import com.cj.ttshop.utils.jedis.JedisClient;
import com.cj.ttshop.vo.R;


@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public R getContentListByCid(Long cid) {
		
		
		  //查询缓存
        try {
            //如果缓存中有直接响应结果
            String json = jedisClient.hget("CONTENT_LIST", cid + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return R.ok().put("data", list);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        
        //把结果添加到缓存
        try {
            jedisClient.hset("CONTENT_LIST", cid + "", JsonUtils.objectToJson(list));
            //设置过期时间（缓存同步）
            jedisClient.expire("CONTENT_LIST", 3600);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("list:"+list);
        return R.ok().put("data", list);
	}

	
}
