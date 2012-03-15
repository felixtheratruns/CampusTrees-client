package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;

public class Tree
{
	private int mID;
	private Species mSpecies;
	private GeoPoint mLatLong;
	
	public Tree()
	{
	}
	
	public int getID() { return mID; }
	public Species getSpecies() { return mSpecies; }
	public GeoPoint getLocation() { return mLatLong; }
	public void setLocation(GeoPoint latLong) { mLatLong = latLong; }
}
