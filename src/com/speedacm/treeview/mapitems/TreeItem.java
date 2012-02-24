package com.speedacm.treeview.mapitems;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.speedacm.treeview.models.Tree;

public class TreeItem extends Overlay
{
	
	private static Paint mPaint = null;
	private Tree mTree;
	
	public TreeItem(Tree tree)
	{	
		if(mPaint == null)
		{
			mPaint = new Paint();
			mPaint.setColor(Color.BLUE);
		}
		mTree = tree;
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		// TODO: check to see if the tap is within a certain threshold
		return false;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapview, boolean shadow)
	{	
		if(shadow) return;
		
		// figure out the screen coordinates for our tree
		Point screenPt = new Point();
		mapview.getProjection().toPixels(mTree.getLocation(), screenPt);
		
		if(screenPt.x < 0 || screenPt.y < 0 || screenPt.x > mapview.getWidth() || screenPt.y > mapview.getHeight())
			return;
		
		canvas.drawCircle(screenPt.x, screenPt.y, 4f, mPaint);
	}
}
