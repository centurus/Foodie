package com.centaurus.map;

import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.centaurus.Foodie.R;
import com.centaurus.Foodie.R.drawable;
import com.centaurus.Foodie.R.id;
import com.centaurus.Foodie.R.layout;
import com.centaurus.Foodie.R.menu;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MapActivity extends Activity {

	private MapView mMapView;
	private BaiduMap fodMap;
	// ��λ���
	private LocationClient mLocationClient;
	private MyLocationListener mLocationListener;
	public boolean isFirstIn = true;
	private double mLatitude;
	private double mLongtitude;
	// marker���
	private BitmapDescriptor mMarker;
	private RelativeLayout mMarkerLy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("tanshuai", "OnCreate");
		super.onCreate(savedInstanceState);
		// ��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
		// ע��÷���Ҫ��setContentView����֮ǰʵ��
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.main_map);
		initView();
		// ��ʼ����λ
		initLocation();
		// ��ʼ��������
		initMarker();
		// ���ø��������¼�
		fodMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				Bundle extraInfo = marker.getExtraInfo();
				MapInfo info = (MapInfo) extraInfo.getSerializable("info");
				ImageView iv = (ImageView) mMarkerLy
						.findViewById(R.id.id_info_img);
				TextView distance = (TextView) mMarkerLy
						.findViewById(R.id.id_info_distance);
				TextView name = (TextView) mMarkerLy
						.findViewById(R.id.id_info_name);
				TextView zan = (TextView) mMarkerLy
						.findViewById(R.id.id_info_zan);
				iv.setImageResource(info.getImgId());
				distance.setText(info.getDistance());
				name.setText(info.getName());
				zan.setText(info.getZan() + "");

				/*
				 * InfoWindow infoWindow; TextView tv = new TextView(context);
				 * tv.setBackgroundResource(R.drawable.location_tips);
				 * tv.setPadding(30, 20, 30, 50); tv.setText(info.getName());
				 * tv.setTextColor(Color.parseColor("#ffffff"));
				 * 
				 * final LatLng latLng = marker.getPosition(); Point p =
				 * fodMap.getProjection().toScreenLocation(latLng); p.y -= 47;
				 * LatLng ll = fodMap.getProjection().fromScreenLocation(p);
				 * 
				 * infoWindow = new InfoWindow(tv, ll, new
				 * OnInfoWindowClickListener() {
				 * 
				 * @Override public void onInfoWindowClick() {
				 * mBaiduMap.hideInfoWindow(); } });
				 * fodMap.showInfoWindow(infoWindow);
				 */
				mMarkerLy.setVisibility(View.VISIBLE);
				return true;
			}
		});
		fodMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}

			@Override
			public void onMapClick(LatLng arg0) {
				mMarkerLy.setVisibility(View.GONE);

			}
		});
	}

	private void initMarker() {
		mMarker = BitmapDescriptorFactory.fromResource(R.drawable.maker);
		mMarkerLy = (RelativeLayout) findViewById(R.id.id_marker_ly);
	}

	private void initLocation() {

		mLocationClient = new LocationClient(this);
		mLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);
		LocationClientOption option = new LocationClientOption();
		// option=new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setIsNeedAddress(true);
		option.setOpenGps(true);
		option.setLocationMode(LocationMode.Hight_Accuracy);
		option.setScanSpan(1000);
		mLocationClient.setLocOption(option);
	}

	private void initView() {
		// TODO Auto-generated method stub
		mMapView = (MapView) findViewById(R.id.id_bmapView);
		fodMap = mMapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
		fodMap.setMapStatus(msu);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		Log.i("tanshuai", "OnResume");
		super.onResume();
		// ��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onResume();
	}

	@Override
	protected void onStart() {
		Log.i("tanshuai", "OnStart");
		super.onStart();
		// ������λ
		fodMap.setMyLocationEnabled(true);
		if (!mLocationClient.isStarted())
			mLocationClient.start();
		Log.d("tanshuai", "�Ƿ�����:" + mLocationClient.isStarted());
	}

	@Override
	protected void onPause() {
		Log.i("tanshuai", "OnPause");
		super.onPause();
		// ��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���
		// mMapView.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
		// ֹͣ��λ
		fodMap.setMyLocationEnabled(false);
		mLocationClient.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar w1ill
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.location:
			centerToMylocation();
			break;
		case R.id.id_add_overlay:
			addOverlays(MapInfo.infos);
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * 
	 * ���marker.����Ҫ��ӷ�������Ҫ��jasonת��Ϊһ��list��������
	 */
	private void addOverlays(List<MapInfo> infos) {
		fodMap.clear();
		LatLng latLng = null;
		Marker marker = null;
		OverlayOptions options;
		for (MapInfo info : infos) {
			// ��γ��
			latLng = new LatLng(info.getLatitude(), info.getLongtitude());
			// ͼ��
			options = new MarkerOptions().position(latLng).icon(mMarker)
					.zIndex(5);
			marker = (Marker) fodMap.addOverlay(options);
			Bundle arg0 = new Bundle();
			arg0.putSerializable("info", info);
			marker.setExtraInfo(arg0);
		}

		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		fodMap.setMapStatus(msu);

	}

	private void centerToMylocation() {

		LatLng latLng = new LatLng(mLatitude, mLongtitude);
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		fodMap.animateMapStatus(msu);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		@SuppressLint("NewApi")
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.main_map, container,
					false);
			return rootView;
		}
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {

			MyLocationData data = new MyLocationData.Builder()//
					.accuracy(location.getRadius())//
					.latitude(location.getLatitude())//
					.longitude(location.getLongitude()).build();
			fodMap.setMyLocationData(data);

			mLatitude = location.getLatitude();
			mLongtitude = location.getLongitude();
			if (isFirstIn) {
				LatLng latLng = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
				fodMap.animateMapStatus(msu);
				isFirstIn = false;
			}
		}
	}
}