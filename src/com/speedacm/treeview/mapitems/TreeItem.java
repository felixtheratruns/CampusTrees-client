package com.speedacm.treeview.mapitems;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.speedacm.treeview.models.Tree;

public class TreeItem extends Overlay
{
	
	private Tree mTree;
	
	public TreeItem(Tree tree)
	{	
		mTree = tree;
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		// TODO: check to see if the tap is within a certain threshold
		return false;
	}
}
