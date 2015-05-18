package com.centaurus.Foodie;

import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.centaurus.adpter.MyListViewAdapter;
import com.centaurus.bean.FoodContent;
import com.centaurus.util.CallbackJSONObject;
import com.centaurus.util.JsonUtils;
import com.centaurus.util.SearchUtils;
import com.centaurus.util.URLUtils;
import com.centaurus.util.VolleyUtils;
import com.centaurus.view.ClearEditText;
import com.centaurus.view.MyDialog;

/**
 * 搜索
 * 
 */
public class SearchActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	private String key;
	private ListView listview;
	private ClearEditText editText;
	private ImageButton searchBtn, backBtn;
	private MyListViewAdapter adapter;
	private VolleyUtils volleyUtils;
	private JsonUtils jsonUtils;
	private MyDialog myDialog;
	private List<FoodContent> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		adapter = new MyListViewAdapter(this);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		listview = (ListView) this.findViewById(R.id.search_listview);
		searchBtn = (ImageButton) this.findViewById(R.id.search_btn);
		editText = (ClearEditText) this.findViewById(R.id.search_txt);
		backBtn = (ImageButton) findViewById(R.id.backBtn);
		volleyUtils = new VolleyUtils();
		volleyUtils.newRequestQueue(this);
		jsonUtils = new JsonUtils();
		listview.setAdapter(adapter);
		searchBtn.setOnClickListener(this);
		listview.setOnItemClickListener(this);
		backBtn.setOnClickListener(this);

	}



	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.backBtn:
			finish();
			break;

		case R.id.search_btn:
			key=editText.getText().toString();
			if (!"".equals(key) && null != key) {
				key = SearchUtils.encodeUnicode(key);
				getSearchList(key);
			} else {
				editText.setShakeAnimation();
				Toast.makeText(SearchActivity.this, "输入内容不能为空",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}

	}
/**
 * 关键字搜索
 * @param key
 */
	private void getSearchList(String key) {
		myDialog = MyDialog.newInstance(this);
		myDialog.show();
		volleyUtils.getJSONObject(URLUtils.SEARCH + key,
				new CallbackJSONObject() {

					@Override
					public void getJSONObject(JSONObject json) {
						myDialog.dismiss();
						list = jsonUtils.parserJSONObjectToSearchList(json);
						if (list != null && list.size() != 0) {
							adapter.addList(list);
						} else {
							Toast.makeText(SearchActivity.this, "无搜索结果",
									Toast.LENGTH_LONG).show();
						}
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

	public void edittext() {
		editText.setFocusable(true);
		editText.requestFocus();
		onFocusChange(editText.isFocused());

	}

	public void onFocusChange(boolean hasFocus) {
		final boolean isFocus = hasFocus;
		(new Handler()).postDelayed(new Runnable() {
			public void run() {
				InputMethodManager imm = (InputMethodManager) editText
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				if (isFocus) {
					imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				} else {
					imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
				}
			}

		}, 100);
	}
}
