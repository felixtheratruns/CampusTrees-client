package com.speedacm.treeview.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.speedacm.treeview.R;

public class TreeMapActivity extends Activity
{
	
	//
	// this collection of variables represents
	// whether or not we have our available 
	// maps, tree locations, tree types, etc
	//
	private boolean mFetchedTreeTypes = false;
	private boolean mFetchedMap = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treemap);
	}
	
	/** This function is only called once, before the menu is shown */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mapmenu, menu);
		return true;
	}
	
	/** This function is called before the menu is opened every time */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		if(!mFetchedTreeTypes)
		{
			// TODO: fetch trees from server and use menu.add
		}
		return true;
	}
}
