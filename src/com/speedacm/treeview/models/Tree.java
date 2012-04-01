package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;
import com.speedacm.treeview.data.DataStore;

public class Tree
{
	private int mID;  // tree ID
	private int mSID; // species ID
	
	private float mDBH;
	private float mHeight;
	
	private GeoPoint mLatLong;
	
	public Tree(int id, int sid, GeoPoint point, float dbh, float height)
	{
		mID = id;
		mSID = sid;
		mLatLong = point;
		mDBH = dbh;
		mHeight = height;
	}
	
	public int getID() { return mID; }
	public int getSpeciesID() { return mSID; }
	public GeoPoint getLocation() { return mLatLong; }
	public float getDBH() { return mDBH; }
	public float getHeight() { return mHeight; }
	public void setLocation(GeoPoint latLong) { mLatLong = latLong; }
	
	public Species getSpecies()
	{
		// call the synchronous function
		return DataStore.getInstance().getSpecies(mSID);
	}
}
