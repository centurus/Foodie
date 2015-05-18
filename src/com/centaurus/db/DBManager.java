package com.centaurus.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.centaurus.bean.FoodContent;
import com.centaurus.bean.UserName;

public class DBManager {
	
	private DBHelper helper;
	
	public DBManager(Context context){
		helper = new DBHelper(context);
	}
	
	public List<FoodContent> query(){
		List<FoodContent>  list = new ArrayList<FoodContent>();
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor=database.query("collect", null, null, null, null, null, null);
		while(cursor.moveToNext()){
			FoodContent food = new FoodContent();
			food.setId(cursor.getString(cursor.getColumnIndex("_id")));
			food.setImagebytes(cursor.getBlob(cursor.getColumnIndex("image")));
			food.setContent(cursor.getString(cursor.getColumnIndex("content")));
			food.setName(cursor.getString(cursor.getColumnIndex("name")));
			food.setImageid((Integer.valueOf(cursor.getString(cursor.getColumnIndex("imageid")))));
			//food.setUsername(cursor.getString(cursor.getColumnIndex("username")));
			list.add(food);
		}
		return list;
	}
	

	public long insert(ContentValues values){
		SQLiteDatabase database = helper.getWritableDatabase();
		long id=database.insert("collect", null, values);
		return id;
	}
	
	public int delete(String [] id){
		SQLiteDatabase database = helper.getWritableDatabase();
		int flag=database.delete("collect", "_id=?", id);
		return flag;
	}
	public long insertUser(ContentValues values){
		SQLiteDatabase database=helper.getWritableDatabase();
		long id=database.insert("username", null, values);
		return id;
	}
	public List<UserName> selectuser(String[] user){
		SQLiteDatabase database=helper.getWritableDatabase();
		List<UserName>list_user=new ArrayList<UserName>();
		Cursor cursor=database.query("username", null, "user=?", user, null, null, null,null);
		while(cursor.moveToNext()){
			UserName username=new UserName();
			username.setUser(cursor.getString(cursor.getColumnIndex("user")));
			username.setPassword(cursor.getString(cursor.getColumnIndex("password")));
			list_user.add(username);
		}
		return list_user;
	}
	public boolean selectuserboolean(String[] user){
		SQLiteDatabase database=helper.getWritableDatabase();
		System.out.println(user.toString()+"===================username++++++++++++");
		Cursor cursor=database.query("username", null, "user=?", user, null, null, null);
		System.out.println(cursor.getCount()+"getCount"+"======"+cursor.toString());
		while(cursor.moveToNext()){
			if(user[0].equals(cursor.getString(cursor.getColumnIndex("user")))){
				return true;
			}
		}
		return false;
	}

	public List<UserName> selectusers(String user,String password){
		SQLiteDatabase database=helper.getWritableDatabase();
		List<UserName>list_user=new ArrayList<UserName>();
		String sql="select * from username where user='"+user+"' and password='"+password+"'";
		Cursor cursor=database.rawQuery(sql, null);
		if(cursor.getCount()>0){
			while(cursor.moveToNext()){
				UserName username=new UserName();
				username.setUser(cursor.getString(cursor.getColumnIndex("user")));
				username.setPassword(cursor.getString(cursor.getColumnIndex("password")));
				list_user.add(username);
			}
		}
		cursor.close();
		database.close();
		return list_user;
	}
	public List<FoodContent> query(String user){
		List<FoodContent>  list = new ArrayList<FoodContent>();
		SQLiteDatabase database = helper.getReadableDatabase();
		String sql="select * from collect where user='"+user+"'";
		Cursor cursor=database.rawQuery(sql, null);
		if(cursor.getCount()>0){
		while(cursor.moveToNext()){
			FoodContent food = new FoodContent();
			food.setId(cursor.getString(cursor.getColumnIndex("_id")));
			food.setImagebytes(cursor.getBlob(cursor.getColumnIndex("image")));
			food.setContent(cursor.getString(cursor.getColumnIndex("content")));
			food.setName(cursor.getString(cursor.getColumnIndex("name")));
			food.setUsername(cursor.getString(cursor.getColumnIndex("user")));
			food.setImageid(cursor.getInt(cursor.getColumnIndex("imageid")));
			list.add(food);
		}
		}
		return list;
	}
	public int deletes(String id,String user){
		int i=0;
		SQLiteDatabase database = helper.getReadableDatabase();
		String sql="delete from collect where _id="+id+" and user='"+user+"'";
		try{
			database.execSQL(sql);
			i=1;
		}catch(SQLException e){
			i=-1;
		}
		
		
		return i;
	}
	
}
