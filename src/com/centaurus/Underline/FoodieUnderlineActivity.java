package com.centaurus.Underline;

import com.centaurus.Foodie.BuyActivity;
import com.centaurus.Foodie.HomeActivity;
import com.centaurus.Foodie.MineActivity;
import com.centaurus.Foodie.MoreActivity;
import com.centaurus.Foodie.R;
import com.centaurus.Foodie.R.id;
import com.centaurus.Foodie.R.layout;
import com.centaurus.Foodie.R.string;
import com.centaurus.map.MapActivity;
import com.centaurus.util.ExitManager;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;

public class FoodieUnderlineActivity extends TabActivity {
	TabHost tabHost;
	private RadioButton main_tab_home, main_tab_catagory, main_tab_car,
			main_tab_buy, main_tab_more;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_under);
        initTab();
        init();
        ExitManager.getInstance().addActivity(this);
    }
    
    public void init(){
    	main_tab_home=(RadioButton)findViewById(R.id.main_tab_home);
    	main_tab_catagory = (RadioButton) findViewById(R.id.main_tab_catagory);
		main_tab_car = (RadioButton) findViewById(R.id.main_tab_car);
		main_tab_buy = (RadioButton) findViewById(R.id.main_tab_buy);
		main_tab_more = (RadioButton) findViewById(R.id.main_tab_more);
		main_tab_home.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("home");
				//tabHost.setCurrentTabByTag("RestInfo");

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
		main_tab_buy.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("buy");

			}
		});
		main_tab_more.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("more");

			}
		});
    }
    
    public void initTab(){
    	tabHost=getTabHost();
    	tabHost.addTab(tabHost.newTabSpec("home").setIndicator("home")
				.setContent(new Intent(this, HomeActivity.class)));
    	tabHost.addTab(tabHost.newTabSpec("catagory").setIndicator("catagory")
				.setContent(new Intent(this, MapActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("car").setIndicator("car")
				.setContent(new Intent(this, MineActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("buy").setIndicator("buy")
				.setContent(new Intent(this, BuyActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("more").setIndicator("more")
				.setContent(new Intent(this, MoreActivity.class)));
    }
    
    public boolean dispatchKeyEvent( KeyEvent event) {
		int keyCode=event.getKeyCode();
	      if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (event.getRepeatCount() == 0) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						FoodieUnderlineActivity.this);
				alertDialog.setTitle(FoodieUnderlineActivity.this
						.getString(R.string.app_close));
				alertDialog.setPositiveButton(FoodieUnderlineActivity.this
						.getString(R.string.btn_ok),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								ExitManager.getInstance().exit();
							}
						});
				alertDialog.setNegativeButton(FoodieUnderlineActivity.this
						.getString(R.string.btn_cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				alertDialog.show();
			}
		}
		return super.dispatchKeyEvent(event);
	}

}