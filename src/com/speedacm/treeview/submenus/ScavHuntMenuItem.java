/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.submenus;

import com.speedacm.treeview.models.ScavHunt;

public class ScavHuntMenuItem {
	private String mName;
	private ScavHuntMenuActionListener mListener;
	private ScavHunt mScavHunt;
	
	public ScavHuntMenuItem(String name, ScavHunt s_hunt, ScavHuntMenuActionListener listener)
	{
		mName = name;
		mScavHunt = s_hunt;
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
	public String getName()
	{
		return mName;
	}
	
	public ScavHunt getScavHunt(){
		return mScavHunt;
	}
	
}
