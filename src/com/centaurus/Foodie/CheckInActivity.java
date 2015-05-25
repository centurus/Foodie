package com.centaurus.Foodie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class CheckInActivity extends Activity implements OnClickListener {
	private ImageView checkinBack;
	private TextView checkInOk, checkInUpLoadImg;
	private RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
		checkinBack = (ImageView) findViewById(R.id.checkin_back);
		checkInOk = (TextView) findViewById(R.id.check_in_ok);
		checkInUpLoadImg = (TextView) findViewById(R.id.check_in_upload_img);
		ratingBar=(RatingBar) findViewById(R.id.checkin_ratbar);
		checkInUpLoadImg.setOnClickListener(this);
		checkInOk.setOnClickListener(this);
		checkinBack.setOnClickListener(this);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				
			}
		});
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.checkin_back:
			finish();
			break;

		case R.id.check_in_upload_img:
			Toast.makeText(CheckInActivity.this, "打开图库上传图片", Toast.LENGTH_SHORT)
					.show();
			break;

		case R.id.check_in_ok:
			Toast.makeText(CheckInActivity.this, "签到确定按钮", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}

	}
}
