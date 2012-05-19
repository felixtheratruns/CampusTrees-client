package com.speedacm.treeview.models;

public class ScavHunt {

	private String title;
	private String date;
	private String body;
	
	

	public ScavHunt(String pTitle,String pDate, String pBody)
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
