package com.speedacm.treeview.mapmodes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.speedacm.treeview.mapitems.BuildingItem;
import com.speedacm.treeview.views.DynamicMapActivity;

public class SustainMode extends MapMode
{

	private List<BuildingItem> mBuildings;
	private DynamicMapActivity mParent;
	
	public SustainMode(DynamicMapActivity parent)
	{
		mBuildings = new ArrayList<BuildingItem>();
		mParent = parent;
	}
	
	/**
	 * Fetches the building data from the server
	 */
	public void fetch()
	{
		
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		if(shadow) return;
		
		for(BuildingItem item : mBuildings)
		{
			item.draw(canvas, mapView, shadow);
		}
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		for(BuildingItem item : mBuildings)
		{
			if(item.onTap(p, mapView))
				return true;
		}
		
		return false;
	}
	
	public void onActivate()
	{
		
	}
	
	public void onDeactivate()
	{
		
	}
	
	
}
