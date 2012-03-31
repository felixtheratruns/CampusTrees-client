package com.speedacm.treeview.views;

import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;

import android.app.Activity;
import android.os.Bundle;

public class NewsActivity extends Activity implements DSResultListener
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsbox);
		
	}

	@Override
	public void onDSResultReceived(int requestID, Object payload) {
		// TODO Auto-generated method stub
		
	}
	
	
}
