package com.centaurus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "myfav_jia.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="create table collect(_id Integer primary key,name varchar(64),content varchar(128),image varchar(128),user varchar(64),imageid varchar(20))";
		String sqll="create table username(_id Integer primary key,user varchar(64),password varchar(64))";
		db.execSQL(sql);
		db.execSQL(sqll);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
