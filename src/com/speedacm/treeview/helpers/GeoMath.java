package com.speedacm.treeview.helpers;

import android.graphics.Point;

public final class GeoMath
{
	/**
	 * Tests to see if a point lies within a polygon.
	 * @param pt
	 * @param polygon
	 * @return Whether or not the point is within the polygon
	 */
	public static boolean pointInPolygon(Point pt, Iterable<Point> polygon)
	{
		
		boolean first = true;
		int intersections = 0;
		
		// first get the maximum X coordinate from the list
		int maxX = Integer.MIN_VALUE;
		for(Point p : polygon)
			maxX = Math.max(maxX, p.x);
		maxX += 1; // make sure it lies beyond the final line

		// we're creating a horizontal line segment
		Point rayPoint = new Point(maxX, pt.y);
		
		Point prevPoint = null;
		Point firstPoint = null;
		
		// check all intersections between the ray and the edges
		for(Point point : polygon)
		{
			if(first)
			{
				firstPoint = point;
				first = false;
			}
			if(prevPoint != null)
			{
				if(lineIntersect(pt, rayPoint, prevPoint, point))
					intersections++;
			}
			prevPoint = point;
		}
		
		// now complete the loop by checking the edge formed
		// by the last point and the first point
		if(lineIntersect(pt, rayPoint, prevPoint, firstPoint))
			intersections++;
		
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
	public static boolean lineIntersect(Point a1, Point a2, Point b1, Point b2)
	{
		
		float denom = (b2.y - b1.y) * (a2.x - a1.x) - (b2.x - b1.x) * (a2.y - a1.y);
		if(denom == 0) return false;
		
		// figure out how "far" on each line the intersection point lies
		float uA = ((b2.x - b1.x) * (a1.y - b1.y) - (b2.y - b1.y) * (a1.x - b1.x)) / denom;
		float uB = ((a2.x - a1.x) * (a1.y - b1.y) - (a2.y - a1.y) * (a1.x - b1.x)) / denom;
		
		// if both are within 0 and 1, that means the intersection point lies
		// on the lines themselves. Otherwise, the segments don't intersect.
		return (uA > 0 && uA < 1 && uB > 0 && uB < 1);
	}
}
