package com.speedacm.treeview.data;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.speedacm.treeview.models.Zone;

public class DataStore
{
	
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
	
	/*
	 * Synchronous Functions
	 */
	
	public Zone[] getAllZones()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
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
	
	private DataStore()
	{
		mTasks = new HashMap<Integer, DSTask<?>>();
	}
	
}
