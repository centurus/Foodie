package com.centaurus.view;

import android.app.ProgressDialog;
import android.content.Context;

public class MyDialog extends ProgressDialog {
	private static MyDialog dialog=null;
	public static MyDialog newInstance(Context context){
		dialog = new MyDialog(context);
		dialog.setTitle("请稍等");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage("获取查询内容...");
		return dialog;
	}

	public MyDialog(Context context) {
		super(context);
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}
	
}
