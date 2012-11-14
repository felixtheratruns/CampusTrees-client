package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;
import com.speedacm.treeview.data.DataStore;

public class Tree
{
	private int mID;  // tree ID
	private int mSID; // species ID
	
	private float mDBH;
	private float mHeight;
	private float mGreenWt;
	private float mDryWt;
	private int   mAge;
	private float mCO2SeqTotal;
	private float mCO2SeqPerYr;
	private float mVol;
	private float mCrownArea;
	private float mCarbonWt;
	private String mMonetary;
	
	private GeoPoint mLatLong;
	
	public Tree(int id, int sid, GeoPoint point, float dbh, float height, float greenwt, float drywt, int age, float co2seqtot, float co2seqyr, float vol, float crownarea, float carbonwt, String monetary)
	{
		mID = id;
		mSID = sid;
		mLatLong = point;
		mDBH = dbh;
		mHeight = height;
		mGreenWt = greenwt;
		mDryWt = drywt;
		mAge = age;
		mCO2SeqTotal = co2seqtot;
		mCO2SeqPerYr = co2seqyr;
		mVol = vol;
		mCrownArea = crownarea;
		mCarbonWt = carbonwt;
		mMonetary = monetary;
	}
	
	public int getID() { return mID; }
	public int getSpeciesID() { return mSID; }
	public GeoPoint getLocation() { return mLatLong; }
	public float getDBH() { return mDBH; }
	public float getHeight() { return mHeight; }
	public int getAge() { return mAge; }
	public float getCO2SeqTotal() { return mCO2SeqTotal; }
	public float getCO2SeqPerYr() { return mCO2SeqPerYr; }
	public float getDryWeight() { return mDryWt; }
	public float getGreenWeight() { return mGreenWt; }
	public float getCarbonWeight() { return mCarbonWt; }
	public float getCrownArea() { return mCrownArea; }
	public float getVolume() { return mVol; }
	public String getMonetary() { return mMonetary; }
	public void setLocation(GeoPoint latLong) { mLatLong = latLong; }
	
	public Species getSpecies()
	{
		// call the synchronous function
		return DataStore.getInstance().getSpecies(mSID);
	}
}
