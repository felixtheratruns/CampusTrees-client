package com.speedacm.treeview.mapitems;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.speedacm.treeview.models.Tree;

public class MultiTreeItem extends Overlay
{
	
	private List<Tree> mTrees;
	private Paint mPaint;
	
	public MultiTreeItem(List<Tree> trees, Paint paint)
	{
		mTrees = trees;
		mPaint = paint;
	}
	
	public MultiTreeItem(List<Tree> trees)
	{
		mTrees = trees;
		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
	}
	
	public MultiTreeItem()
	{
		this(new ArrayList<Tree>());
	}
	
	public void addTree(Tree t)
	{
		mTrees.add(t);
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		// TODO: check to see if the tap hits one of our trees
		return false;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapview, boolean shadow)
	{
		if(shadow) return; // not drawing shadow layer at the moment
		
		for(Tree t : mTrees)
		{
			// figure out the screen coordinates for the tree
			Point screenPt = new Point();
			mapview.getProjection().toPixels(t.getLocation(), screenPt);
			
			if(screenPt.x < 0 || screenPt.y < 0 || screenPt.x > mapview.getWidth() || screenPt.y > mapview.getHeight())
				continue;
			
			canvas.drawCircle(screenPt.x, screenPt.y, 4f, mPaint);
		}
	}

}
