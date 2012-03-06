package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;

public class Building {
	
	private GeoPoint mLocation;
	
	public Building(GeoPoint location)
	{
		mLocation = location;
	}
	
	public GeoPoint getLocation() { return mLocation; }
}
