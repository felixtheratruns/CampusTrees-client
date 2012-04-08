package com.speedacm.treeview.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.AsyncTask;

import com.google.android.maps.GeoPoint;
import com.speedacm.treeview.data.storage.AbstractStorage;
import com.speedacm.treeview.data.storage.DiskStorage;
import com.speedacm.treeview.data.storage.MemStorage;
import com.speedacm.treeview.data.storage.NetStorage;
import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.Species;
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
		
		return putTask(newRequestID, task);
	}
	
	public int beginGetZoneDetails(final Zone z, final DSResultListener<Zone> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<Zone> task = new DSTask<Zone>(listener, newRequestID) {
			@Override
			protected Zone doInBackground(Void... params) {
				getZoneDetails(z);
				return z;
			}
		};
		
		return putTask(newRequestID, task);
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
		
		return putTask(newRequestID, task);
	}
	
	public int beginGetAllSpecies(final DSResultListener<Species[]> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<Species[]> task = new DSTask<Species[]>(listener, newRequestID) {
			@Override
			protected Species[] doInBackground(Void... params) {
				return getAllSpecies();
			}
		};
		
		return putTask(newRequestID, task);
	}
	
	public int beginGetSpecies(final int id, final DSResultListener<Species> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<Species> task = new DSTask<Species>(listener, newRequestID) {
			@Override
			protected Species doInBackground(Void... params) {
				return getSpecies(id);
			}
		};
		
		return putTask(newRequestID, task);
	}
	
	public int beginGetTree(final int id, final DSResultListener<Tree> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<Tree> task = new DSTask<Tree>(listener, newRequestID) {
			@Override
			protected Tree doInBackground(Void... params) {
				return getTree(id);
			}
		};
		
		return putTask(newRequestID, task);
	}
	
	public int beginGetFloweringSpecies(final int month, final DSResultListener<List<Integer>> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<List<Integer>> task = new DSTask<List<Integer>>(listener, newRequestID) {
			@Override
			protected List<Integer> doInBackground(Void... params) {
				return getFloweringSpecies(month);
			}
		};
		
		return putTask(newRequestID, task);
	}
	
	public int beginGetFruitingSpecies(final int month, final DSResultListener<List<Integer>> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		DSTask<List<Integer>> task = new DSTask<List<Integer>>(listener, newRequestID) {
			@Override
			protected List<Integer> doInBackground(Void... params) {
				return getFruitingSpecies(month);
			}
		};
		
		return putTask(newRequestID, task);
	}
	
	/*
	 * Synchronous Functions
	 */
	
	public Zone[] getAllZones()
	{	
		return mStorage.getAllZones();
	}
	
	public void getZoneDetails(Zone z)
	{
		mStorage.getZoneDetails(z);
	}
	
	public Building[] getAllBuildings()
	{
		Building b = new Building(new GeoPoint(38215901, -85758128));
		Building[] bs = new Building[1];
		bs[0] = b;
		return bs;
	}
	
	public Species[] getAllSpecies()
	{
		return mStorage.getAllSpecies();
	}
	
	public Species getSpecies(int id)
	{
		return mStorage.getSpecies(id);
	}
	
	public Tree getTree(int id)
	{
		return mStorage.getTree(id);
	}

	public List<Integer> getFloweringSpecies(int month)
	{
		return mStorage.getFloweringSpecies(month);
	}
	
	public List<Integer> getFruitingSpecies(int month)
	{
		return mStorage.getFruitingSpecies(month);
	}
	
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
	private AbstractStorage mStorage;
	
	private DataStore()
	{
		mTasks = new HashMap<Integer, DSTask<?>>();
		
		
		// chained fallback storage in case each layer doesn't have it yet
		mStorage = new MemStorage(new DiskStorage(new NetStorage(null)));
	}
	
	private int putTask(int id, DSTask<?> task)
	{
		mTasks.put(id, task);
		task.execute();
		return id;
	}
	
}
