package com.speedacm.treeview.views;

import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.speedacm.treeview.R;
import com.speedacm.treeview.mapitems.MultiTreeItem;
import com.speedacm.treeview.mapitems.ZoneItem;
import com.speedacm.treeview.mapmodes.MapMode;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;

public class DynamicMapActivity extends MapActivity
{
	
	//
	// this collection of variables represents
	// whether or not we have our available 
	// maps, tree locations, tree types, etc
	//
	
	private MapView mMap;
	private MapMode mMapMode;
	
	// latitude and longitude of "University of Louisville"
	// according to google: 38.2159018, -85.7581278
	
	private final GeoPoint mUofL_LatLong = new GeoPoint(38215901, -85758128);
	private final int mUofL_DefZoom = 18;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treemap);
		mMap = (MapView)findViewById(R.id.mainTreeMap);
		resetMap(false); // don't animate to point
		mMap.setBuiltInZoomControls(true);
		
		setupFakeData();
	}
	
	private void setupFakeData()
	{
		List<Overlay> overlays = mMap.getOverlays();
		
		Tree testTree1 = new Tree(); testTree1.setLocation(new GeoPoint(38214901, -85757474));
		Tree testTree2 = new Tree(); testTree2.setLocation(new GeoPoint(38215901, -85758474));
		Tree testTree3 = new Tree(); testTree3.setLocation(new GeoPoint(38216901, -85759474));
		
		Zone zone = new Zone();
		zone.addPoint(new GeoPoint(38217901, -85757474));
		zone.addPoint(new GeoPoint(38218901, -85756074));
		zone.addPoint(new GeoPoint(38219901, -85755474));
		zone.addPoint(new GeoPoint(38220901, -85756474));
		zone.addPoint(new GeoPoint(38221901, -85757074));
		
		MultiTreeItem mti = new MultiTreeItem();
		mti.addTree(testTree1);
		mti.addTree(testTree2);
		mti.addTree(testTree3);
		
		overlays.add(mti);
		overlays.add(new ZoneItem(zone));
	}
	
	private void resetMap(boolean animate)
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
		return true;
	}
	
	/** This function is called before the menu is opened every time */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		return true;
	}
	
	/** This function is how we hook into the options handlers */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.mapm_filtertrees:
				break;
			case R.id.mapm_treetypes:
				break;
			case R.id.mapm_resetview:
				resetMap(true); // animate to reset point
				break;
		}
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
