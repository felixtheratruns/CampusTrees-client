package com.speedacm.treeview.data;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.google.android.maps.GeoPoint;
import com.speedacm.treeview.data.storage.AbstractStorage;
import com.speedacm.treeview.data.storage.DiskStorage;
import com.speedacm.treeview.data.storage.MemStorage;
import com.speedacm.treeview.data.storage.NetStorage;
import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;

public class DataStore
{
	
	public static final int NO_REQUEST = -1;
	
	// helper class to abstract out the AsyncTask boilerplate
	private abstract class DSTask<T> extends AsyncTask<Void, Void, T>
	{
		private DSResultListener<T> mListener;
		private int mRequestID;
		
		public DSTask(DSResultListener<T> listener, int requestID)
		{
			mListener = listener;
			mRequestID = requestID;
		}
		
		@Override
		protected void onPostExecute(T result)
		{
			mListener.onDSResultReceived(mRequestID, result);
			mTasks.remove(mRequestID);
		}
	}
	
	/*
	 * Asynchronous Functions
	 */
	
	public int beginGetAllZones(final DSResultListener<Zone[]> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<Zone[]> task = new DSTask<Zone[]>(listener, newRequestID) {
			@Override
			protected Zone[] doInBackground(Void... params) {
				return getAllZones();
			}
		};
		
		mTasks.put(newRequestID, task);
		task.execute();
		
		return newRequestID;
	}
	
	public int beginGetZone(final int id, final DSResultListener<Zone> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<Zone> task = new DSTask<Zone>(listener, newRequestID) {
			@Override
			protected Zone doInBackground(Void... params) {
				return getZone(id);
			}
		};
		
		mTasks.put(newRequestID, task);
		task.execute();
		
		return newRequestID;
	}
	
	public int beginGetAllBuildings(final DSResultListener<Building[]> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<Building[]> task = new DSTask<Building[]>(listener, newRequestID) {
			@Override
			protected Building[] doInBackground(Void... params) {
				return getAllBuildings();
			}
		};
		
		mTasks.put(newRequestID, task);
		task.execute();
		
		return newRequestID;
	}
	
	/*
	 * Synchronous Functions
	 */
	
	public Zone[] getAllZones()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		return mParser.parseAllZonesResponse("[{\"id\":1,\"points\":[{\"lat\":38.213761075989,\"long\":-85.762285415524},{\"lat\":38.213419670942,\"long\":-85.757426252598},{\"lat\":38.215868479454,\"long\":-85.75697564162},{\"lat\":38.21611925634,\"long\":-85.757946601268},{\"lat\":38.216077109014,\"long\":-85.758633246657},{\"lat\":38.216443789913,\"long\":-85.761857261588}]},{\"id\":2,\"points\":[{\"lat\":2.1,\"long\":2.1},{\"lat\":2.2,\"long\":2.2},{\"lat\":2.3,\"long\":2.3},{\"lat\":2.4,\"long\":2.4}]}]");
	}
	
	public Zone getZone(int id)
	{
		if(id == 1)
		{
			// TODO: create an actual new zone from JSON data
			// because this has weird behavior when mTrees isn't inited
			Zone z = new Zone();
			Tree t = new Tree();
			t.setLocation(new GeoPoint(38215652,-85758172));
			z.addTree(t);
			
			return z;
		}
		return null;
	}
	
	public Building[] getAllBuildings()
	{
		Building b = new Building(new GeoPoint(38215901, -85758128));
		Building[] bs = new Building[1];
		bs[0] = b;
		return bs;
	}
	
	/*
	public Building getBuilding(int id)
	{
		return null;
	}
	*/
	
	/*
	 * Public helper functions
	 */
	
	public static DataStore getInstance()
	{
		if(mSingleton == null)
			mSingleton = new DataStore();
		
		return mSingleton;
	}
	
	public void cancelRequest(int requestID)
	{
		DSTask<?> task = mTasks.remove(requestID);
		
		if(task != null)
			task.cancel(false);
	}
	
	/*
	 * Private stuff
	 */
	
	private static DataStore mSingleton = null;
	
	private int mNextRequestID = 1;
	private Map<Integer, DSTask<?>> mTasks;
	private DataParser mParser;
	private AbstractStorage mStorage;
	
	private DataStore()
	{
		mTasks = new HashMap<Integer, DSTask<?>>();
		mParser = new DataParser();
		
		// chained fallback storage in case each layer doesn't have it yet
		mStorage = new MemStorage(new DiskStorage(new NetStorage(null)));
	}
	
}
