package com.centaurus.bean;

import java.util.List;

public class FoodContent {
	private String category;
	private String editid;
	private String editname;
	private String content;
	private String gettime;
	private String id;
	private int imageSizePo_high;
	private int imageSizePo_width;
	private int imageid;
	private List<Materidl> materidlList;
	private String name;
	private List<Step> stepList;
	private String type;
	private List<Tips> tipsList;
	private List<DiscussionPo> discussionPoList;
	private String collectNum;
	private byte[] imagebytes;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public byte[] getImagebytes() {
		return imagebytes;
	}
	public void setImagebytes(byte[] imagebytes) {
		this.imagebytes = imagebytes;
	}
	public String getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(String collectNum) {
		this.collectNum = collectNum;
	}
	public List<DiscussionPo> getDiscussionPoList() {
		return discussionPoList;
	}
	public void setDiscussionPoList(List<DiscussionPo> discussionPoList) {
		this.discussionPoList = discussionPoList;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Tips> getTipsList() {
		return tipsList;
	}
	public void setTipsList(List<Tips> tipsList) {
		this.tipsList = tipsList;
	}
	private int version;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEditid() {
		return editid;
	}
	public void setEditid(String editid) {
		this.editid = editid;
	}
	public String getEditname() {
		return editname;
	}
	public void setEditname(String editname) {
		this.editname = editname;
	}
	public String getGettime() {
		return gettime;
	}
	public void setGettime(String gettime) {
		this.gettime = gettime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getImageSizePo_high() {
		return imageSizePo_high;
	}
	public void setImageSizePo_high(int imageSizePo_high) {
		this.imageSizePo_high = imageSizePo_high;
	}
	public int getImageSizePo_width() {
		return imageSizePo_width;
	}
	public void setImageSizePo_width(int imageSizePo_width) {
		this.imageSizePo_width = imageSizePo_width;
	}
	public int getImageid() {
		return imageid;
	}
	public void setImageid(int imageid) {
		this.imageid = imageid;
	}
	public List<Materidl> getMateridlList() {
		return materidlList;
	}
	public void setMateridlList(List<Materidl> materidlList) {
		this.materidlList = materidlList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Step> getStepList() {
		return stepList;
	}
	public void setStepList(List<Step> stepList) {
		this.stepList = stepList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "FoodContent [category=" + category + ", editid=" + editid
				+ ", editname=" + editname + ", content=" + content
				+ ", gettime=" + gettime + ", id=" + id + ", imageSizePo_high="
				+ imageSizePo_high + ", imageSizePo_width=" + imageSizePo_width
				+ ", imageid=" + imageid + ", materidlList=" + materidlList
				+ ", name=" + name + ", stepList=" + stepList + ", type="
				+ type + ", tipsList=" + tipsList + ", version=" + version+",username="+username
				+ "]";
	}
	
	
}
