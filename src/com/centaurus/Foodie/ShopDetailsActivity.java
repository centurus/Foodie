package com.centaurus.Foodie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.centaurus.map.MapActivity;
import com.centaurus.util.LoadImg;

/**
 * ��������ģ��
 * */
public class ShopDetailsActivity extends Activity {

	// private ShopInfo info = null;
	private LoadImg loadImg;
	// private HttpGetThread http = null;
	// private MyJson myJson = new MyJson();
	// private ArrayList<SignInfo> SignList;na
	// private ArrayList<CommentsInfo> CommentsList;
	// private ArrayList<FoodInfo> FoodList;
	// top�͵��̵�����
	private ImageView mShop_details_back, mShop_details_share,
			mShop_details_off, mShop_details_photo, mShop_details_star;
	private TextView mShop_details_name, mShop_details_money;
	// ������ĵ���ʽ��ť
	private LinearLayout mShop_details_bottom_img1, mShop_details_bottom_img2,
			mShop_details_bottom_img3, mShop_details_bottom_img4;
	// �����������������
	private RelativeLayout mshop_details_address, mshop_details_phone,
			mshop_details_ding, mshop_details_card, mshop_details_quan,
			mshop_details_tuan;
	private TextView mshop_details_address_txt, mshop_details_phone_txt,
			mshop_details_card_txt, mshop_details_quan_txt,
			mshop_details_tuan_txt;
	private ImageView mshop_details_ding_hui, mshop_details_ding_jiang;
	// �����Ƽ���layout
	private RelativeLayout mshop_details_tuijian;
	private TextView mshop_details_tuijian_txt;
	// ������layout
	private RelativeLayout mshop_details_dianping;
	private TextView mshop_dianping_top, mshop_details_dianping_name,
			mshop_details_dianping_txt, mshop_details_dianping_time;
	private ImageView mshop_details_dianping_star;
	// ǩ�����Ե�layout
	private RelativeLayout mshop_details_qiandaoqiang;
	private TextView mshop_qiandaoqiang_top, mshop_details_qiandaoqiang_txt,
			mshop_details_qiandaoqiang_time, mshop_details_qiandaoqiang_cishu;
	private ImageView mshop_details_qiandaoqiang_img;
	// ������Ϣ��layout
	private RelativeLayout mshop_details_qita;
	// ��ҵ긽���layout
	private TextView mshop_fujin_meishi, mshop_fujin_jingdian,
			mshop_fujin_jiudian, mshop_fujin_quanbu;
	// ����ֵ��layout
	private RelativeLayout mshop_details_fendian;
	private TextView mshop_details_fendians_txt;
	// ������ҵ��layout
	private RelativeLayout mshop_details_kanguo;
	// ����popupWindow
	private View parent;
	private PopupWindow popupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shop_details);
		loadImg = new LoadImg(ShopDetailsActivity.this);
		Intent intent = getIntent();
		initView();

	}

	private void initView() {

		// ����ؼ�
		mShop_details_back = (ImageView) findViewById(R.id.Shop_details_back);
		mShop_details_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});

		mShop_details_bottom_img1 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img1);
		mShop_details_bottom_img1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ShopDetailsActivity.this,
						CheckInActivity.class);
				startActivity(intent);

			}
		});
		mShop_details_bottom_img2 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img2);
		mShop_details_bottom_img2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ShopDetailsActivity.this,
						ShopCommentActivity.class);
				startActivity(intent);

			}
		});
		mShop_details_name = (TextView) findViewById(R.id.Shop_details_name);
		mShop_details_photo = (ImageView) findViewById(R.id.Shop_details_photo);
		mShop_details_star = (ImageView) findViewById(R.id.Shop_details_star);
		mShop_details_money = (TextView) findViewById(R.id.Shop_details_money);
		mshop_details_address = (RelativeLayout) findViewById(R.id.shop_details_address);
		mshop_details_phone = (RelativeLayout) findViewById(R.id.shop_details_phone);
		mshop_details_ding = (RelativeLayout) findViewById(R.id.shop_details_ding);
		mshop_details_card = (RelativeLayout) findViewById(R.id.shop_details_card);
		mshop_details_quan = (RelativeLayout) findViewById(R.id.shop_details_quan);
		mshop_details_tuan = (RelativeLayout) findViewById(R.id.shop_details_tuan);
		mshop_details_address_txt = (TextView) findViewById(R.id.shop_details_address_txt);
		mshop_details_address_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				/*Intent intent = new Intent(ShopDetailsActivity.this,
						MapActivity.class);
				startActivity(intent);*/

			}
		});
		mshop_details_phone_txt = (TextView) findViewById(R.id.shop_details_phone_txt);
		mshop_details_card_txt = (TextView) findViewById(R.id.shop_details_card_txt);
		mshop_details_quan_txt = (TextView) findViewById(R.id.shop_details_quan_txt);
		mshop_details_tuan_txt = (TextView) findViewById(R.id.shop_details_tuan_txt);
		mshop_details_ding_hui = (ImageView) findViewById(R.id.shop_details_ding_hui);
		mshop_details_ding_jiang = (ImageView) findViewById(R.id.shop_details_ding_jiang);
		// �����Ƽ���Ϣ�Ŀؼ�����
		mshop_details_tuijian = (RelativeLayout) findViewById(R.id.shop_details_tuijian);
		mshop_details_tuijian_txt = (TextView) findViewById(R.id.shop_details_tuijian_txt);
		// ����
		mshop_details_dianping = (RelativeLayout) findViewById(R.id.shop_details_dianping);
		mshop_dianping_top = (TextView) findViewById(R.id.shop_dianping_top);
		mshop_details_dianping_name = (TextView) findViewById(R.id.shop_details_dianping_name);
		mshop_details_dianping_star = (ImageView) findViewById(R.id.shop_details_dianping_star);
		mshop_details_dianping_txt = (TextView) findViewById(R.id.shop_details_dianping_txt);
		mshop_details_dianping_time = (TextView) findViewById(R.id.shop_details_dianping_time);
		// ǩ������
		mshop_details_qiandaoqiang = (RelativeLayout) findViewById(R.id.shop_details_qiandaoqiang);
		mshop_qiandaoqiang_top = (TextView) findViewById(R.id.shop_qiandaoqiang_top);
		mshop_details_qiandaoqiang_txt = (TextView) findViewById(R.id.shop_details_qiandaoqiang_txt);
		mshop_details_qiandaoqiang_time = (TextView) findViewById(R.id.shop_details_qiandaoqiang_time);
		mshop_details_qiandaoqiang_cishu = (TextView) findViewById(R.id.shop_details_qiandaoqiang_cishu);
		mshop_details_qiandaoqiang_img = (ImageView) findViewById(R.id.shop_details_qiandaoqiang_img);
		// ������Ϣ
		mshop_details_qita = (RelativeLayout) findViewById(R.id.shop_details_qita);
		// ����ҵ긽��
		mshop_fujin_meishi = (TextView) findViewById(R.id.shop_fujin_meishi);
		mshop_fujin_jingdian = (TextView) findViewById(R.id.shop_fujin_jingdian);
		mshop_fujin_jiudian = (TextView) findViewById(R.id.shop_fujin_jiudian);
		mshop_fujin_quanbu = (TextView) findViewById(R.id.shop_fujin_quanbu);
		// �鿴����ֵ�
		mshop_details_fendian = (RelativeLayout) findViewById(R.id.shop_details_fendian);
		mshop_details_fendians_txt = (TextView) findViewById(R.id.shop_details_fendians_txt);
		// ������ҵ���˻�����
		mshop_details_kanguo = (RelativeLayout) findViewById(R.id.shop_details_kanguo);

	}

}
