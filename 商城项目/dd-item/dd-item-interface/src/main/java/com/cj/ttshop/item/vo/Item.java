package com.cj.ttshop.item.vo;

import java.io.Serializable;

import com.cj.ttshop.pojo.TbItem;

public class Item extends TbItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String desc;

	public Item() {
		super();
	}

	public Item(TbItem tbItem, String desc) {
		
		super();
        this.desc = desc;
        this.setId(tbItem.getId());
        this.setTitle(tbItem.getTitle());
        this.setSellPoint(tbItem.getSellPoint());
        this.setPrice(tbItem.getPrice());
        this.setNum(tbItem.getNum());
        this.setBarcode(tbItem.getBarcode());
        this.setImage(tbItem.getImage());
        this.setCid(tbItem.getCid());
        this.setStatus(tbItem.getStatus());
        this.setCreated(tbItem.getCreated());
        this.setUpdated(tbItem.getUpdated());
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String[] getImages() {
		
		if(this.getImage() != null && !"".equals(this.getImage())) {
			String[] images = this.getImage().split(",");
			return images;
		}
		return null;
	}
}
