package com.cj.ttshop.vo;

import java.io.Serializable;

public class TbSearchItemCustom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	
	 private String id;//solr中的id是字符串格式
	    private String title;
	    private String sellPoint;
	    private long price;
	    private String image;
	    private String catName;
	     private String description;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSellPoint() {
			return sellPoint;
		}
		public void setSellPoint(String sellPoint) {
			this.sellPoint = sellPoint;
		}
		public long getPrice() {
			return price;
		}
		public void setPrice(long price) {
			this.price = price;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getCatName() {
			return catName;
		}
		public void setCatName(String catName) {
			this.catName = catName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public String[] getImages(){
			if (this.image !=null && !"".equals(this.image)) {
				String[] images=this.image.split(",");
				return images;
			}
			return null;
		}
		@Override
		public String toString() {
			return "TbSearchItemCustom [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price
					+ ", image=" + image + ", catName=" + catName + ", description=" + description + "]";
		}
	     
		
	
}
