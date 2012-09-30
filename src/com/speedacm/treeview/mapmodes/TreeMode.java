package com.speedacm.treeview.mapmodes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Projection;
import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.filters.EdibleFilter;
import com.speedacm.treeview.filters.Filter;
import com.speedacm.treeview.filters.HistoricalFilter;
import com.speedacm.treeview.filters.NativeFilter;
import com.speedacm.treeview.filters.SeasonalFilter;
import com.speedacm.treeview.filters.SeasonalFilter.SeasonalType;
import com.speedacm.treeview.filters.SpeciesFilter;
import com.speedacm.treeview.helpers.GeoMath;
import com.speedacm.treeview.mapitems.MultiTreeItem;
import com.speedacm.treeview.mapitems.ZoneItem;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Species.NativeType;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;
import com.speedacm.treeview.views.DynamicMapActivity;
import com.speedacm.treeview.views.SpeciesSearchActivity;
import com.speedacm.treeview.views.TreeInfoActivity;


public class TreeMode extends MapMode
{
	
	// tree hit threshold, in pixels (squared)
	private final float treeHitThresh = 20f*20f;
	
	// request IDs
	private final int SPECIES_SEARCH_REQID = 1;
	
	private List<ZoneItem> mZoneItems;
	private MultiTreeItem mActiveTrees;
	private ZoneItem mActiveZone;
	private DynamicMapActivity mParent;
	private int mCurrentFetchID = DataStore.NO_REQUEST;

	private Filter mFilter;
	
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
				mParent.setBusyIndicator(false);
				mCurrentFetchID = DataStore.NO_REQUEST;
				
				if(payload == null)
				{
					// something went wrong loading the zones
					showText("Error loading zones, check connection.");
					return;
				}
				
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
				{
					mActiveTrees = getTreesFromZone(payload);
					mActiveTrees.updateFilter(mFilter);
				}
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
			{
				z.drawOutline(canvas, mapView, false);
				continue;
			}
			
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
				
				// are we still loading a zone?
				if(mActiveTrees == null)
					return false;
				
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
					mActiveTrees.updateFilter(mFilter);
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
		Intent in = new Intent(mParent, TreeInfoActivity.class);
		in.putExtra("tree", t.getID());
		mParent.startActivity(in);
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
	
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		menu.findItem(R.id.mapm_filtertrees).setVisible(true);
		menu.findItem(R.id.mapm_treetypes).setVisible(true);
		return true;
	}
	
	private void showText(String text)
	{
		Toast.makeText(mParent, text, Toast.LENGTH_SHORT).show();
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		switch(id)
		{
		case R.id.mapm_filtertrees:	
			break;
			
		case R.id.mapm_treetypes:
			
			Intent in = new Intent(mParent, SpeciesSearchActivity.class);
			mParent.startActivityForResult(in, SPECIES_SEARCH_REQID);
			
			return false;
			
		case R.id.mapmf_none:
			updateFilter(null);
			break;
			
		case R.id.mapmf_flowering:
			updateFilter(new SeasonalFilter(SeasonalType.Flowering));
			break;
			
		case R.id.mapmf_fruiting:
			updateFilter(new SeasonalFilter(SeasonalType.Fruiting));
			break;
			
		case R.id.mapmf_edible:
			updateFilter(new EdibleFilter());
			break;
			
		case R.id.mapmf_nativeky:
			updateFilter(new NativeFilter(NativeType.KY));
			break;
			
		case R.id.mapmf_nativeus:
			updateFilter(new NativeFilter(NativeType.US));
			break;
			
		case R.id.mapmf_nonnative:
			updateFilter(new NativeFilter(NativeType.None));
			break;
			
		case R.id.mapmf_historical:
			showText("Historical filter not yet implemented.");
			updateFilter(new HistoricalFilter());
			break;
			
		default:
			showText("Unknown item ID: " + Integer.toString(id));
			break;
		}
		
		return true;
	}
	
	private void updateFilter(Filter f)
	{
		mFilter = f;
		if(mActiveTrees != null)
			mActiveTrees.updateFilter(mFilter);
		mParent.invalidateMap();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode != SPECIES_SEARCH_REQID)
		{
			showText("Unknown Activity Result");
			return;
		}
		
		if(resultCode == Activity.RESULT_OK)
		{
			int sID = data.getIntExtra("sid", -1);
			if(sID != -1)
			{
				Species s = DataStore.getInstance().getSpecies(sID);
				updateFilter(new SpeciesFilter(s));
			}
		}
		else
			showText(Integer.toString(resultCode));
		
		mParent.invalidateMap();
	}
}
