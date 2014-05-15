/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
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
