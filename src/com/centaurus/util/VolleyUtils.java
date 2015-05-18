package com.centaurus.util;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class VolleyUtils {
	
	private RequestQueue mQueue;
	private JsonObjectRequest jsonObjectRequest;
	public RequestQueue newRequestQueue(Context context){
		if(mQueue == null){
			mQueue = Volley.newRequestQueue(context);
		}
		return mQueue;
	}
//通过URL获取webservice的json数据包
	public void getJSONObject(String url,final CallbackJSONObject callbackJSONObject){
		
		jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject json) {
				callbackJSONObject.getJSONObject(json);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
			}
		});
		mQueue.add(jsonObjectRequest);
	}
	public void close(){
		if(mQueue!=null&&jsonObjectRequest!=null){
			mQueue.cancelAll(jsonObjectRequest);
		}
	}
}

