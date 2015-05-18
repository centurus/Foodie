package com.centaurus.bean;

public class Materidl {
	private int contentid;
	private int id;
	private int mwikipediaid;
	private String name;
	private int ordernum;
	private int version;
	private String dosage;
	
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	@Override
	public String toString() {
		return "Materidl [contentid=" + contentid + ", id=" + id
				+ ", mwikipediaid=" + mwikipediaid + ", name=" + name
				+ ", ordernum=" + ordernum + ", version=" + version + "]";
	}
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMwikipediaid() {
		return mwikipediaid;
	}
	public void setMwikipediaid(int mwikipediaid) {
		this.mwikipediaid = mwikipediaid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

}
