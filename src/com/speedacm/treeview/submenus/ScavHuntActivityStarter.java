/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.submenus;

import android.app.Activity;
import android.content.Intent;

import com.speedacm.treeview.models.ScavHunt;

public class ScavHuntActivityStarter implements ScavHuntMenuActionListener {

	Activity mOldActivity;
	Class<?> mClass;
	
	public ScavHuntActivityStarter(Activity act, Class<?> cls)
	{
		mOldActivity = act;
		mClass = cls;
	}
	
	@Override
	public void onMenuAction(ScavHuntMenuItem item)
	{
		Intent switchAct = new Intent(mOldActivity, mClass);
		switchAct.putExtra("position",item.getName());
		
	//	MyParcelable mp;
		
		ScavHunt s_hunt = item.getScavHunt();
		switchAct.putExtra("s_hunt",s_hunt);
		
		//switchAct.putExtra("body");
		mOldActivity.startActivity(switchAct);
		
	}
}
