package com.centaurus.Foodie;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.centaurus.Foodie.R;
import com.centaurus.adpter.MyListViewAdapter;
import com.centaurus.bean.FoodContent;
import com.centaurus.util.CallbackJSONObject;
import com.centaurus.util.JsonUtils;
import com.centaurus.util.URLUtils;
import com.centaurus.util.VolleyUtils;
import com.centaurus.view.MyDialog;
/**
 * 首页
 *
 */
public class HomeActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private ListView listview1;
	private TextView common_title;
	private String title;
	private String url;
	private VolleyUtils volleyUtils;
	private List<FoodContent> list = new ArrayList<FoodContent>();
	private MyListViewAdapter adapter;
	private MyDialog dialog; 
	private Button morebutton;
	private String[] idss;
	private JsonUtils jsonUtils;
	private static final int MORE_LIMIT = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_chosen_recipe);
		listview1 = (ListView) this.findViewById(R.id.listView1_chosen_recipe);
		common_title = (TextView) this.findViewById(R.id.common_title);
		title = "精选菜谱";
		url = URLUtils.CHOSEN_RECIPE;
		common_title.setText(title);
		adapter = new MyListViewAdapter(this);
		morebutton = new Button(this);
		morebutton.setText("更多");
		morebutton.setGravity(Gravity.CENTER);
		morebutton.setOnClickListener(this);
		listview1.addFooterView(morebutton);
		listview1.setAdapter(adapter);
		listview1.setOnItemClickListener(this);
		init();
	}

	private String moreids;

	private void init() {
		dialog = MyDialog.newInstance(this);
		jsonUtils = new JsonUtils();
		volleyUtils = new VolleyUtils();
		volleyUtils.newRequestQueue(this);
		dialog.show();

		volleyUtils.getJSONObject(url, new CallbackJSONObject() {

			@Override
			public void getJSONObject(JSONObject json) {
				moreids = jsonUtils.parserIds(json);
				idss = moreids.split(",");
				String ids = getCurrentids();
				volleyUtils.getJSONObject(
						URLUtils.SIFT_RECIPE_LIST_BY_ID + ids,
						new CallbackJSONObject() {
							@Override
							public void getJSONObject(JSONObject json) {
								dialog.dismiss();
								list = jsonUtils
										.parserJSONObjectToFoodContent(json);
								number = list.size();
								adapter.addList(list);

							}
						});
			}

		});
	}

	private int number = 0;

	private String getCurrentids() {
		String currentids = "";
		for (int i = number; i < MORE_LIMIT + number; i++) {
			currentids += idss[i] + ",";
		}
		number += MORE_LIMIT;
		System.out.println("----currentids----" + currentids);
		return currentids;
	}

	private void getMoreInfo() {
		String ids = getCurrentids();
		dialog.show();

		volleyUtils.getJSONObject(URLUtils.SIFT_RECIPE_LIST_BY_ID + ids,
				new CallbackJSONObject() {

					@Override
					public void getJSONObject(JSONObject json) {

						dialog.dismiss();
						list.addAll(jsonUtils
								.parserJSONObjectToFoodContent(json));
						adapter.addList(list);
						adapter.notifyDataSetChanged();
					}
				});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this, FoodContentActivity.class);
		intent.putExtra("id", list.get(position).getId());
		intent.putExtra("imageid", list.get(position).getImageid() + "");
		intent.putExtra("name", list.get(position).getName());
		this.startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		getMoreInfo();
	}

}
