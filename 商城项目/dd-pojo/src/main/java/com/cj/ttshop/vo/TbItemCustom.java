package com.cj.ttshop.vo;

import java.io.Serializable;

import com.cj.ttshop.pojo.TbItem;

public class TbItemCustom extends TbItem implements Serializable{

	private static final long serialVersionUID = 1L;


	
	private String catName;
	private String statusName;
	public TbItemCustom() {
		super();
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
