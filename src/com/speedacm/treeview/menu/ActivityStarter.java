package com.speedacm.treeview.menu;

import android.app.Activity;
import android.content.Intent;

/**
 * This is an implementation of a MenuActionListener
 * that is able to start any activity on top of the
 * current activity.
 */
public class ActivityStarter implements MenuActionListener
{

	Activity mOldActivity;
	Class<?> mClass;
	
	public ActivityStarter(Activity act, Class<?> cls)
	{
		mOldActivity = act;
		mClass = cls;
	}
	
	@Override
	public void onMenuAction(MenuEntry item)
	{
		Intent switchAct = new Intent(mOldActivity, mClass);
		mOldActivity.startActivity(switchAct);
	}
}
