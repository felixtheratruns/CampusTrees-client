package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;

public class PlantFact {
	
	private GeoPoint latLong;
	private String title;
	private String body;
	
	public PlantFact(GeoPoint pLatLong, String pTitle, String pBody)
	{
		latLong = pLatLong;
		title = pTitle;
		body = pBody;
	}
	
	public PlantFact(String pTitle, String pBody)
	{
		title = pTitle;
		body = pBody;
	}
	
	public String toString()
	{
		return title;
	}
	
	public GeoPoint getLocation() { return latLong; }
	
	
}
