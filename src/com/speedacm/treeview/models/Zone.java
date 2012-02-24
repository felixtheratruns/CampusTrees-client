package com.speedacm.treeview.models;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Rect;

import com.google.android.maps.GeoPoint;

public class Zone
{
	private List<GeoPoint> mPoints;
	private Rect mBoundingBox;
	
	public Zone(String jsonText)
	{
		this();
		recalcBounds();
	}
	
	public Zone()
	{
		mPoints = new ArrayList<GeoPoint>();
	}
	
	public void addPoint(GeoPoint p)
	{
		mPoints.add(p);
		recalcBounds();
	}
	
	private void recalcBounds()
	{
		int minLat = Integer.MAX_VALUE;
		int maxLat = Integer.MIN_VALUE;
		int minLng = Integer.MAX_VALUE;
		int maxLng = Integer.MAX_VALUE;
		
		for(GeoPoint p : mPoints)
		{
			int lat = p.getLatitudeE6();
			int lng = p.getLongitudeE6();
			
			minLat = Math.min(minLat, lat);
			maxLat = Math.max(maxLat, lat);
			minLng = Math.min(minLng, lng);
			maxLng = Math.max(maxLng, lng);
		}
		
		mBoundingBox = new Rect(minLng, maxLat, maxLng, minLat);
	}
	
	public List<GeoPoint> getPoints() { return mPoints; }
	public void setPoints(List<GeoPoint> points) { mPoints = points; }
}
