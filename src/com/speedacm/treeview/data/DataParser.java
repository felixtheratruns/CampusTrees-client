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
import com.speedacm.treeview.models.ScavHunt;
import com.speedacm.treeview.models.ScavHuntSubItem;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Species.NativeType;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.WildLifeFact;
import com.speedacm.treeview.models.Zone;

public class DataParser
{
	private String tag = "DataParser";
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
		JsonParser jp = null;
		try
		{
			jp = mMapper.getJsonFactory().createJsonParser(json);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.e("json parse exception?", "exception", e);
			return null;
		}
		
		try
		{
			return mMapper.readTree(jp);
		}
		catch(Exception e){
			e.printStackTrace();
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
		double crownarea = treeNode.path("crownarea").asDouble(Double.NaN);
		double vol = treeNode.path("vol").asDouble(Double.NaN);
		double carbonwt = treeNode.path("carbonwt").asDouble(Double.NaN);
		String monetary = "$" + treeNode.path("mvalue").asText();
		
		if(id == -1 || lat == Double.NaN || lng == Double.NaN || sid == -1)
		{
			return null;
		}
		
		int latE6 = (int)(lat * 1E6);
		int lngE6 = (int)(lng * 1E6);
		
		return new Tree(
				id, sid, new GeoPoint(latE6, lngE6), (float)dbh, (float)height,
				(float)greenwt, (float)drywt, age, (float)co2seqtot, (float)co2seqyr,
				(float)crownarea, (float)vol, (float)carbonwt, monetary);
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
		while(pFactIter.hasNext())
		{			
			JsonNode pFactNode = pFactIter.next();	

			try
			{	
				String title = pFactNode.path("title").asText();
				Tree tree = parseTreeData(pFactNode);
				plantFacts.add(new PlantFact(title, tree));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return plantFacts.toArray(new PlantFact[plantFacts.size()]);
	}
	
	public ScavHunt[] parseAllScavHuntsResponse(String json)
	{
		if(json == null || json.equals("")) return null;
		JsonNode rootNode = mapNode(json);
		
		if(rootNode == null) return null;
		
		ArrayList<ScavHunt> scavHunt = new ArrayList<ScavHunt>(rootNode.size());
		Iterator<JsonNode> scavhuntIter = rootNode.getElements();
		//Iterator<String> newsNames = rootNode.getFieldNames();
		while(scavhuntIter.hasNext())
		{
			JsonNode scavHuntNode = scavhuntIter.next();
			//String newsName = newsNames.next();
			try
			{
				String title = scavHuntNode.path("title").asText();
				String scavid = scavHuntNode.path("scavid").asText();
			//	int scav_id = (int)(new Integer(scavid));
				scavHunt.add(new ScavHunt(title,(int)(new Integer(scavid))));
				
				
			/*
				JsonNode items = scavHuntNode.findValue("items");
				Iterator<JsonNode> item_bodies = items.getElements();
				JsonNode cur;
				String item_title;
				String item;
				ArrayList<String> bodies = new ArrayList<String>();
				ArrayList<String> titles = new ArrayList<String>();
				ArrayList<ScavHuntSubItem> sub_items = new ArrayList<ScavHuntSubItem>();
				
				while(item_bodies.hasNext()){
					cur = item_bodies.next();
					item_title = cur.path("item_title").asText();
					item = cur.path("item").asText();
					titles.add(item_title);
					bodies.add(item);
					
					sub_items.add(new ScavHuntSubItem(item_title,item));
				}
				ScavHunt.add(new ScavHunt(title, sub_items));
			*/
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return scavHunt.toArray(new ScavHunt[scavHunt.size()]);
	}
	
	
	
	public ScavHuntSubItem[] parseAllScavHuntSubItemsResponse(String json)
	{
		if(json == null || json.equals("")) return null;
		JsonNode rootNode = mapNode(json);
		
		if(rootNode == null) return null;
		
		ArrayList<ScavHuntSubItem> scavHuntSubItems = new ArrayList<ScavHuntSubItem>(rootNode.size());
		Iterator<JsonNode> scavhuntsubitemIter = rootNode.getElements();
		//Iterator<String> newsNames = rootNode.getFieldNames();
		while(scavhuntsubitemIter.hasNext())
		{
			JsonNode scavHuntNode = scavhuntsubitemIter.next();
			//String newsName = newsNames.next();
			try
			{
				String title = scavHuntNode.path("title").asText();
				String body = scavHuntNode.path("body").asText();
				String png_name = scavHuntNode.path("png_name").asText();
				scavHuntSubItems.add(new ScavHuntSubItem(title, body, png_name));
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return scavHuntSubItems.toArray(new ScavHuntSubItem[scavHuntSubItems.size()]);
	}
	
	
	
	/*
		public ScavHunt[] parseAllScavHuntResponse(String json)
	{
		if(json == null || json.equals("")) return null;
		JsonNode rootNode = mapNode(json);
		
		
		if(rootNode == null) return null;
		
		ArrayList<ScavHunt> ScavHunt = new ArrayList<ScavHunt>(rootNode.size());
		Iterator<JsonNode> scavhuntIter = rootNode.getElements();
		//Iterator<String> newsNames = rootNode.getFieldNames();
		while(scavhuntIter.hasNext())
		{
			JsonNode scavHuntNode = scavhuntIter.next();
			//String newsName = newsNames.next();
			try
			{
				String title = scavHuntNode.path("title").asText();
				JsonNode items = scavHuntNode.findValue("items");
				Iterator<JsonNode> item_bodies = items.getElements();
				JsonNode cur;
				String item_title;
				String item;
				ArrayList<String> bodies = new ArrayList<String>();
				ArrayList<String> titles = new ArrayList<String>();
				ArrayList<ScavHuntSubItem> sub_items = new ArrayList<ScavHuntSubItem>();
				
				while(item_bodies.hasNext()){
					cur = item_bodies.next();
					item_title = cur.path("item_title").asText();
					item = cur.path("item").asText();
					titles.add(item_title);
					bodies.add(item);
					
					sub_items.add(new ScavHuntSubItem(item_title,item));
				}
				ScavHunt.add(new ScavHunt(title, sub_items));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return ScavHunt.toArray(new ScavHunt[ScavHunt.size()]);
	}
	
	*/
	
	public News[] parseAllNewsResponse(String json)
	{
		
		JsonNode rootNode = mapNode(json);
		if(rootNode == null) return null;
		
		ArrayList<News> News = new ArrayList<News>(rootNode.size());
		
		Iterator<JsonNode> newsIter = rootNode.getElements();
		//Iterator<String> newsNames = rootNode.getFieldNames();
		while(newsIter.hasNext())
		{			
			JsonNode newsNode = newsIter.next();
			//String newsName = newsNames.next();

			try
			{	
				String title = newsNode.path("title").asText();
				String date = newsNode.path("date").asText();
				String body = newsNode.path("body").asText();
				News.add(new News(title, date, body));
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
		
		Iterator<JsonNode> wFactIter = rootNode.getElements();

		while(wFactIter.hasNext())
		{			
			JsonNode wFactNode = wFactIter.next();

			try
			{	
				String title = wFactNode.path("title").asText();
				String body = wFactNode.path("body").asText();
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
				int zoneID = zoneNode.path("zpid").asInt(-1);
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
