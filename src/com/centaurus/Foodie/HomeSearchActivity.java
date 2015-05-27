package com.centaurus.Foodie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.centaurus.adpter.MyListViewAdapter;
import com.centaurus.adpter.SearchMainAdapter;
import com.centaurus.adpter.SearchMoreAdapter;
import com.centaurus.bean.FoodContent;
import com.centaurus.util.CallbackJSONObject;
import com.centaurus.util.JsonUtils;
import com.centaurus.util.Model;
import com.centaurus.util.URLUtils;
import com.centaurus.util.VolleyUtils;
import com.centaurus.view.MyDialog;

/**
 * 首页带筛选功能
 * 
 * @author wlp
 * 
 */

public class HomeSearchActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private ListView mListView, toplist, rightlist, leftlist2, middlelist2,
			leftlist1, middlelist1;
	private ImageView mShoplist_back;
	private LinearLayout mtopLl, mShoplist_mainlist2, mShoplist_mainlist1;
	private TextView mleftTextBtn, mMiddleTextBtn, mrightTextBtn;
	private SearchMoreAdapter topadapter = null;
	private SearchMoreAdapter rightadapter = null;
	private SearchMoreAdapter middleadapter1 = null;
	private SearchMainAdapter leftdapter1 = null;
	private SearchMoreAdapter middkeadapter2 = null;
	private SearchMainAdapter leftadapter2 = null;
	private ImageView mSearch_city_img;
	private TextView mShoplist_title_txt;
	private String url = null;
	private boolean toplistview = false;
	private boolean threelistview = false;
	private boolean mainlistview1 = false;
	private boolean mainlistview2 = false;
	private List<Map<String, Object>> mainList1;
	private List<Map<String, Object>> mainList2;
	private MyListViewAdapter adapter;
	private MyDialog dialog;
	private JsonUtils jsonUtils;
	private VolleyUtils volleyUtils;
	private Button morebutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_search);
		initView();
	}

	private void initView() {
		mShoplist_back = (ImageView) findViewById(R.id.Shoplist_back);
		mtopLl = (LinearLayout) findViewById(R.id.Shoplist_shanghuleixing);
		mShoplist_title_txt = (TextView) findViewById(R.id.Shoplist_title_txt);
		mSearch_city_img = (ImageView) findViewById(R.id.Search_city_img);
		mleftTextBtn = (TextView) findViewById(R.id.Shoplist_title_textbtn1);
		mMiddleTextBtn = (TextView) findViewById(R.id.Shoplist_title_textbtn2);
		mrightTextBtn = (TextView) findViewById(R.id.Shoplist_title_textbtn3);
		toplist = (ListView) findViewById(R.id.Shoplist_toplist);
		mShoplist_mainlist1 = (LinearLayout) findViewById(R.id.Shoplist_mainlist1);
		leftlist1 = (ListView) findViewById(R.id.Shoplist_onelist1);
		middlelist1 = (ListView) findViewById(R.id.Shoplist_twolist1);
		mShoplist_mainlist2 = (LinearLayout) findViewById(R.id.Shoplist_mainlist2);
		leftlist2 = (ListView) findViewById(R.id.Shoplist_onelist2);
		middlelist2 = (ListView) findViewById(R.id.Shoplist_twolist2);
		rightlist = (ListView) findViewById(R.id.Shoplist_threelist);
		mListView = (ListView) findViewById(R.id.ShopListView);

		MyOnclickListener mOnclickListener = new MyOnclickListener();
		mShoplist_back.setOnClickListener(mOnclickListener);
		// mtopLl.setOnClickListener(mOnclickListener);
		mleftTextBtn.setOnClickListener(mOnclickListener);
		mMiddleTextBtn.setOnClickListener(mOnclickListener);
		mrightTextBtn.setOnClickListener(mOnclickListener);
		// -----------------------------------------------------------------
		initModel1();
		initModel2();
		leftdapter1 = new SearchMainAdapter(HomeSearchActivity.this, mainList1,
				R.layout.shop_list1_item, false);
		leftdapter1.setSelectItem(0);
		leftadapter2 = new SearchMainAdapter(HomeSearchActivity.this,
				mainList2, R.layout.shop_list1_item, true);
		leftadapter2.setSelectItem(0);
		topadapter = new SearchMoreAdapter(HomeSearchActivity.this,
				Model.TOP_TXT, R.layout.shop_list2_item);
		rightadapter = new SearchMoreAdapter(HomeSearchActivity.this,
				Model.Right_TXT, R.layout.shop_list2_item);
		toplist.setAdapter(topadapter);
		leftlist1.setAdapter(leftdapter1);
		leftlist2.setAdapter(leftadapter2);
		rightlist.setAdapter(rightadapter);
		initMiddleAdapter1(Model.LEFT_RIGHT_PLACE[0]);
		initMiddleAdapter2(Model.MIDDLE_RIGHT_TXT[0]);

		initLiseViewOnItemClicListener();

		// --访问数据------
		url = URLUtils.CHOSEN_RECIPE;
		adapter = new MyListViewAdapter(this);
		morebutton = new Button(this);
		morebutton.setText("更多");
		morebutton.setGravity(Gravity.CENTER);
		mListView.addFooterView(morebutton);
		mListView.setAdapter(adapter);
		morebutton.setOnClickListener(this);
		mListView.setOnItemClickListener(this);
		initDada();
	}

	/**
	 * 注册listView item的监听
	 */
	private void initLiseViewOnItemClicListener() {
		TopListOnItemclick topListOnItemclick = new TopListOnItemclick();
		LeftListOnItemClick1 leftlistclick1 = new LeftListOnItemClick1();
		MiddleListOnItemclick1 middlelistclick1 = new MiddleListOnItemclick1();
		LeftListOnItemClick2 leftlistclick2 = new LeftListOnItemClick2();
		MiddleListonItemclick2 middlelistclick2 = new MiddleListonItemclick2();
		RigntListOnItemclick rightListOnItemClick = new RigntListOnItemclick();
		toplist.setOnItemClickListener(topListOnItemclick);
		leftlist1.setOnItemClickListener(leftlistclick1);
		middlelist1.setOnItemClickListener(middlelistclick1);
		leftlist2.setOnItemClickListener(leftlistclick2);
		middlelist2.setOnItemClickListener(middlelistclick2);
		rightlist.setOnItemClickListener(rightListOnItemClick);
	}

	private String moreids;
	private String[] idss;
	private List<FoodContent> list = new ArrayList<FoodContent>();

	/**
	 * 加载数据
	 */
	private void initDada() {
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

	private static final int MORE_LIMIT = 10;
	private int number = 0;

	private String getCurrentids() {
		String currentids = "";
		for (int i = number; i < MORE_LIMIT + number; i++) {
			currentids += idss[i] + ",";
		}
		number += MORE_LIMIT;
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

	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			if (mID == R.id.Shoplist_back) {
				HomeSearchActivity.this.finish();
			}
			if (mID == R.id.Shoplist_shanghuleixing) {
				if (!toplistview) {
					mSearch_city_img
							.setImageResource(R.drawable.title_arrow_up);
					toplist.setVisibility(View.VISIBLE);
					topadapter.notifyDataSetChanged();
					toplistview = true;
				} else {
					mSearch_city_img.setImageResource(R.drawable.search_city);
					toplist.setVisibility(View.GONE);
					toplistview = false;
				}
			} else {
				mSearch_city_img.setImageResource(R.drawable.search_city);
				toplist.setVisibility(View.GONE);
				toplistview = false;
			}
			if (mID == R.id.Shoplist_title_textbtn3) {
				Drawable drawable = null;
				if (!threelistview) {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_up_black);
					rightlist.setVisibility(View.VISIBLE);
					rightadapter.notifyDataSetChanged();
					threelistview = true;
				} else {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_down_black);
					rightlist.setVisibility(View.GONE);
					threelistview = false;
				}
				// 这一步必须要做,否则不会显示.
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mrightTextBtn.setCompoundDrawables(null, null, drawable, null);
			} else {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mrightTextBtn.setCompoundDrawables(null, null, drawable, null);
				rightlist.setVisibility(View.GONE);
				threelistview = false;

			}
			if (mID == R.id.Shoplist_title_textbtn2) {
				Drawable drawable = null;
				if (!mainlistview2) {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_up_black);
					mShoplist_mainlist2.setVisibility(View.VISIBLE);
					middkeadapter2.notifyDataSetChanged();
					mainlistview2 = true;
				} else {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_down_black);
					mShoplist_mainlist2.setVisibility(View.GONE);
					mainlistview2 = false;
				}
				// 这一步必须要做,否则不会显示.
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mMiddleTextBtn.setCompoundDrawables(null, null, drawable, null);
			} else {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mMiddleTextBtn.setCompoundDrawables(null, null, drawable, null);
				mShoplist_mainlist2.setVisibility(View.GONE);
				mainlistview2 = false;
			}
			if (mID == R.id.Shoplist_title_textbtn1) {
				Drawable drawable = null;
				if (!mainlistview1) {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_up_black);
					mShoplist_mainlist1.setVisibility(View.VISIBLE);
					middleadapter1.notifyDataSetChanged();
					mainlistview1 = true;
				} else {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_down_black);
					mShoplist_mainlist1.setVisibility(View.GONE);
					mainlistview1 = false;
				}
				// 这一步必须要做,否则不会显示.
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mleftTextBtn.setCompoundDrawables(null, null, drawable, null);
			} else {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mleftTextBtn.setCompoundDrawables(null, null, drawable, null);
				mShoplist_mainlist1.setVisibility(View.GONE);
				mainlistview1 = false;
			}
		}
	};

	private class TopListOnItemclick implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			topadapter.setSelectItem(arg2);
			mSearch_city_img.setImageResource(R.drawable.search_city);
			mShoplist_title_txt.setText(Model.TOP_TXT[arg2]);
			toplist.setVisibility(View.GONE);
			toplistview = false;
		}
	}

	private class LeftListOnItemClick1 implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			initMiddleAdapter1(Model.LEFT_RIGHT_PLACE[arg2]);
			leftdapter1.setSelectItem(arg2);
			leftdapter1.notifyDataSetChanged();
		}
	}

	private class MiddleListOnItemclick1 implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			middleadapter1.setSelectItem(arg2);
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_arrow_down_black);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),
					drawable.getMinimumHeight());
			mleftTextBtn.setCompoundDrawables(null, null, drawable, null);
			int position = leftdapter1.getSelectItem();
			mleftTextBtn.setText(Model.LEFT_RIGHT_PLACE[position][arg2]);
			mShoplist_mainlist1.setVisibility(View.GONE);
			mainlistview1 = false;
		}
	}

	private class LeftListOnItemClick2 implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			initMiddleAdapter2(Model.MIDDLE_RIGHT_TXT[arg2]);
			leftadapter2.setSelectItem(arg2);
			leftadapter2.notifyDataSetChanged();
		}
	}

	private class MiddleListonItemclick2 implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			middkeadapter2.setSelectItem(arg2);
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_arrow_down_black);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),
					drawable.getMinimumHeight());
			mMiddleTextBtn.setCompoundDrawables(null, null, drawable, null);
			int position = leftadapter2.getSelectItem();
			mMiddleTextBtn.setText(Model.MIDDLE_RIGHT_TXT[position][arg2]);
			mShoplist_mainlist2.setVisibility(View.GONE);
			mainlistview2 = false;
		}
	}

	private class RigntListOnItemclick implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			rightadapter.setSelectItem(arg2);
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_arrow_down_black);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),
					drawable.getMinimumHeight());
			mrightTextBtn.setCompoundDrawables(null, null, drawable, null);
			mrightTextBtn.setText(Model.Right_TXT[arg2]);
			rightlist.setVisibility(View.GONE);
			threelistview = false;
		}
	}

	private void initModel1() {
		mainList1 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < Model.LEFT_LEFT_PLACE.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("txt", Model.LEFT_LEFT_PLACE[i]);
			mainList1.add(map);
		}
	}

	private void initModel2() {
		mainList2 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < Model.MIDDLE_LEFT_TXT.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("img", Model.MIDDLE_LEFT_IMG[i]);
			map.put("txt", Model.MIDDLE_LEFT_TXT[i]);
			mainList2.add(map);
		}
	}

	private void initMiddleAdapter1(String[] array) {
		middleadapter1 = new SearchMoreAdapter(HomeSearchActivity.this, array,
				R.layout.shop_list2_item);
		middlelist1.setAdapter(middleadapter1);
		middleadapter1.notifyDataSetChanged();
	}

	private void initMiddleAdapter2(String[] array) {
		middkeadapter2 = new SearchMoreAdapter(HomeSearchActivity.this, array,
				R.layout.shop_list2_item);
		middlelist2.setAdapter(middkeadapter2);
		middkeadapter2.notifyDataSetChanged();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (toplistview) {
				mSearch_city_img.setImageResource(R.drawable.search_city);
				toplist.setVisibility(View.GONE);
				toplistview = false;
			} else if (threelistview) {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mrightTextBtn.setCompoundDrawables(null, null, drawable, null);
				rightlist.setVisibility(View.GONE);
				threelistview = false;
			} else if (mainlistview1) {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mleftTextBtn.setCompoundDrawables(null, null, drawable, null);
				mShoplist_mainlist1.setVisibility(View.GONE);
				mainlistview1 = false;
			} else if (mainlistview2) {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mMiddleTextBtn.setCompoundDrawables(null, null, drawable, null);
				mShoplist_mainlist2.setVisibility(View.GONE);
				mainlistview2 = false;
			}

		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(HomeSearchActivity.this,
				ShopDetailsActivity.class);
		HomeSearchActivity.this.startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		getMoreInfo();

	}

}
