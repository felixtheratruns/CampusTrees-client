package com.speedacm.treeview.data;

import java.util.ArrayList;
import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.android.maps.GeoPoint;
import com.speedacm.treeview.models.Zone;

public class DataParser
{
	private ObjectMapper mMapper;
	
	public DataParser()
	{
		mMapper = new ObjectMapper();
	}
	
	private JsonNode mapNode(String json)
	{
		// at this point, we're "all or nothing" with regards to the JSON stuff,
		// so if any error occurs during the parsing process, just return null
		// hence, all Exception types regardless of subclass are caught here
		
		try
		{
			JsonParser jp = mMapper.getJsonFactory().createJsonParser(json);
			return mMapper.readTree(jp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<GeoPoint> getPointsFromNode(JsonNode arrayNode)
	{
		if(arrayNode == null) return null;
		
		ArrayList<GeoPoint> points = new ArrayList<GeoPoint>(arrayNode.size());
		Iterator<JsonNode> pointIter = arrayNode.getElements();
		
		while(pointIter.hasNext())
		{
			JsonNode pointNode = pointIter.next();
			double latitude = pointNode.get("lat").asDouble(Double.NaN);
			double longitude = pointNode.get("long").asDouble(Double.NaN);
			
			// this will trigger if these properties don't exist
			if(latitude == Double.NaN || longitude == Double.NaN ) return null;
			
			// convert lat/long to latE6/longE6 and return a point
			points.add(new GeoPoint((int)(latitude * 1E6),(int)(longitude * 1E6)));
		}
		
		return points;
	}
	
	public Zone[] parseAllZonesResponse(String json)
	{
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return null;
		
		ArrayList<Zone> zones = new ArrayList<Zone>(rootNode.size());
		
		Iterator<JsonNode> zoneIter = rootNode.getElements();
		while(zoneIter.hasNext())
		{
			JsonNode zoneNode = zoneIter.next();
			
			try
			{
				int zoneID = zoneNode.get("id").asInt(-1);
				if(zoneID == -1) return null;
				
				ArrayList<GeoPoint> points = getPointsFromNode(zoneNode.get("points"));
				
				zones.add(new Zone(zoneID, points));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return zones.toArray(new Zone[zones.size()]);
	}
}
