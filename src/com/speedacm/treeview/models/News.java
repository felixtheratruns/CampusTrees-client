package com.speedacm.treeview.models;

public class News {

	private String title;
	private String date;
	private String body;
	
	

	public News(String pTitle,String pDate, String pBody)
	{
		title = pTitle;
		date = pDate;
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
	
	public String getDate()
	{
		return date;
	}
	
	public String getBody()
	{
		return body;
	}
	
	
	
}
