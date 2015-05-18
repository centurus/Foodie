package com.centaurus.bean;

public class Tips {

	private int contentid;
	private String details;
	private int id;
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
	@Override
	public String toString() {
		return "Tips [contentid=" + contentid + ", details=" + details
				+ ", id=" + id + "]";
	}
}
