package com.centaurus.Foodie;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.centaurus.bean.DiscussionPo;
import com.centaurus.bean.FoodContent;
import com.centaurus.bean.Materidl;
import com.centaurus.bean.Step;
import com.centaurus.bean.Tips;
import com.centaurus.db.DBManager;
import com.centaurus.util.BitmapCache;
import com.centaurus.util.CallbackJSONObject;
import com.centaurus.util.JsonUtils;
import com.centaurus.util.SearchUtils;
import com.centaurus.util.ShearedPf;
import com.centaurus.util.URLUtils;
import com.centaurus.util.VolleyUtils;
import com.centaurus.view.MyDialog;
/**
 * 详情界面
 *
 */
public class FoodContentActivity extends Activity implements OnClickListener{
	
	private String id;
	private String imageid;
	private String name;
	private NetworkImageView topimage;
	private NetworkImageView comment_user_image;
	private TextView common_title;
	private TextView food_content_message;
	private TextView favnum_number;
	private TextView create_time;
	private TextView upload_username;
	private TextView favnum_number_text;
	private TextView food_content_comment_number;
	private LinearLayout custom_material_view;
	private LinearLayout custom_practice_view;
	private LinearLayout custom_tips_view;
	private LinearLayout custom_comments_view;
	private VolleyUtils volleyUtils;
	private RequestQueue mQueue;
	private ImageLoader imageLoader;
	private JsonUtils jsonUtils;
	private View count_gone_view;
	private View tips_if_gone;
	private View comments_if_gone;
	private FoodContent food;
	private MyDialog dialog;
	private TextView collect_click;
	private DBManager manager;
	private String user;
	private LinearLayout detailTop;
	private ShearedPf spf=new ShearedPf(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_content);
		init();
		getFoodInfo();
	}
	private void init() {
		new ImageView(this);
		id=getIntent().getStringExtra("id");
		imageid = getIntent().getStringExtra("imageid");
		name = getIntent().getStringExtra("name");
		detailTop=(LinearLayout) findViewById(R.id.detail_top_ll);
		LayoutParams layoutParams = detailTop.getLayoutParams();
		int width = getResources().getDisplayMetrics().widthPixels;
		layoutParams.height = 3 * width / 5;
		detailTop.setLayoutParams(layoutParams);
		common_title=(TextView) this.findViewById(R.id.common_title);
		custom_material_view=(LinearLayout) this.findViewById(R.id.custom_material_view);
		custom_practice_view=(LinearLayout) this.findViewById(R.id.custom_practice_view);
		custom_tips_view=(LinearLayout) this.findViewById(R.id.custom_tips_view);
		custom_comments_view=(LinearLayout) this.findViewById(R.id.custom_comments_view);
		food_content_message=(TextView) this.findViewById(R.id.food_content_message);
		food_content_comment_number=(TextView) this.findViewById(R.id.food_content_comment_number);
		collect_click=(TextView) this.findViewById(R.id.collect_click);
		collect_click.setOnClickListener(this);
		tips_if_gone=this.findViewById(R.id.tips_if_gone);
		comments_if_gone=this.findViewById(R.id.comments_if_gone);
		favnum_number=(TextView) this.findViewById(R.id.favnum_number);
		create_time=(TextView) this.findViewById(R.id.create_time);
		upload_username=(TextView) this.findViewById(R.id.upload_username);
		comment_user_image=(NetworkImageView) this.findViewById(R.id.comment_user_image);
		favnum_number_text=(TextView) this.findViewById(R.id.favnum_number_text);
		topimage=(NetworkImageView) this.findViewById(R.id.food_content_top_image);
		count_gone_view = this.findViewById(R.id.count_gone_view);
		common_title.setText(SearchUtils.changeString(name));
		volleyUtils = new VolleyUtils();
		mQueue = volleyUtils.newRequestQueue(this);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}
	
	private void getFoodInfo(){
		jsonUtils = new JsonUtils();
		dialog = MyDialog.newInstance(this);
		dialog.show();
		System.out.println("--------URLUtils.IMAGE_URL+imageid----"+URLUtils.IMAGE_URL+imageid);
		topimage.setImageUrl(URLUtils.IMAGE_URL+imageid, imageLoader);
		volleyUtils.getJSONObject(URLUtils.RECIPEINFO+id, new CallbackJSONObject() {
			@Override
			public void getJSONObject(JSONObject json) {
				// TODO Auto-generated method stub
				String message = jsonUtils.parserMessage(json);
				food_content_message.setText(message);
			}
		});
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient conn = new DefaultHttpClient();
				HttpPost post = new HttpPost(URLUtils.COUNT_REFER_BY_CONTENTID+id);
				HttpResponse response =null;
				try {
					response=conn.execute(post);
					if(response.getStatusLine().getStatusCode()==200){
						int num = Integer.parseInt(EntityUtils.toString(response.getEntity()));
						if(num!=0){
							count_gone_view.setVisibility(View.VISIBLE);
							favnum_number_text.setText(num+"");
						}else{
							count_gone_view.setVisibility(View.GONE);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		volleyUtils.getJSONObject(URLUtils.VIEWSERVLET+id, new CallbackJSONObject() {
			
			@Override
			public void getJSONObject(JSONObject json) {
				// TODO Auto-generated method stub
				food=jsonUtils.parserFoodContent(json);
				initView();
			}
		});
	}
	protected void initView() {
		
		
		LayoutInflater inflater = LayoutInflater.from(this);
		favnum_number.setText(food.getCollectNum());
		upload_username.setText(food.getEditname());
		create_time.setText(food.getGettime());
		comment_user_image.setImageUrl(URLUtils.IMAGE_URL+food.getImageid(), imageLoader);
		List<Materidl> mlist=food.getMateridlList();
		List<DiscussionPo> dlist = food.getDiscussionPoList();
		List<Step> plist = food.getStepList();
		List<Tips> tlist = food.getTipsList();
		for(int i=0;i<mlist.size();i++){
			TextView materail_view_name;
			TextView materail_view_dosage;
			Materidl materidl=mlist.get(i);
			View materailview =inflater.inflate(R.layout.material_view, null, false);
			materail_view_name=(TextView) materailview.findViewById(R.id.materail_view_name);
			materail_view_dosage=(TextView) materailview.findViewById(R.id.materail_view_dosage);
			custom_material_view.addView(materailview,i);
			materail_view_name.setText(materidl.getName());
			materail_view_dosage.setText(materidl.getDosage());
		}
		if(tlist!=null&&tlist.size()!=0){
			TextView tips_view_content;
			for(int i=0;i<tlist.size();i++){
				View tipsview=inflater.inflate(R.layout.tips_view, null, false);
				tips_view_content=(TextView) tipsview.findViewById(R.id.tips_view_content);
				custom_tips_view.addView(tipsview, i);
				tips_view_content.setText((i+1)+"、 "+tlist.get(i).getDetails());
			}
		}else{
			tips_if_gone.setVisibility(View.GONE);
		}
		for(int i=0;i<plist.size();i++){
			View parciceview;
			NetworkImageView parcice_view_image;
			TextView parcice_view_step;
			parciceview=inflater.inflate(R.layout.practice_view, null, false);
			parcice_view_image  = (NetworkImageView) parciceview.findViewById(R.id.parcice_view_image);
			parcice_view_step=(TextView) parciceview.findViewById(R.id.parcice_view_step);
			custom_practice_view.addView(parciceview, i);
			Step step = plist.get(i);
			parcice_view_image.setImageUrl(URLUtils.IMAGE_URL+step.getImageid(), imageLoader);
			parcice_view_step.setText((i+1)+"、 "+step.getDetails());
		}
		if(dlist!=null&& dlist.size()!=0){
			food_content_comment_number.setText("全部"+dlist.size()+"条评论");
			NetworkImageView comments_user_image;
			TextView comments_user_name;
			TextView comments_time;
			TextView comments_view_content;
			for(int i=0;i<dlist.size();i++){
				DiscussionPo discussionPo = dlist.get(i);
				View commentview=inflater.inflate(R.layout.comments_view, null, false);
				comments_user_image=(NetworkImageView) commentview.findViewById(R.id.comments_user_image);
				comments_user_name=(TextView) commentview.findViewById(R.id.comments_user_name);
				comments_time=(TextView) commentview.findViewById(R.id.comments_time);
				comments_view_content=(TextView) commentview.findViewById(R.id.comments_view_content);
				custom_comments_view.addView(commentview, i);
				comments_user_image.setImageUrl(URLUtils.IMAGE_URL+discussionPo.getPic(), imageLoader);
				comments_user_name.setText(discussionPo.getUsertitle());
				comments_time.setText(discussionPo.getDistime());
				comments_view_content.setText(discussionPo.getDicussion());
			}
		}else{
			comments_if_gone.setVisibility(View.GONE);
		}
		dialog.dismiss();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		manager=new DBManager(this);
		if(collect_click.getText().toString().equals("添加收藏")){
			ContentValues values=new ContentValues();
			if(spf.getisuser()==1){
				user=spf.getUsername();
				values.put("user", spf.getUsername());
			}
			values.put("name", food.getName());
			values.put("_id", id);
			values.put("content", food.getContent());
			values.put("imageid", food.getImageid());
			if(topimage.getDrawable()!=null){
				Bitmap bm = drawableToBitmap(topimage.getDrawable());
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
				byte[] imagebytes=  baos.toByteArray();
				values.put("image", imagebytes);
			}
		    long id=manager.insert(values);
		    Drawable drawable = getResources().getDrawable(R.drawable.un_favorites);
		    if(id==-1){
		    	Toast.makeText(this, "收藏失败，已收藏", Toast.LENGTH_LONG).show();
		    }else{
		    	Toast.makeText(this, "收藏成功", Toast.LENGTH_LONG).show();
		    }
		    collect_click.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
		    collect_click.setText("取消收藏");
		}else{
			int j=manager.deletes(id,user);
			if(j==1){
			Toast.makeText(this, "删除成功", Toast.LENGTH_LONG).show();
			Drawable drawable2=getResources().getDrawable(R.drawable.favorites);
			collect_click.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable2, null, null);
			collect_click.setText("添加收藏");
			}else{
				Toast.makeText(this, "删除失败", Toast.LENGTH_LONG).show();
			}
		}
	}
	 public static Bitmap drawableToBitmap(Drawable drawable) {
		          // 取 drawable 的长宽
		          int w = drawable.getIntrinsicWidth();
		         int h = drawable.getIntrinsicHeight();
		          // 取 drawable 的颜色格式
		          Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
		                  : Bitmap.Config.RGB_565;
		          // 建立对应 bitmap
		         Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		         // 建立对应 bitmap 的画布
		         Canvas canvas = new Canvas(bitmap);
		         drawable.setBounds(0, 0, w, h);
		         // 把 drawable 内容画到画布中
		         drawable.draw(canvas);
		         return bitmap;
	    }
	
}
