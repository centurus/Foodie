package com.centaurus.Foodie;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.centaurus.util.ExitManager;

public class FoodieUnderlineActivity extends TabActivity {
	TabHost tabHost;
	private RadioButton main_tab_home, main_tab_catagory, main_tab_car,
	main_my, main_tab_more;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_under);
		initTab();
		init();
		ExitManager.getInstance().addActivity(this);
	}

	public void init() {
		main_tab_home = (RadioButton) findViewById(R.id.main_tab_home);
		main_tab_catagory = (RadioButton) findViewById(R.id.main_tab_catagory);
		main_tab_car = (RadioButton) findViewById(R.id.main_tab_car);
		main_my = (RadioButton) findViewById(R.id.main_my);
		main_tab_more = (RadioButton) findViewById(R.id.main_tab_more);
		main_tab_home.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("home");

			}
		});

		main_tab_catagory.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("catagory");

			}
		});
		main_tab_car.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("car");

			}
		});
		main_my.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("main_my");

			}
		});
		main_tab_more.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("more");

			}
		});
	}

	public void initTab() {
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("home").setIndicator("home")
				.setContent(new Intent(this, HomeSearchActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("catagory").setIndicator("catagory")
				.setContent(new Intent(this, BitMapActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("main_my").setIndicator("main_my")
				.setContent(new Intent(this, MyActivity.class)));
//		tabHost.addTab(tabHost.newTabSpec("more").setIndicator("more")
//				.setContent(new Intent(this, MoreActivity.class)));
	}
	


}