/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
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
