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
