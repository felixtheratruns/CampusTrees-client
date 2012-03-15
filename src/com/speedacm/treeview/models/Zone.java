package com.speedacm.treeview.models;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Rect;

import com.google.android.maps.GeoPoint;

public class Zone
{
	private List<GeoPoint> mPoints;
	private List<Tree> mTrees;
	private Rect mBoundingBox;
	
	private boolean mFetched = false;
	
	private int mID;
	
	public Zone(int id, List<GeoPoint> points)
	{
		mID = id;
		mPoints = points;
		mFetched = false;
	}
	
	public Zone(int id, List<GeoPoint> points, List<Tree> trees)
	{
		this(id, points);
		mTrees = trees;
		mFetched = true;
	}
	
	public Zone()
	{
		this(-1, new ArrayList<GeoPoint>());
	}
	
	public boolean isFetched()
	{
		return mFetched;
	}
	
	public void addPoint(GeoPoint p)
	{
		mPoints.add(p);
		recalcBounds();
	}
	
	public void addTree(Tree t)
	{
		if(mTrees == null)
		{
			// this is just dummy code in case someone
			// is attempting to create a zone manually
			mTrees = new ArrayList<Tree>();
			mFetched = true; // prevent fetches
		}
		mTrees.add(t);
	}
	
	private void recalcBounds()
	{
		int minLat = Integer.MAX_VALUE;
		int maxLat = Integer.MIN_VALUE;
		int minLng = Integer.MAX_VALUE;
		int maxLng = Integer.MIN_VALUE;
		
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
	
	public boolean inBounds(GeoPoint p)
	{
		int lat = p.getLatitudeE6();
		int lng = p.getLongitudeE6();
		
		return (lat >= mBoundingBox.bottom && lat <= mBoundingBox.top && 
				lng >= mBoundingBox.left   && lng <= mBoundingBox.right);
	}
	
	public List<GeoPoint> getPoints() { return mPoints; }
	public void setPoints(List<GeoPoint> points) { mPoints = points; recalcBounds(); }
	
	public List<Tree> getTrees() { return mTrees; }
	public void setTrees(List<Tree> trees) { mTrees = trees; mFetched = true; }

	public int getID() { return mID; }
}
