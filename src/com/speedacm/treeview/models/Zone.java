package com.speedacm.treeview.models;

import java.util.List;

import com.google.android.maps.GeoPoint;

public class Zone
{
	private List<GeoPoint> mPoints;
	
	public Zone(String jsonText)
	{
	}
	
	public Zone()
	{
	}
	
	public List<GeoPoint> getPoints() { return mPoints; }
	public void setPoints(List<GeoPoint> points) { mPoints = points; }
}
