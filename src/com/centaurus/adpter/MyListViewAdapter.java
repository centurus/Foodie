package com.centaurus.adpter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.centaurus.Foodie.R;
import com.centaurus.bean.FoodContent;
import com.centaurus.util.BitmapCache;
import com.centaurus.util.URLUtils;
import com.centaurus.util.VolleyUtils;

public class MyListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private ImageLoader mImageLoader;
	private VolleyUtils volleyUtils;
	private RequestQueue mQueue;
	private List<FoodContent> list = new ArrayList<FoodContent>();

	public MyListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		volleyUtils = new VolleyUtils();
		mQueue = volleyUtils.newRequestQueue(context);
		mImageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	public void addList(List<FoodContent> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public FoodContent getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.chosen_recipe_listview_item1, container, false);
			holder.imageView1_holder = (NetworkImageView) convertView
					.findViewById(R.id.networkimage_chosen_recipe_listview);
			holder.textView1_holder = (TextView) convertView
					.findViewById(R.id.textView1_chosen_recipe_listview);
			holder.textView2_holder = (TextView) convertView
					.findViewById(R.id.textView2_chosen_recipe_listview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView1_holder.setText(getItem(position).getName());
		holder.textView2_holder.setText(getItem(position).getContent());
		holder.imageView1_holder.setImageUrl(
				URLUtils.IMAGE_URL + getItem(position).getImageid(),
				mImageLoader);
		return convertView;
	}

}

class ViewHolder {
	ImageView image;
	NetworkImageView imageView1_holder;
	TextView textView1_holder, textView2_holder;
}