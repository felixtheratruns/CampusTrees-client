package com.speedacm.treeview.helpers;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.Projection;

public final class GeoToScreen
{
	public static List<Point> Convert(Projection proj, List<GeoPoint> points)
	{
		ArrayList<Point> plist = new ArrayList<Point>(points.size());
		
		for(GeoPoint gp : points)
		{
			Point p = new Point();
			proj.toPixels(gp, p);
			plist.add(p);
		}
		
		return plist;
	}
}

