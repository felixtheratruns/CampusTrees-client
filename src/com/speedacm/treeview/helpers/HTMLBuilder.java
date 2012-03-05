package com.speedacm.treeview.helpers;

public class HTMLBuilder
{
	
	private StringBuilder mSB;
	
	public HTMLBuilder()
	{
		mSB = new StringBuilder();
	}
	
	public HTMLBuilder begin()
	{
		mSB.append("<p>");
		return this;
	}
	
	private HTMLBuilder addHeaderLevel(int level, String s)
	{
		String ls = Integer.toString(level);
		mSB.append("<h" + ls + ">" + s + "</h" + ls + ">");
		return this;
	}
	
	public HTMLBuilder addH1(String s)
	{
		//mSB.append("<h1>" + s + "</h1>");
		return addHeaderLevel(1,s);
	}
	
	public HTMLBuilder addH2(String s)
	{
		//mSB.append("<h2>" + s + "</h2>");
		return addHeaderLevel(2,s);
	}
	
	public HTMLBuilder addH3(String s)
	{
		return addHeaderLevel(3,s);
	}
	
	public HTMLBuilder addH4(String s)
	{
		return addHeaderLevel(4,s);
	}
	
	public HTMLBuilder addH5(String s)
	{
		return addHeaderLevel(5,s);
	}
	
	public HTMLBuilder addH6(String s)
	{
		return addHeaderLevel(6,s);
	}
	
	public HTMLBuilder addString(String s)
	{
		mSB.append(s);
		return this;
	}
	
	public HTMLBuilder addBold(String s)
	{
		mSB.append("<b>" + s + "</b>");
		return this;
	}
	
	public HTMLBuilder addBreak()
	{
		mSB.append("<br />");
		return this;
	}
	
	public String end()
	{
		mSB.append("</p>");
		return mSB.toString();
	}
}
