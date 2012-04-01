package com.speedacm.treeview.mapmodes;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.graphics.Canvas;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.mapitems.BuildingItem;
import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.views.DynamicMapActivity;

public class SustainMode extends MapMode
{

	private List<BuildingItem> mBuildings;
	private DynamicMapActivity mParent;
	private int mCurrentRequestID = DataStore.NO_REQUEST;
	
	private DSResultListener<Building[]> mBuildingLoadListener =
		new DSResultListener<Building[]>() {

			@Override
			public void onDSResultReceived(int requestID, Building[] payload) {
				
				Toast.makeText(mParent, "Buildings Loaded", Toast.LENGTH_SHORT).show();
				
				for(Building b : payload)
					mBuildings.add(new BuildingItem(b));
				mCurrentRequestID = DataStore.NO_REQUEST;
				mParent.setBusyIndicator(false);
			}
		};
	
	public SustainMode(DynamicMapActivity parent)
	{
		mBuildings = new ArrayList<BuildingItem>();
		mParent = parent;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		if(shadow) return;
		
		for(BuildingItem item : mBuildings)
		{
			item.draw(canvas, mapView, shadow);
		}
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		for(BuildingItem item : mBuildings)
		{
			if(item.onTap(p, mapView))
			{
				popUpBuildingInfo(item);
				return true;
			}
		}
		
		return false;
	}
	
	private void popUpBuildingInfo(BuildingItem item)
	{
		Dialog d = new Dialog(mParent);
		d.setTitle("Building Info");
		d.setContentView(R.layout.buildinginfo);
		d.show();
	}
	
	public void onActivate()
	{
		mCurrentRequestID = DataStore.getInstance().beginGetAllBuildings(mBuildingLoadListener);
		mParent.setBusyIndicator(true);
	}
	
	public void onDeactivate()
	{
		if(mCurrentRequestID != DataStore.NO_REQUEST)
			DataStore.getInstance().cancelRequest(mCurrentRequestID);
	}
	
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		menu.findItem(R.id.mapm_filtertrees).setVisible(false);
		menu.findItem(R.id.mapm_treetypes).setVisible(false);
		return true;
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		return true;
	}
	
}
