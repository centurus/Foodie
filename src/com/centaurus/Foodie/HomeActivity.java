package com.centaurus.Foodie;

import java.util.ArrayList;

import com.centaurus.Foodie.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends Activity implements OnItemClickListener,
		OnScrollListener {
	
	private ListView ResturantList;
	private ArrayAdapter<String> arr_adapter;
	//private SimpleAdapter simp_adapterAdapter;
	public String rantName;
	//从RestInfo获取餐厅信息数组
	RestInfo rInfo=new RestInfo();
	String[] arr_data = rInfo.arr_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_home);
		ResturantList = (ListView) findViewById(R.id.RestaurantlistView);
		// 新建一个适配器

		//String[] arr_data = { "家园餐厅", "明湖餐厅", "教职工餐厅" };
		
		// 适配器加载数据源
		arr_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arr_data);
		// 视图（RestaurantList）加载适配器
		ResturantList.setAdapter(arr_adapter);
		ResturantList.setOnItemClickListener(this);
		ResturantList.setOnScrollListener(this);

	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(HomeActivity.this, RestDetailActivity.class);
		rantName=arr_data[(int) arg3];
		i.putExtra(ACCOUNT_SERVICE, rantName);
		startActivityForResult(i, arg2);

	}
   
}
