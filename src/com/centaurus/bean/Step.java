package com.centaurus.bean;

public class Step {

	private int contentid;
	private String details;
	private int id;
	private String imageid;
	private int time;
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Step [contentid=" + contentid + ", details=" + details
				+ ", id=" + id + ", imageid=" + imageid + ", time=" + time
				+ "]";
	}
	
}
