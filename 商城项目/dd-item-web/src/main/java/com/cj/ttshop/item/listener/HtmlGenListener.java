package com.cj.ttshop.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.cj.ttshop.item.service.ItemService;
import com.cj.ttshop.item.vo.Item;
import com.cj.ttshop.vo.R;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenListener implements MessageListener {

	@Autowired
	private ItemService itemService;

	@Autowired
	private FreeMarkerConfig freemarkerConfig;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {

			// 获取消息内容：itemId
			TextMessage txmessaged = (TextMessage) message;

			String test = txmessaged.getText();

			Long itemId = new Long(test);

		     //准备数据
            R r= itemService.getItemByItemId(itemId);
            Item item=(Item)r.get("item");
            
            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.put("item", item);
            
            //准备模板
            Configuration configuration = freemarkerConfig.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            
            //生成静态页
            Writer out = new FileWriter(new File("F:/ftl/ttshop/" + itemId + ".html"));
            template.process(dataModel, out);
            out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
