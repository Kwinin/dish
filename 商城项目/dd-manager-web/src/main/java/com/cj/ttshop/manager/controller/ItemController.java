package com.cj.ttshop.manager.controller;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cj.ttshop.item.service.ItemService;
import com.cj.ttshop.pojo.TbItem;
import com.cj.ttshop.vo.EasyUIDataGridResult;
import com.cj.ttshop.vo.Order;
import com.cj.ttshop.vo.Pager;
import com.cj.ttshop.vo.R;
import com.cj.ttshop.vo.TbItemQuery;

@Controller
public class ItemController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ActiveMQTopic topicDestination;

	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		System.out.println(itemId);
		return itemService.getItemById(itemId);
	}

	@RequestMapping("/item/list-all")
	@ResponseBody
	public List<TbItem> getItemListAll() {
		return itemService.getItemListAll();
	}

	@RequestMapping("/item/list-page")
	@ResponseBody
	public EasyUIDataGridResult getItemListPage(Pager pager, Order order, TbItemQuery query) {

		return itemService.getItenListByPager(pager, order, query);
	}

	@RequestMapping("/item/delete")
	@ResponseBody
	public R delete(@RequestParam("ids") List<Long> ids) {
		return itemService.deleteByIdList(ids);

	}

	@RequestMapping("/item/up")
	@ResponseBody
	public R up(@RequestParam("ids") List<Long> ids) {
		return itemService.upByIdList(ids);

	}

	@RequestMapping("/item/down")
	@ResponseBody
	public R down(@RequestParam("ids") List<Long> ids) {
		return itemService.downByIdList(ids);

	}

	@RequestMapping("/item/save")
	@ResponseBody
	public R save(TbItem item, String desc) {
		R r = itemService.save(item, desc);
		
		//发送消息
		long itemId = (long) r.get("itemId");
		jmsTemplate.send(topicDestination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub

				TextMessage textMessage = session.createTextMessage(itemId + "");
				return textMessage;
			}
		});

		return r;  
	}
}
