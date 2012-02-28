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
	public void onMenuAction(MenuEntry item)
	{
		Intent mapIntent = new Intent(mOldActivity, DynamicMapActivity.class);
		mapIntent.putExtra("mapmode", mType);
		mOldActivity.startActivity(mapIntent);
	}

}
