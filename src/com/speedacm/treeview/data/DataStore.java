package com.speedacm.treeview.data;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.speedacm.treeview.models.Zone;

public class DataStore
{
	
	/*
	 * Asynchronous Functions
	 */
	
	public int beginGetAllZones(final DSResultListener<Zone[]> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		AsyncTask<Void, Void, Zone[]> task = new AsyncTask<Void, Void, Zone[]>() {
			@Override
			protected Zone[] doInBackground(Void... params) {
				return getAllZones();
			}
			
			@Override
			protected void onPostExecute(Zone[] result) {
				listener.onDSResultReceived(newRequestID, result);
				mTasks.remove(this);
			}
		};
		
		mTasks.put(newRequestID, task);
		task.execute();
		
		return newRequestID;
	}
	
	public int beginGetZone(final int id, final DSResultListener<Zone> listener)
	{
		final int newRequestID = mNextRequestID++;
		
		AsyncTask<Void, Void, Zone> task = new AsyncTask<Void, Void, Zone>() {
			@Override
			protected Zone doInBackground(Void... params) {
				return getZone(id);
			}
			
			@Override
			protected void onPostExecute(Zone result) {
				listener.onDSResultReceived(newRequestID, result);
				mTasks.remove(this);
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
		return null;
	}
	
	public Zone getZone(int id)
	{
		return null;
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
		AsyncTask<Void, Void, ?> task = mTasks.remove(requestID);
		
		if(task != null)
			task.cancel(false);
	}
	
	/*
	 * Private stuff
	 */
	
	private static DataStore mSingleton = null;
	
	private int mNextRequestID = 1;
	private Map<Integer, AsyncTask<Void, Void, ?>> mTasks;
	
	private DataStore()
	{
		mTasks = new HashMap<Integer, AsyncTask<Void,Void,?>>();
	}
	
}
