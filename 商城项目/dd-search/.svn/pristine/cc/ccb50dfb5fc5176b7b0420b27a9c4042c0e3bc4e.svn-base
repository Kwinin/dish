package com.cj.ttshop.search.service.impl;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.ttshop.dao.TbItemMapperCustom;
import com.cj.ttshop.search.service.SearchItemService;
import com.cj.ttshop.vo.R;
import com.cj.ttshop.vo.TbSearchItemCustom;
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private TbItemMapperCustom itemMapper;

	@Autowired
	private SolrServer solrServer;

	@Override
	public R importAllItems() throws Exception {

		// 查询商品
		List<TbSearchItemCustom> itemList = itemMapper.selectSearchItemList();

		// 构建索引库
		for (TbSearchItemCustom item : itemList) {

			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", item.getId());
			document.addField("item_title", item.getTitle());
			document.addField("item_sell_point", item.getSellPoint());
			document.addField("item_price", item.getPrice());
			document.addField("item_image", item.getImage());
			document.addField("item_category_name", item.getCatName());

			solrServer.add(document);
		}

		solrServer.commit();
		return R.ok("索引创建成功");

	}

	@Override
	public R search(String keyword, int page, int rows) throws SolrServerException {
	
	
		//组织查询条件
				SolrQuery query = new SolrQuery();
				
				//设置查询关键字
				query.setQuery(keyword);
				
				//设置分页条件
				if(page <= 0) {
					page = 1;
				}
				query.setStart((page - 1) * rows);
				query.setRows(rows);
				
				//设置查询的域
				query.set("df", "item_keywords");
				
				//设置高亮显示
				query.setHighlight(true);
				//设置高亮参数
				query.setHighlightSimplePre("<em color=\"red\">");
				query.setHighlightSimplePost("</em>");
				query.addHighlightField("item_title");
				
				
				//组装查询结果
				QueryResponse response = solrServer.query(query);
				
				//获取docs节点的数据
				SolrDocumentList solrDocumentList = response.getResults();
				
				//获取highlighting节点的数据
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				
				List<TbSearchItemCustom> itemList = new ArrayList<TbSearchItemCustom>();
			    for(SolrDocument SolrDocument : solrDocumentList) {
			    	
			    	TbSearchItemCustom item = new TbSearchItemCustom();
			    	item.setId((String)SolrDocument.get("id"));
			    	item.setCatName((String)SolrDocument.get("item_category_name"));
			    	item.setImage((String)SolrDocument.get("item_image"));
			    	item.setPrice((long)SolrDocument.get("item_price"));
			    	item.setSellPoint((String)SolrDocument.get("item_sell_point"));
			    	
			    	//获取高亮结果字段
			    	//item.setTitle((String)SolrDocument.get("item_title"));
			    	Map<String, List<String>> map = highlighting.get(SolrDocument.get("id"));
			    	List<String> list = map.get("item_title");
			    	if(list != null && list.size() > 0) {
			    		item.setTitle(list.get(0));
			    	}else {
			    		item.setTitle((String)SolrDocument.get("item_title"));
			    	}
			    	itemList.add(item);
			    }
			    
			    //获取页码数据
			    
			    //一共找到多少条记录
			    long numFound = solrDocumentList.getNumFound();
			    long totalPage = numFound / rows;
			    if(numFound % rows > 0) {
			    	totalPage++;
			    }
				
			    return R.ok().put("itemList", itemList).put("totalPage", totalPage).put("numFound", numFound);
	}

}
