package com.speedacm.treeview.models;

public class News {

	private String title;
	private String body;
	

	public News(String pTitle, String pBody)
	{
		title = pTitle;
		body = pBody;
	}

	public String toString()
	{
		return title + ":\n"  + body;
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
