package com.centaurus.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;






import com.centaurus.Foodie.R;
import com.centaurus.Foodie.R.drawable;

import android.R.integer;
import android.R.string;

public class Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double latitude;
	private double longitude;
	private int ImgId ;
	private String name;
	private String distance;
	private int zan;
	public static List<Info> infos = new ArrayList<Info>();
	
	static{
		infos.add(new Info(39.95786, 116.342709, R.drawable.a01, "Ӣ�׹���С�ù�",
				"����209��", 1456));
	
	}
	public Info(double latitude, double longitude, int imgId, String name,
			String distance, int zan)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.ImgId= imgId;
		this.name = name;
		this.distance = distance;
		this.zan = zan;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongtitude() {
		return longitude;
	}
	public void setLongtitude(double longtitude) {
		this.longitude = longtitude;
	}
	public int getImgId() {
		return ImgId;
	}
	public void setImgId(int imgId) {
		ImgId = imgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public int getZan() {
		return zan;
	}
	public void setZan(int zan) {
		this.zan = zan;
	}
	
}
