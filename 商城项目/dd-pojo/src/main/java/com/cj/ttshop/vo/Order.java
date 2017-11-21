package com.cj.ttshop.vo;

import java.io.Serializable;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	private String sort;
	private String order;
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "Order [sort=" + sort + ", order=" + order + "]";
	}
	
}
