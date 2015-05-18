package com.centaurus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;

public class ShearedPf{
	private SharedPreferences spf;
	private Context context;
	private String username;
	private String password;
	public ShearedPf(Context context){
		this.context=context;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getuser(){
		spf=context.getSharedPreferences("user", Context.MODE_PRIVATE);
		Map<String,String>map=new HashMap<String,String>();
		List<Map<String,String>>list_map=new ArrayList<Map<String,String>>();
		map=(Map<String, String>) spf.getAll();
		for (Entry<String, String> maps : map.entrySet()) {
			Map<String,String>map_list=new HashMap<String, String>();
			map.put(maps.getKey(), maps.getValue());
			if(maps.getKey().equals("username")){
				username=maps.getValue();
			}
			if(maps.getKey().equals("password")){
				password=maps.getValue();
			}
			list_map.add(map_list);
		}
		return list_map;
	}
	public int getisuser(){
		List<Map<String,String>> list=getuser();
		if(list.size()>0){
			return 1;
		}
		return -1;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		getuser();
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
