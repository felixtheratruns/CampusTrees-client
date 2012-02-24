package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;

public class Tree
{
	private GeoPoint mLatLong;
	
	public Tree(String jsonText)
	{
	}
	
	public Tree()
	{
	}
	
	public GeoPoint getLocation() { return mLatLong; }
	public void setLocation(GeoPoint latLong) { mLatLong = latLong; }
}
