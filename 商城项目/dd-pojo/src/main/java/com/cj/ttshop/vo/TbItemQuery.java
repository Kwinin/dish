package com.cj.ttshop.vo;

import java.io.Serializable;

public class TbItemQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	private String title;
	private String status;
	private Long cid;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "TbItemQuery [title=" + title + ", status=" + status + ", cid=" + cid + "]";
	}
	
	
}
