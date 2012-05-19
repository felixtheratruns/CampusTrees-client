package com.speedacm.treeview.models;

public class PlantFact {
	
	private String mTitle;
	private Tree mTree;
	
	
	public PlantFact(String title, Tree tree)
	{
		mTitle = title;
		mTree = tree;
	}
	
	public String getTitle()
	{
		return mTitle;
	}
	
	public Tree getTree()
	{
		return mTree;
	}

	
}
