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
import com.speedacm.treeview.R;

public class BuildingItem extends Overlay
{
	private static Bitmap mIcon = null;
	private static Paint mPaint = null;
	
	private GeoPoint mLocation;
	
	public BuildingItem(GeoPoint location)
	{
		if(mPaint == null)
		{
			mPaint = new Paint();
			mPaint.setColor(Color.WHITE);
		}
		
		mLocation = location;
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
		mapView.getProjection().toPixels(mLocation, p);
		
		canvas.drawBitmap(mIcon, p.x - (mIcon.getWidth() / 2), p.y - (mIcon.getHeight() / 2), mPaint);
	}
}
