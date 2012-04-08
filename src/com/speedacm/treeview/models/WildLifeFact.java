package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;

public class WildLifeFact {

	private String title;
	private String body;
	
	
	public WildLifeFact(String pTitle, String pBody)
	{
		title = pTitle;
		body = pBody;
	}
	
	public String getBody(){
		return body;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String toString()
	{
		return title + ":\n"  + body;
	}

	
}