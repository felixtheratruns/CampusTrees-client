package com.speedacm.treeview.helpers;

import java.util.List;

import com.google.android.maps.GeoPoint;

public final class GeoMath
{
	/**
	 * Tests to see if a point lies within a polygon.
	 * @param pt
	 * @param polygon
	 * @return Whether or not the point is within the polygon
	 */
	public static boolean pointInPolygon(GeoPoint pt, List<GeoPoint> polygon)
	{
		
		int intersections = 0;
		
		// first get the maximum X coordinate from the list
		int maxX = Integer.MIN_VALUE;
		for(GeoPoint p : polygon)
			maxX = Math.max(maxX, p.getLatitudeE6());
		maxX += 1; // make sure it lies beyond the final line

		// we're creating a horizontal line segment
		GeoPoint rayPoint = new GeoPoint(maxX, pt.getLongitudeE6());
		
		GeoPoint prevPoint = null;
		for(GeoPoint point : polygon)
		{
			if(prevPoint != null)
			{
				if(lineIntersect(pt, rayPoint, prevPoint, point))
					intersections++;
			}
			prevPoint = point;
		}
		
		// if the number of intersections is odd,
		// the point lies within the polygon.
		return (intersections % 2) == 1;
	}
	
	/**
	 * Tests whether or not two line segments intersect.
	 * @param a1 The first point of the first segment.
	 * @param a2 The second point of the first segment.
	 * @param b1 The first point of the second segment.
	 * @param b2 The second point of the second segment.
	 * @return True if the lines intersect, False otherwise.
	 */
	public static boolean lineIntersect(GeoPoint a1, GeoPoint a2, GeoPoint b1, GeoPoint b2)
	{
		
		float a1x = a1.getLongitudeE6() / 1E6f;
		float a1y = a1.getLatitudeE6() / 1E6f;
		
		float a2x = a2.getLongitudeE6() / 1E6f;
		float a2y = a2.getLatitudeE6() / 1E6f;
		
		float b1x = b1.getLongitudeE6() / 1E6f;
		float b1y = b1.getLatitudeE6() / 1E6f;
		
		float b2x = b2.getLongitudeE6() / 1E6f;
		float b2y = b2.getLatitudeE6() / 1E6f;
		
		float denom = (b2y - b1y) * (a2x - a1x) - (b2x - b1x) * (a2y - a1y);
		if(denom == 0) return false;
		
		// figure out how "far" on each line the intersection point lies
		float uA = ((b2x - b1x) * (a1y - b1y) - (b2y - b1y) * (a1x - b1x)) / denom;
		float uB = ((a2x - a1x) * (a1y - b1y) - (a2y - a1y) * (a1x - b1x)) / denom;
		
		// if both are within 0 and 1, that means the intersection point lies
		// on the lines themselves. Otherwise, the segments don't intersect.
		return (uA > 0 && uA < 1 && uB > 0 && uB < 1);
	}
}
