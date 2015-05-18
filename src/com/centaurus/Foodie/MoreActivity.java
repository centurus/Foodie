package com.centaurus.Foodie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;

public class MoreActivity extends Activity {
	private TableRow myfavorite;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_more);
		myfavorite=(TableRow) findViewById(R.id.myfav);
		//收藏夹
		myfavorite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MoreActivity.this,MyCollectActivity.class);
				startActivity(intent);
			}
		});
		//搜索
		findViewById(R.id.search_tv).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MoreActivity.this, SearchActivity.class);
				startActivity(intent);
			}
		});
	}
}
