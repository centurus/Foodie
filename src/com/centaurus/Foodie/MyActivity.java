package com.centaurus.Foodie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 我的模块
 * */

public class MyActivity extends Activity {

	private TextView mMy_register, mMy_login, mMy_address, mMy_checkin,
			mMy_comment;
	private LinearLayout mMy_messagebtn;
	private LinearLayout mMy_list_tuangou, my_favorite_ll,
			mMy_list_tuangoushoucang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		initView();
	}

	private void initView() {
		mMy_register = (TextView) findViewById(R.id.My_register);
		mMy_login = (TextView) findViewById(R.id.My_login);
		mMy_address = (TextView) findViewById(R.id.My_address);
		mMy_checkin = (TextView) findViewById(R.id.My_checkin);
		mMy_comment = (TextView) findViewById(R.id.My_comment);
		mMy_messagebtn = (LinearLayout) findViewById(R.id.My_messagebtn);
		mMy_list_tuangou = (LinearLayout) findViewById(R.id.My_list_tuangou);
		my_favorite_ll = (LinearLayout) findViewById(R.id.my_favorite_ll);
		mMy_list_tuangoushoucang = (LinearLayout) findViewById(R.id.My_list_tuangoushoucang);
		MyOnclickListener mOnclickListener = new MyOnclickListener();
		mMy_register.setOnClickListener(mOnclickListener);
		mMy_login.setOnClickListener(mOnclickListener);
		mMy_address.setOnClickListener(mOnclickListener);
		mMy_checkin.setOnClickListener(mOnclickListener);
		mMy_comment.setOnClickListener(mOnclickListener);
		mMy_messagebtn.setOnClickListener(mOnclickListener);
		mMy_list_tuangou.setOnClickListener(mOnclickListener);
		my_favorite_ll.setOnClickListener(mOnclickListener);
		mMy_list_tuangoushoucang.setOnClickListener(mOnclickListener);
	}

	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			switch (mID) {
			case R.id.My_checkin:
				Intent checkInIntent = new Intent(MyActivity.this,
						CheckInActivity.class);
				startActivity(checkInIntent);

				break;
			case R.id.My_comment:
				
				
				
				break;
			case R.id.my_favorite_ll:
				Intent favoriteIntent = new Intent(MyActivity.this,
						MyCollectActivity.class);
				startActivity(favoriteIntent);
				break;
			case R.id.My_register:
				Intent intent = new Intent(MyActivity.this,
						RegistrationActivity.class);
				startActivity(intent);
				break;
			case R.id.My_login:
				Intent intent2 = new Intent(MyActivity.this,
						LoginActivity.class);
				startActivity(intent2);
				break;
			}
		}

	}
}
