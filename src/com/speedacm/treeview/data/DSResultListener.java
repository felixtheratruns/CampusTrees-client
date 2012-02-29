package com.speedacm.treeview.data;

public interface DSResultListener<T>
{
	public void onDSResultReceived(int requestID, T payload); 
}
