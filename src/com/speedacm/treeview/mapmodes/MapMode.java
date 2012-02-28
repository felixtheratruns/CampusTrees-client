package com.speedacm.treeview.mapmodes;

import com.google.android.maps.Overlay;

public abstract class MapMode extends Overlay
{
	public abstract void onActivate();
	public abstract void onDeactivate();
}
