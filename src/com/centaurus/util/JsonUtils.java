package com.centaurus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.centaurus.bean.DiscussionPo;
import com.centaurus.bean.FoodContent;
import com.centaurus.bean.Materidl;
import com.centaurus.bean.Step;
import com.centaurus.bean.Tips;


public class JsonUtils {
	
	public Map<String,String> parserShake(JSONObject json){
		Map<String,String> map = new HashMap<String, String>();
		try {
			map.put("imageid", json.getString("imageid"));
			map.put("id", json.getString("id"));
			map.put("name", json.getString("name"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 解析菜谱内容评论详情
	 * @param json
	 * @return
	 */
	public String parserMessage(JSONObject json){
		String message="";
		try {
			message= json.getString("message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * 解析菜谱列表id
	 * @param json
	 * @return
	 */
	public String parserIds(JSONObject json){
		String ids = "";
		try {
			JSONArray array = json.getJSONArray("list");
			for(int i=0;i<array.length();i++){
				ids+=array.getString(i)+",";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ids;
	}
	
	/**
	 * 解析菜谱详情
	 * @param json
	 * @return
	 */
	public FoodContent parserFoodContent(JSONObject json){
		FoodContent food = new FoodContent();
		List<Materidl> mlist = new ArrayList<Materidl>();
		List<Step> steplist = new ArrayList<Step>();
		List<Tips> tipslist = new ArrayList<Tips>();
		List<DiscussionPo> dislist = new ArrayList<DiscussionPo>();
		try {
			food.setName(json.getString("name"));
			food.setCollectNum(json.getString("collectNum"));
			food.setEditid(json.getString("editid"));
			food.setEditname(json.getString("editname"));
			food.setContent(json.getString("content"));
			JSONObject time = json.getJSONObject("gettime");
			food.setGettime((time.getInt("year")+1900)+"-"+time.getString("month")+"-"+time.getString("date"));
			food.setImageid(json.getInt("imageid"));
			JSONObject image = json.getJSONObject("imageSizePo");
			food.setImageSizePo_high(image.getInt("high"));
			food.setImageSizePo_width(image.getInt("width"));
			JSONArray array = json.getJSONArray("materialList");
			for(int i = 0; i < array.length(); i++){
				Materidl materidl = new Materidl();
				JSONObject mObject = array.getJSONObject(i);
				materidl.setId(mObject.getInt("id"));
				materidl.setContentid(mObject.getInt("contentid"));
				materidl.setName(mObject.getString("name"));
				materidl.setDosage(mObject.getString("dosage"));
				mlist.add(materidl);
			}
			food.setMateridlList(mlist);
			JSONArray sArray = json.getJSONArray("stepList");
			for(int j = 0; j < sArray.length() ; j++){
				Step step = new Step();
				JSONObject sObject = sArray.getJSONObject(j);
				step.setContentid(sObject.getInt("contentid"));
				step.setDetails(sObject.getString("details"));
				step.setId(sObject.getInt("id"));
				step.setImageid(sObject.getString("imageid"));
				step.setTime(sObject.getInt("time"));
				steplist.add(step);
			}
			food.setStepList(steplist);
			JSONArray tipArray = json.getJSONArray("tipsList");
			for(int i=0;i<tipArray.length();i++){
				Tips tip = new Tips();
				JSONObject tipObject = tipArray.getJSONObject(i);
				tip.setContentid(tipObject.getInt("contentid"));
				tip.setDetails(tipObject.getString("details"));
				tip.setId(tipObject.getInt("id"));
				tipslist.add(tip);
			}
			food.setTipsList(tipslist);
			food.setType(json.getString("type"));
			JSONArray disArray = json.getJSONArray("discussionPoList");
			for(int j=0;j<disArray.length();j++){
				DiscussionPo discussion = new DiscussionPo();
				JSONObject disObject = disArray.getJSONObject(j);
				discussion.setCommentid(disObject.getInt("commentid"));
				discussion.setContentid(disObject.getInt("contentid"));
				discussion.setDicussion(disObject.getString("discussion"));
				discussion.setDistime(disObject.getString("distime"));
				discussion.setId(disObject.getInt("id"));
				discussion.setUsertitle(disObject.getString("usertitle"));
				discussion.setPic(disObject.getInt("pic"));
				discussion.setUid(disObject.getInt("uid"));
				dislist.add(discussion);
			}
			food.setDiscussionPoList(dislist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return food;
	}
	/**
	 * 解析热门专题WIEBO评论
	 * @param json
	 * @return
	 */
	public List<Map<String,String>> parserWeiboListByIdList(JSONObject json){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			try {
				JSONArray array = json.getJSONArray("list");
				for(int i=0;i<array.length();i++){
					JSONObject object = array.getJSONObject(i);
					Map<String,String> map = new HashMap<String, String>();
					map.put("weibo", object.getString("weibo"));
					map.put("pic", object.getString("pic"));
					map.put("region", object.getString("region"));
					map.put("nickname", object.getString("nickname"));
					map.put("addtimeFull", object.getString("addtimeFull"));
					map.put("enjoy", object.getString("enjoy"));
					map.put("id", object.getString("id"));
					map.put("uid", object.getString("uid"));
					list.add(map);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return list;
	}

	/**
	 * 解析热门专题第一�?
	 * 
	 * @param json
	 * @return
	 */
	public List<Map<String, String>> parserHotRecipeIdList(JSONObject json) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			JSONArray array = json.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				Map<String,String> map = new HashMap<String, String>();
				map.put("name", object.getString("name"));
				map.put("recipeididlist", object.getString("recipeididlist"));
				map.put("uid", object.getString("uid"));
				map.put("id", object.getString("id"));
				map.put("wid", object.getString("wid"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	/**
	 * 解析菜谱列表JSON数据
	 * 
	 * @param json
	 * @return 菜谱列表（包含菜谱详细信息）
	 */
	public List<FoodContent> parserJSONObjectToFoodContent(JSONObject json) {
		List<FoodContent> list = new ArrayList<FoodContent>();
		try {
			JSONArray array = json.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				FoodContent food = new FoodContent();
				if(array.getJSONObject(i)!=null){
				JSONObject object = array.getJSONObject(i);
				food.setName(object.getString("name"));
				food.setContent(object.getString("content"));
				food.setId(object.getString("id"));
				food.setImageid(object.getInt("imageid"));
				list.add(food);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
	/**
	 * 解析菜谱列表JSON数据--搜索列表
	 * 
	 * @param json
	 * @return 菜谱列表（包含菜谱详细信息）
	 */
	public List<FoodContent> parserJSONObjectToSearchList(JSONObject json) {
		List<FoodContent> list = new ArrayList<FoodContent>();
		try {
			JSONArray array = json.getJSONArray("contentList");
			for (int i = 0; i < array.length(); i++) {
				FoodContent food = new FoodContent();
				JSONObject object = array.getJSONObject(i);
				String name =SearchUtils.changeStringColor(object.getString("name"));
				food.setName(name);
				food.setContent(SearchUtils.changeStringColor(object.getString("content")));
				food.setId(object.getString("id"));
				food.setImageid(Integer.valueOf(object.getString("imageid")));
				list.add(food);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
	/**
	 * 解析首页图片显示信息
	 * 
	 * @param json
	 * @return
	 */
	public List<Map<String, String>> parserHeaderImageInfo(JSONObject json) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			JSONArray array = json.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject object = array.getJSONObject(i);
				map.put("name", object.getString("name"));
				map.put("imageid", object.getString("imageid"));
				map.put("id", object.getString("id")); // id为查看内容id
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 解析�?��活动显示信息
	 * 
	 * @param json
	 * @return
	 */
	public List<Map<String,String>> parseJSONActivites(JSONObject json){
		List<Map<String,String>>list=new ArrayList<Map<String,String>>();
		try{
			JSONArray array=json.getJSONArray("list");
			for(int i=0;i<array.length();i++){
				Map<String,String>map=new HashMap<String, String>();
				JSONObject object=array.getJSONObject(i);
				map.put("areaName", object.optString("areaName"));
				map.put("commentNum", object.optString("commentNum"));
				map.put("endtime", object.optString("endtime"));
				map.put("endtimeSimple", object.optString("endtimeSimple"));
				map.put("id", object.optString("id"));
				map.put("imageid", object.optString("imageid"));
				map.put("starttimeSimple", object.optString("starttimeSimple"));
				map.put("stockname", object.optString("stockname"));
				map.put("stocknum",object.optString("stocknum"));
				map.put("subhead", object.optString("subhead"));
				map.put("title", object.optString("title"));
				map.put("wid", object.optString("wid"));
				map.put("stype", object.optString("stype"));
				map.put("prize",object.optString("prize") );
				list.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 解析达人推荐显示信息
	 * 
	 * @param json
	 * @return
	 */
	public List<Map<String,String>> parseJSONStaffPicks(JSONObject json){
	List<Map<String,String>>list=new ArrayList<Map<String,String>>();
		
		try{
			JSONArray array=json.getJSONArray("list");
			for(int i=0;i<array.length();i++){
				JSONObject object=array.getJSONObject(i);
				Map<String,String>map=new HashMap<String, String>();
				map.put("enabled", object.optString("enabled"));
				map.put("id",object.optString("id"));
				map.put("pic", object.optString("pic"));
				map.put("profile", object.optString("profile"));
				map.put("sex", object.optString("sex"));
				map.put("title", object.optString("title"));
				map.put("region", object.optString("region"));
				map.put("username", object.optString("username"));
				list.add(map);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 
	 * 解析热门专题
	 * 
	 * 
	 */
	public List<String> parseHotSpecial(JSONObject json){
		List<String>list=new ArrayList<String>();
		try{
			JSONArray array=json.getJSONArray("list");
			for(int i=0;i<array.length();i++){
				Object object=array.get(i);
				list.add(object.toString());
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 解析厨友�?--大杂烩的信息
	 * 
	 * 
	 */
	public List<Map<String,String>> parseBigMixedStew(JSONObject json){
		List<Map<String,String>>list=new ArrayList<Map<String,String>>();
		try{
			JSONArray array=json.getJSONArray("list");
			for(int i=0;i<array.length();i++){
				JSONObject object=array.getJSONObject(i);
				Map<String,String>map=new HashMap<String,String>();
				map.put("addtime",object.optString("addtime"));
				map.put("addtimeFull",object.optString("addtimeFull"));
				map.put("collectionid",object.optString("collectionid"));
				map.put("disabled", object.optString("disabled"));
				map.put("enjoy", object.optString("enjoy"));
				map.put("forward", object.optString("forward"));
				map.put("id", object.optString("id"));
				map.put("list", object.optString("list"));
				map.put("mid", object.optString("mid"));
				map.put("nickname", object.optString("nickname"));
				map.put("pic", object.optString("pic"));
				map.put("picid", object.optString("picid"));
				map.put("region", object.optString("region"));
				map.put("terminal", object.optString("terminal"));
				map.put("title", object.optString("title"));
				map.put("total", object.optString("total"));
				map.put("type", object.optString("type"));
				map.put("uid", object.optString("uid"));
				map.put("updatetime", object.optString("updatetime"));
				map.put("usercontent", object.optString("usercontent"));
				map.put("weibo", object.optString("weibo"));
				list.add(map);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
}
