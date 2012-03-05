package com.speedacm.treeview.mapmodes;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Point;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Projection;
import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.helpers.GeoMath;
import com.speedacm.treeview.helpers.HTMLBuilder;
import com.speedacm.treeview.mapitems.MultiTreeItem;
import com.speedacm.treeview.mapitems.ZoneItem;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;
import com.speedacm.treeview.views.DynamicMapActivity;


public class TreeMode extends MapMode
{
	
	// tree hit threshold, in pixels (squared)
	private final float treeHitThresh = 20f*20f;
	
	private List<ZoneItem> mZoneItems;
	private MultiTreeItem mActiveTrees;
	private ZoneItem mActiveZone;
	private DynamicMapActivity mParent;
	
	// listener for when we load the original zones
	private DSResultListener<Zone[]> mZoneLoadListener =
		new DSResultListener<Zone[]>() {
			
			@Override
			public void onDSResultReceived(int requestID, Zone[] payload) {
				// TODO: actually process the loading of zones
				Toast.makeText(mParent, "Zones Loaded", Toast.LENGTH_SHORT).show();
				mParent.setBusyIndicator(false);
			}
		};

	public TreeMode(DynamicMapActivity parent)
	{
		mZoneItems = new ArrayList<ZoneItem>();
		mActiveTrees = null;
		mActiveZone = null;
		mParent = parent;
		
		mParent.setBusyIndicator(true);
		DataStore.getInstance().beginGetAllZones(mZoneLoadListener);
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
		MultiTreeItem mti = new MultiTreeItem(z.getTrees());
		return mti;
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
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		if(mActiveZone != null)
		{
			Projection proj = mapView.getProjection();
			Point hitPoint = new Point();
			proj.toPixels(p, hitPoint);
			
			if(mActiveZone.pointInZone(p, mapView))
			{
				Tree closestTree = null;
				float closestDistance = Float.MAX_VALUE;
				
				// process the trees, see if one is hit
				for(Tree t : mActiveTrees)
				{
					Point treePoint = new Point();
					proj.toPixels(t.getLocation(), treePoint);
					
					float distsqr = GeoMath.pointDistanceSquared(treePoint, hitPoint);
					
					if(distsqr < treeHitThresh && distsqr < closestDistance)
					{
						closestTree = t;
						closestDistance = distsqr;
					}
				}
				
				if(closestTree != null)
				{
					// we hit a tree
					popUpTreeInfo(closestTree);
					return true;
				}
				
				return false; // no tree in zone was hit
			}
		}
	
		// if we didn't hit a tree (or don't have an active zone)
		// then try and switch to a zone if we can
		return attemptSwitchZones(p, mapView);
	}

	private boolean attemptSwitchZones(GeoPoint p, MapView mapView)
	{
		
		for(ZoneItem zi : mZoneItems)
		{
			if(zi.pointInZone(p, mapView))
			{
				mActiveZone = zi;
				mActiveTrees = getTreesFromZone(zi.getZone());
				return true;
			}
		}
		
		return false;
	}
	
	private void popUpTreeInfo(Tree t)
	{
		// instantiate dialog and load layout
		Dialog d = new Dialog(mParent);
		d.setContentView(R.layout.treeinfo);
		d.setTitle("Tree Information");
		
		HTMLBuilder hb = new HTMLBuilder();
		TextView htmlText = (TextView)d.findViewById(R.id.treeHtmlText);
		
		htmlText.setText(Html.fromHtml(hb.begin()
			.addH5("Species").addString("Birch")
			.addH5("DBH").addString("unknown")
			.addH5("Age").addString("39")
			.addH5("O2 Production").addString("unknown")
			.addH5("CO2 Sequestered/yr").addString("unknown")
			.addH5("CO2 Seq. total").addString("unknown")
			.addH5("Weight").addString("unknown")
			.addH5("Carbon amount").addString("something")
			.end()));
		
		
		// TODO: find each view within the dialog and set their values
		
		d.show();
	}
	
	public void onActivate()
	{
		// setup some fake data
		Zone z = new Zone();
		z.addPoint(new GeoPoint(38215852,-85758372));
		z.addPoint(new GeoPoint(38215852,-85757972));
		z.addPoint(new GeoPoint(38215452,-85757972));
		z.addPoint(new GeoPoint(38215452,-85758372));
		
		Tree t = new Tree();
		t.setLocation(new GeoPoint(38215652,-85758172));
		z.addTree(t);
		
		mZoneItems.add(new ZoneItem(z));
	}
	
	public void onDeactivate()
	{
		
	}
}
