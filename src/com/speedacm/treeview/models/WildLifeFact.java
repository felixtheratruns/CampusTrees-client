package com.speedacm.treeview.models;


public class WildLifeFact {

	private String title;
	private String body;
	private String date;

	

	public WildLifeFact(String pTitle, String pBody)
	{
		title = pTitle;
		body = pBody;
		
	}

	public String toString()
	{
		return title + ":\n"  + date + ":\n" + body;
	}

	public String getTitle()
	{
		return title;
	}
	
	public String getBody()
	{
		return body;
	}
	
}