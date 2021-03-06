/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.speedacm.treeview.R;
import com.speedacm.treeview.mapmodes.MapMode;
import com.speedacm.treeview.mapmodes.SustainMode;
import com.speedacm.treeview.mapmodes.TreeMode;

public class DynamicMapActivity extends MapActivity
{	
	private MapView mMap;
	private MapMode mMapMode;
	private int mMapModeID;
	
	public static final int TREE_MODE = 0;
	public static final int SUSTAIN_MODE = 1;
	
	// latitude and longitude of "University of Louisville"
	// according to google: 38.2159018, -85.7581278
	
	private final GeoPoint mUofL_LatLong = new GeoPoint(38215901, -85758128);
	private final int mUofL_DefZoom = 18;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.treemap);
		mMap = (MapView)findViewById(R.id.mainTreeMap);
		resetMap(false); // don't animate to point
		mMap.setBuiltInZoomControls(true);
		setMapMode(getIntent().getExtras().getInt("mapmode"));
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
		mMapMode.onDeactivate();
	}
	
	private void setMapMode(int type)
	{
		if(mMapMode != null)
		{
			mMap.getOverlays().clear();
			mMapMode.onDeactivate();
		}
		
		mMapModeID = type;
		
		switch(type)
		{
		case TREE_MODE:
			setTitle(R.string.treemap_label);
			mMapMode = new TreeMode(this);
			break;
			
		case SUSTAIN_MODE:
			setTitle(R.string.sustmap_label);
			mMapMode = new SustainMode(this);
			break;
		}
		
		mMapMode.onActivate();
		mMap.getOverlays().add(mMapMode);
		mMap.invalidate();
	}
	
	public void invalidateMap()
	{
		mMap.invalidate();
	}
	
	public void setBusyIndicator(boolean enabled)
	{
		setProgressBarIndeterminateVisibility(enabled);
	}
	
	public void resetMap(boolean animate)
	{
		MapController mc = mMap.getController();
		mc.setZoom(mUofL_DefZoom);
		if(animate)
			mc.animateTo(mUofL_LatLong);
		else
			mc.setCenter(mUofL_LatLong);
	}
	
	/** This function is only called once, before the menu is shown */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mapmenu, menu);
		return mMapMode.onCreateOptionsMenu(menu);
	}
	
	/** This function is called before the menu is opened every time */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		MenuItem swmode = menu.findItem(R.id.mapm_switchmode);
		swmode.setVisible(false);
		
		switch(mMapModeID)
		{
		case TREE_MODE:
			swmode.setTitle("Switch to Sustainability");
			break;
			
		case SUSTAIN_MODE:
			swmode.setTitle("Switch to Tree Map");
			break;
		}
		
		return mMapMode.onPrepareOptionsMenu(menu);
	}
	
	private void cycleMode()
	{
		switch(mMapModeID)
		{
		case TREE_MODE:
			setMapMode(SUSTAIN_MODE);
			break;
		case SUSTAIN_MODE:
			setMapMode(TREE_MODE);
			break;
		}
	}
	
	/** This function is how we hook into the options handlers */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.mapm_resetview:
				resetMap(true); // animate to reset point
				return true;
			case R.id.mapm_switchmode:
				cycleMode();
				return true;
			default:
				return mMapMode.onOptionsItemSelected(item);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mMapMode.onActivityResult(requestCode, resultCode, data);
	}
}
