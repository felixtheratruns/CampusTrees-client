package com.speedacm.treeview.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.speedacm.treeview.R;

public class TreeMapActivity extends MapActivity
{
	
	//
	// this collection of variables represents
	// whether or not we have our available 
	// maps, tree locations, tree types, etc
	//
	private boolean mFetchedTreeTypes = false;
	private boolean mFetchedMap = false;
	private MapView mMap;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treemap);
		mMap = (MapView)findViewById(R.id.mainTreeMap);
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

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
