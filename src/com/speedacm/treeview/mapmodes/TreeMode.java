package com.speedacm.treeview.mapmodes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.google.android.maps.MapView;
import com.speedacm.treeview.mapitems.MultiTreeItem;
import com.speedacm.treeview.mapitems.ZoneItem;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;
import com.speedacm.treeview.views.DynamicMapActivity;


public class TreeMode extends MapMode
{
	
	private List<ZoneItem> mZoneItems;
	private List<Tree> mTrees;
	private MultiTreeItem mActiveTrees;
	private ZoneItem mActiveZone;
	private DynamicMapActivity mParent;

	public TreeMode(DynamicMapActivity parent)
	{
		mZoneItems = new ArrayList<ZoneItem>();
		mTrees = new ArrayList<Tree>();
		mActiveTrees = null;
		mActiveZone = null;
		mParent = parent;
	}
	
	/**
	 * Fetches the tree/zone data from the server
	 */
	private void fetchZones()
	{
		mZoneItems.clear();
	}
	
	private void fetchTreesForZone(Zone z)
	{
		mActiveTrees = null;
	}
	
	private MultiTreeItem getTreesFromZone(Zone z)
	{
		return null;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		if(shadow) return;
		
		for(ZoneItem z : mZoneItems)
		{
			// don't bother displaying the zone we're currently
			// displaying trees from
			if(z == mActiveZone)
				continue;
			
			z.draw(canvas, mapView, shadow);
		}
		
		if(mActiveTrees != null)
			mActiveTrees.draw(canvas, mapView, shadow);
	}
	
	public void onActivate()
	{
		
	}
	
	public void onDeactivate()
	{
		
	}
}
