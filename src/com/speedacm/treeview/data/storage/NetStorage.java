package com.speedacm.treeview.data.storage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.speedacm.treeview.data.DataParser;
import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.WildLifeFact;
import com.speedacm.treeview.models.Zone;

public class NetStorage extends AbstractStorage
{
	
	private static final String baseURL = "http://trees.cecsresearch.org/AppHandler.php";
	private HttpClient mClient;
	private DataParser mParser;


	public NetStorage(AbstractStorage fallback)
	{
		super(fallback);
		mClient = new DefaultHttpClient();
		mParser = new DataParser();
	}

	@Override
	public Zone[] getAllZones()
	{
		String json = getHTTPResponse(baseURL + "?zoneRequest=1");
		return mParser.parseAllZonesResponse(json);
	}

	@Override
	public void getZoneDetails(Zone target)
	{
		String json = getHTTPResponse(baseURL + "?zone=" + Integer.toString(target.getID()));
		mParser.parseZoneTreesResponse(json, target);
	}

	@Override
	public Tree getTree(int id)
	{
		String json = getHTTPResponse(baseURL + "?t=" + Integer.toString(id));
		return mParser.parseTreeResponse(json);
	}

	@Override
	public Building[] getAllBuildings()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Species[] getAllSpecies()
	{
		String json = getHTTPResponse(baseURL + "?speciesRequest=1");
		return mParser.parseSpeciesResponse(json);
	}
	
	@Override
	public Species getSpecies(int id)
	{
		// TODO maybe implement specific retrieval of species
		return null;
	}
	
	@Override
	public List<Integer> getFloweringSpecies(int month)
	{
		String json = getHTTPResponse(baseURL + "?flowerMonth=" + Integer.toString(month));
		return mParser.parseSeasonListResponse(json);
	}

	@Override
	public List<Integer> getFruitingSpecies(int month)
	{
		String json = getHTTPResponse(baseURL + "?fruitMonth=" + Integer.toString(month));
		return mParser.parseSeasonListResponse(json);
	}
	
	private String getHTTPResponse(String url)
	{
		try
		{
			// execute the GET request and get its resulting InputStream
			HttpGet get = new HttpGet(url);
			HttpResponse resp = mClient.execute(get);
			InputStream is = resp.getEntity().getContent();
			
			// now convert the InputStream to a String
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder(is.available());
			
			String line;
			while((line = r.readLine()) != null)
			{
				sb.append(line);
			}
			
			return sb.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	//menu item functions other than map"http://trees.cecsresearch.org/joelapi/AppHandler?pFacts=1"
	@Override
	public PlantFact[] getAllPlantFacts() {
		String json = getHTTPResponse("http://trees.cecsresearch.org/joelapi/AppHandler?pFacts=1");
		return mParser.parseAllPlantFactsResponse(json);
	}
	
	@Override
	public News[] getAllNews() {
		String json = getHTTPResponse(baseURL + "?news=1");
		return mParser.parseAllNewsResponse(json);
	}
	
	@Override
	public WildLifeFact[] getAllWildLifeFacts() {
		String json = getHTTPResponse(baseURL + "?wFacts=1");
		return mParser.parseAllWildLifeFactsResponse(json);
	}
	
}
