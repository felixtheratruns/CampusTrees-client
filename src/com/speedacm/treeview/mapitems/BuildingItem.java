/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.mapitems;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.speedacm.treeview.R;
import com.speedacm.treeview.helpers.GeoMath;
import com.speedacm.treeview.models.Building;

public class BuildingItem extends Overlay
{
	private static Bitmap mIcon = null;
	private static Paint mPaint = null;
	
	// squared distance threshold for hit test
	private static final int hitThresh = 16*16;
	
	private Building mBuilding;
	
	public BuildingItem(Building b)
	{
		if(mPaint == null)
		{
			mPaint = new Paint();
			mPaint.setColor(Color.WHITE);
		}
		
		mBuilding = b;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		// first load the icon if we don't have it
		if(mIcon == null)
		{
			mIcon = BitmapFactory.decodeResource(mapView.getContext().getResources(), R.drawable.bulb);
		}
		
		// get the pixel location of the icon
		Point p = new Point();
		mapView.getProjection().toPixels(mBuilding.getLocation(), p);
		
		canvas.drawBitmap(mIcon, p.x - (mIcon.getWidth() / 2), p.y - (mIcon.getHeight() / 2), mPaint);
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		Projection proj = mapView.getProjection();
		Point hitPoint = new Point();
		Point bldPoint = new Point();
		
		proj.toPixels(p, hitPoint);
		proj.toPixels(mBuilding.getLocation(), bldPoint);
		
		return (GeoMath.pointDistanceSquared(hitPoint, bldPoint) < hitThresh);
	}
}
