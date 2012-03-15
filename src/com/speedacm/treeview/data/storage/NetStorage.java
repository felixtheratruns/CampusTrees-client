package com.speedacm.treeview.data.storage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.speedacm.treeview.data.DataParser;
import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;

public class NetStorage extends AbstractStorage
{
	
	private static final String baseURL = "http://trees.cecsresearch.org/dev/AppHandler.php";
	private HttpClient mClient;
	private DataParser mParser;

	public NetStorage(AbstractStorage fallback)
	{
		super(fallback);
		mClient = new DefaultHttpClient();
		mParser = new DataParser();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
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

}
