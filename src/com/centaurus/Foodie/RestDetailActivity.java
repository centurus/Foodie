package com.centaurus.Foodie;

import com.centaurus.Foodie.R.string;

import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RestDetailActivity extends Activity {
	public TextView textView1;
	public String detailRantName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rest_info);
		textView1=(TextView) findViewById(R.id.textView1);
		detailRantName=getIntent().getStringExtra(ACCOUNT_SERVICE);
		textView1.setText(detailRantName);
	}
 
}
