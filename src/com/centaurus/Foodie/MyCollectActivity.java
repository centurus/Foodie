package com.centaurus.Foodie;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.centaurus.adpter.MyListViewAdapter;
import com.centaurus.bean.FoodContent;
import com.centaurus.db.DBManager;
import com.centaurus.util.ShearedPf;

public class MyCollectActivity extends Activity implements OnItemClickListener{
	
	private DBManager manager;
	private List<FoodContent> list;
	private MyListViewAdapter adapter;
	private ListView listView1_chosen_recipe;
	private ShearedPf spf=new ShearedPf(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_collect);
		manager = new DBManager(this);
		listView1_chosen_recipe=(ListView) this.findViewById(R.id.listView1_chosen_recipe);
		listView1_chosen_recipe.setOnItemClickListener(this);
		init();
		
	}
	private void init() {
		if(spf.getisuser()==1){
			String str=spf.getUsername();
			list = manager.query(str);
			
		}else{
			list = manager.query();
		}
		if(list.size()>0){
			adapter=new MyListViewAdapter(this);
			listView1_chosen_recipe.setAdapter(adapter);
			adapter.addList(list);
		}else{
			Toast.makeText(this, "暂无收藏", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this,FoodContentActivity.class);
		intent.putExtra("id", list.get(position).getId());
		intent.putExtra("imageid", list.get(position).getImageid()+"");
		intent.putExtra("name", list.get(position).getName());
		this.startActivity(intent);
		
	}  
	

}
