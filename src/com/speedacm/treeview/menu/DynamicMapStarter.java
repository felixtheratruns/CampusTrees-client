/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.menu;

import android.app.Activity;
import android.content.Intent;

import com.speedacm.treeview.views.DynamicMapActivity;

public class DynamicMapStarter implements MenuActionListener
{

	private int mType;
	private Activity mOldActivity;
	
	public DynamicMapStarter(Activity oldAct, int type)
	{	
		mOldActivity = oldAct;
		mType = type;
	}
	
	@Override
	public void onMenuAction(MenuItem item)
	{
		Intent mapIntent = new Intent(mOldActivity, DynamicMapActivity.class);
		mapIntent.putExtra("mapmode", mType);
		mOldActivity.startActivity(mapIntent);
	}

}
