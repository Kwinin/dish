package com.cj.ttshop.vo;

import java.io.Serializable;

public class Pager implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer page;
	private Integer rows;
	private Integer offset;
	
	public void setPageParams() {
		
		this.offset = (this.page - 1) * this.rows;
	}

	public Pager() {
		super();
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Pager [page=" + page + ", rows=" + rows + ", offset=" + offset + "]";
	}
	
}
