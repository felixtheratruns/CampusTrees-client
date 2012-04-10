package com.speedacm.treeview.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Species.NativeType;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.WildLifeFact;
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
			Log.e("json parse exception?", "exception", e);
			return null;
		}
	}
	
	public Tree parseTreeResponse(String json)
	{
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return null;
		
		return parseTreeData(rootNode);
	}
	
	private Tree parseTreeData(JsonNode treeNode)
	{	
		int id = treeNode.path("id").asInt(-1);
		double lat = treeNode.path("lat").asDouble(Double.NaN);
		double lng = treeNode.path("long").asDouble(Double.NaN);
		int sid = treeNode.path("sid").asInt(-1);
		double dbh = treeNode.path("dbh").asDouble(Double.NaN);
		double height = treeNode.path("height").asDouble(Double.NaN);
		double greenwt = treeNode.path("greenwt").asDouble(Double.NaN);
		double drywt = treeNode.path("drywt").asDouble(Double.NaN);
		double co2seqtot = treeNode.path("co2seqwt").asDouble(Double.NaN);
		double co2seqyr = treeNode.path("co2pyear").asDouble(Double.NaN);
		int age = treeNode.path("age").asInt();
		
		
		if(id == -1 || lat == Double.NaN || lng == Double.NaN || sid == -1)
		{
			return null;
		}
		
		int latE6 = (int)(lat * 1E6);
		int lngE6 = (int)(lng * 1E6);
		
		return new Tree(
				id, sid, new GeoPoint(latE6, lngE6), (float)dbh, (float)height,
				(float)greenwt, (float)drywt, age, (float)co2seqtot, (float)co2seqyr);
	}
	
	private ArrayList<GeoPoint> getPointsFromNode(JsonNode arrayNode)
	{
		if(arrayNode == null) return null;
		
		ArrayList<GeoPoint> points = new ArrayList<GeoPoint>(arrayNode.size());
		Iterator<JsonNode> pointIter = arrayNode.getElements();
		
		while(pointIter.hasNext())
		{
			JsonNode pointNode = pointIter.next();
			double latitude = pointNode.path("lat").asDouble(Double.NaN);
			double longitude = pointNode.path("long").asDouble(Double.NaN);
			
			// this will trigger if these properties don't exist
			if(latitude == Double.NaN || longitude == Double.NaN ) return null;
			
			// convert lat/long to latE6/longE6 and return a point
			points.add(new GeoPoint((int)(latitude * 1E6),(int)(longitude * 1E6)));
		}
		
		return points;
	}
	
	public PlantFact[] parseAllPlantFactsResponse(String json)
	{
		
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return null;
		
		ArrayList<PlantFact> plantFacts = new ArrayList<PlantFact>(rootNode.size());
		
		Iterator<JsonNode> pFactIter = rootNode.getElements();
		Iterator<String> pFactNames = rootNode.getFieldNames();
		while(pFactIter.hasNext() && pFactNames.hasNext())
		{			
			JsonNode pFactNode = pFactIter.next();
			String pFactName = pFactNames.next();
			try
			{	
				String title = pFactName;
				String body = pFactNode.toString();
				plantFacts.add(new PlantFact(title, body));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return plantFacts.toArray(new PlantFact[plantFacts.size()]);
	}
	
	public News[] parseAllNewsResponse(String json)
	{
		
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return null;
		
		ArrayList<News> News = new ArrayList<News>(rootNode.size());
		
		Iterator<JsonNode> newsIter = rootNode.getElements();
		Iterator<String> newsNames = rootNode.getFieldNames();
		while(newsIter.hasNext() && newsNames.hasNext())
		{			
			JsonNode newsNode = newsIter.next();
			String newsName = newsNames.next();
			try
			{	
				String title = newsName;
				String body = newsNode.toString();
				News.add(new News(title, body));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return News.toArray(new News[News.size()]);
	}
	
	public WildLifeFact[] parseAllWildLifeFactsResponse(String json)
	{
		
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return null;
		
		ArrayList<WildLifeFact> WildLifeFacts = new ArrayList<WildLifeFact>(rootNode.size());
		
		Iterator<JsonNode> pFactIter = rootNode.getElements();
		Iterator<String> pFactNames = rootNode.getFieldNames();
		while(pFactIter.hasNext() && pFactNames.hasNext())
		{			
			JsonNode pFactNode = pFactIter.next();
			String pFactName = pFactNames.next();
			try
			{	
				String title = pFactName;
				String body = pFactNode.toString();
				WildLifeFacts.add(new WildLifeFact(title, body));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return WildLifeFacts.toArray(new WildLifeFact[WildLifeFacts.size()]);
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
				int zoneID = zoneNode.path("id").asInt(-1);
				if(zoneID == -1) return null;
				
				ArrayList<GeoPoint> points = getPointsFromNode(zoneNode.path("points"));
				
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

	public void parseZoneTreesResponse(String json, Zone target)
	{
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return;
		
		ArrayList<Tree> trees = new ArrayList<Tree>(rootNode.size());
		
		Iterator<JsonNode> treeIter = rootNode.getElements();
		while(treeIter.hasNext())
		{
			JsonNode treeNode = treeIter.next();
			
			Tree t = parseTreeData(treeNode);
			
			if(t != null)
				trees.add(parseTreeData(treeNode));
		}
		
		target.setTrees(trees);
	}

	public Species[] parseSpeciesResponse(String json)
	{
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return null;
		
		ArrayList<Species> specs = new ArrayList<Species>(rootNode.size());
		
		Iterator<JsonNode> specIter = rootNode.getElements();
		while(specIter.hasNext())
		{
			JsonNode specNode = specIter.next();
			
			int sid = specNode.path("sid").asInt(-1);
			String cname = specNode.path("commonname").asText();
			boolean nativeUS = specNode.path("american").asBoolean();
			boolean nativeKY = specNode.path("ky").asBoolean();
			boolean edible = specNode.path("edible").asBoolean();
			String fruittype = specNode.path("fruittype").asText();
			int count = specNode.path("count").asInt(0);
			
			// capitalize first letter of fruit type
			char[] ft = fruittype.toCharArray();
			ft[0] = Character.toUpperCase(ft[0]);
			fruittype = new String(ft);
			
			if(sid == -1) continue;
			
			NativeType nat;
			if(nativeKY)
				nat = NativeType.KY;
			else if(nativeUS)
				nat = NativeType.US;
			else
				nat = NativeType.None;
			
			specs.add(new Species(sid, cname, fruittype, edible, nat, count));
		}
		
		return specs.toArray(new Species[specs.size()]);
	}

	public List<Integer> parseSeasonListResponse(String json)
	{
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return null;
		
		ArrayList<Integer> ret = new ArrayList<Integer>(rootNode.size());
		
		Iterator<JsonNode> specIter = rootNode.getElements();
		while(specIter.hasNext())
		{
			JsonNode specNode = specIter.next();
			
			int sID = specNode.path("sid").asInt(-1);
			if(sID != -1)
				ret.add(sID);
		}
		
		return ret;
	}
}
