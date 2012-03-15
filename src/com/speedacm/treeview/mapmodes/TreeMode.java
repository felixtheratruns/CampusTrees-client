package com.speedacm.treeview.mapmodes;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Point;
import android.text.Html;
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
import com.speedacm.treeview.models.Species;
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
	private int mCurrentFetchID = DataStore.NO_REQUEST;
	
	// listener for loading the initial species
	private DSResultListener<Species[]> mSpeciesLoadListener =
		new DSResultListener<Species[]>() {
			
			@Override
			public void onDSResultReceived(int requestID, Species[] payload) {
				// chain the species loader into the zone loader (we need species before trees)
				mCurrentFetchID = DataStore.getInstance().beginGetAllZones(mZoneLoadListener);
			}
		};
	
	// listener for when we load the original zones
	private DSResultListener<Zone[]> mZoneLoadListener =
		new DSResultListener<Zone[]>() {
			
			@Override
			public void onDSResultReceived(int requestID, Zone[] payload) {
				Toast.makeText(mParent, "Zones Loaded", Toast.LENGTH_SHORT).show();
				mParent.setBusyIndicator(false);
				mCurrentFetchID = DataStore.NO_REQUEST;
				
				for(Zone z : payload)
					mZoneItems.add(new ZoneItem(z));
				
				mParent.invalidateMap();
			}
		};
	
	private DSResultListener<Zone> mZoneDetailsListener = 
		new DSResultListener<Zone>() {
		
			@Override
			public void onDSResultReceived(int requestID, Zone payload) {
				
				// tell the map to redraw, and make it so we are drawing
				// the current list of trees from this zone
				
				mParent.setBusyIndicator(false);
				mCurrentFetchID = DataStore.NO_REQUEST;
				if(payload.isFetched())
					mActiveTrees = getTreesFromZone(payload);
				else
				{
					mActiveZone = null;
					mActiveTrees = null;
					Toast.makeText(mParent, "Error loading trees for zone.", Toast.LENGTH_SHORT).show();
				}
				mParent.invalidateMap();
			}
		};

	public TreeMode(DynamicMapActivity parent)
	{
		mZoneItems = new ArrayList<ZoneItem>();
		mActiveTrees = null;
		mActiveZone = null;
		mParent = parent;
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
				Zone z = zi.getZone();
				if(z.isFetched())
				{
					mActiveTrees = getTreesFromZone(z);
				}
				else
				{
					
					DataStore ds = DataStore.getInstance();
					// if we're currently fetching something, stop and do another
					if(mCurrentFetchID != DataStore.NO_REQUEST)
						ds.cancelRequest(mCurrentFetchID);
					mCurrentFetchID = ds.beginGetZoneDetails(z, mZoneDetailsListener);
					mParent.setBusyIndicator(true);
				}
				mActiveZone = zi;
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
			.addH5("Species").addString("todo")
			.addH5("DBH").addString("todo")
			.addH5("Age").addString("todo")
			.addH5("O2 Production").addString("todo")
			.addH5("CO2 Sequestered/yr").addString("todo")
			.addH5("CO2 Seq. total").addString("todo")
			.addH5("Weight").addString("todo")
			.addH5("Carbon amount").addString("todo")
			.end()));
		
		
		// TODO: find each view within the dialog and set their values
		
		d.show();
	}
	
	public void onActivate()
	{
		// the event handler for fetching the species will then fire off the zone loader
		mParent.setBusyIndicator(true);
		mCurrentFetchID = DataStore.getInstance().beginGetAllSpecies(mSpeciesLoadListener);
	}
	
	public void onDeactivate()
	{
		if(mCurrentFetchID != DataStore.NO_REQUEST)
			DataStore.getInstance().cancelRequest(mCurrentFetchID);
	}
}
