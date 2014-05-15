/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
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
