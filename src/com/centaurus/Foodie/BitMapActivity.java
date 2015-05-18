package com.centaurus.Foodie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 地图
 *
 */
public class BitMapActivity extends Activity {
	private TextView cateSerachTv,RimsearchTv,locationTv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bit_map);
		initView();
	}

	public void initView(){
		cateSerachTv=(TextView)this.findViewById(R.id.textView1_poi);
		cateSerachTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(BitMapActivity.this,CateSearchActivity.class);
				startActivity(intent);
				
			}
		});
		RimsearchTv=(TextView)this.findViewById(R.id.textView2_poi);
		RimsearchTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(BitMapActivity.this,RimActivity.class);
				startActivity(intent);
				
			}
		});
		locationTv=(TextView)this.findViewById(R.id.textView3_poi);
		locationTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(BitMapActivity.this,LocationMapActivity.class);
				startActivity(intent);
				
			}
		});
	}
	

}
