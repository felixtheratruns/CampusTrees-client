/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.menu;


public class MenuItem {	
	private String mName;
	private int mIcon;
	private MenuActionListener mListener;
		
	public MenuItem(String name, int drawableId, MenuActionListener listener)
	{
		mName = name;
		mIcon = drawableId;
		mListener = listener;
	}
	
	public void action()
	{
		if(mListener != null)
			mListener.onMenuAction(this);
	}
	
	public String toString()
	{
		return mName;
	}

	// getters
	
	public int getDrawable()
	{
		return mIcon;
	}
	
	public String getName()
	{
		return mName;
	}
}
