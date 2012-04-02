package com.speedacm.treeview.mapitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.speedacm.treeview.filters.Filter;
import com.speedacm.treeview.models.Tree;

public class MultiTreeItem extends Overlay implements Iterable<Tree>
{
	
	private HashMap<Tree, Boolean> mFilterTrees;
	private List<Tree> mTrees;
	private Paint mPaint;
	private Paint mFilterPaint;
	private static final float TREE_RADIUS =  4f;
	
	public MultiTreeItem(List<Tree> trees, Paint paint, Paint filterPaint)
	{
		mTrees = trees;
		mPaint = paint;
		mFilterPaint = filterPaint;
		mFilterTrees = new HashMap<Tree, Boolean>();
		updateFilter(null);
	}
	
	public MultiTreeItem(List<Tree> trees)
	{
		this(trees, new Paint(), new Paint());
		mPaint.setColor(Color.BLUE);
		mFilterPaint.setColor(Color.GRAY);
	}
	
	public MultiTreeItem()
	{
		this(new ArrayList<Tree>());
	}
	
	public void addTree(Tree t)
	{
		mTrees.add(t);
	}
	
	public void updateFilter(Filter f)
	{
			for(Tree t : mTrees)
				mFilterTrees.put(t, (f == null) || f.withinFilter(t));
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
			
			if(mFilterTrees.get(t))
				canvas.drawCircle(screenPt.x, screenPt.y, TREE_RADIUS, mPaint);
			else
				canvas.drawCircle(screenPt.x, screenPt.y, TREE_RADIUS, mFilterPaint);
		}
	}

	@Override
	public Iterator<Tree> iterator() {
		return mTrees.iterator(); 
	}

}
