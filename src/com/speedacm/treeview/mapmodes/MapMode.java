package com.speedacm.treeview.mapmodes;

import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.Overlay;

public abstract class MapMode extends Overlay
{
	public abstract void onActivate();
	public abstract void onDeactivate();
	
	// this isn't quite the same as an Activity, but
	// they have some similar functions
	
	public abstract boolean onPrepareOptionsMenu(Menu menu);
	public abstract boolean onCreateOptionsMenu(Menu menu);
	public abstract boolean onOptionsItemSelected(MenuItem item);
}
