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
	private RadioButton main_tab_home, main_tab_catagory,

	main_my;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_under);
		initTab();
		init();
		//ExitManager.getInstance().addActivity(this);
	}

	public void init() {
		main_tab_home = (RadioButton) findViewById(R.id.main_tab_home);
		main_tab_catagory = (RadioButton) findViewById(R.id.main_tab_order);
		main_my = (RadioButton) findViewById(R.id.main_my);
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

		main_my.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("main_my");

			}
		});

	}

	public void initTab() {
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("home").setIndicator("home")
				.setContent(new Intent(this, HomeSearchActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("catagory").setIndicator("catagory")
				.setContent(new Intent(this, MenuOrderActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("main_my").setIndicator("main_my")
				.setContent(new Intent(this, MyActivity.class)));

	}

}