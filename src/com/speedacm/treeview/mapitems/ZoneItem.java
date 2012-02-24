package com.speedacm.treeview.mapitems;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.speedacm.treeview.models.Zone;

public class ZoneItem extends Overlay
{
	
	private Zone mZone;
	
	public ZoneItem(Zone zone)
	{
		mZone = zone;
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		// TODO: compare the point to see if it falls within the polygon
		return false;
	}
}
