package com.speedacm.treeview.mapmodes;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.Overlay;

public abstract class MapMode extends Overlay
{
	public abstract void onActivate();
	public abstract void onDeactivate();
	
	// this isn't quite the same as an Activity, but
	// they have some similar functions
	
	public boolean onPrepareOptionsMenu(Menu menu) { return true; }
	public boolean onCreateOptionsMenu(Menu menu) { return true; }
	public boolean onOptionsItemSelected(MenuItem item) { return true; }
	public void onActivityResult(int requestCode, int resultCode, Intent data) { }
}
