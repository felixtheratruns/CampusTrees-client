package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;
import com.speedacm.treeview.R;

public class CredsActivity extends Activity
{
	
	private static final String[] mSA_from = new String[] { "line1", "line2" };
	private static final int[] mSA_to = new int[] { android.R.id.text1, android.R.id.text2 };
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credsbox);
		
		MergeAdapter adapter = new MergeAdapter();
		ListView lv = (ListView)findViewById(R.id.creditsList);
		
		adapter.addView(buildLabel("Android Development Team"));
		adapter.addAdapter(makeAndroidTeam());
		
		adapter.addView(buildLabel("Urban Wildlife Research Lab"));
		adapter.addAdapter(makeUWRLTeam());
		
		adapter.addView(buildLabel("Dr. Parker's BIOL 263 Class"));
		adapter.addAdapter(makeBIOL263Team());
		
		adapter.addView(buildLabel("Animal Data Collection"));
		adapter.addAdapter(makeAnimalDataTeam());
		
		adapter.addView(buildLabel("Application Artwork"));
		adapter.addAdapter(makeArtworkTeam());
		
		adapter.addView(buildLabel("Center for GIS"));
		adapter.addAdapter(makeGISTeam());
		
		lv.setAdapter(adapter);
	}
	
	private View buildLabel(String text)
	{
		TextView tview = new TextView(this);
		tview.setTextSize(20f);
		tview.setHeight(100);
		tview.setText(text);
		tview.setGravity(Gravity.BOTTOM);
		return tview;
	}
	
	private HashMap<String,String> buildEntry(String text1, String text2)
	{
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("line1", text1);
		hm.put("line2",	text2);
		return hm;
	}
	
	private SimpleAdapter makeAdapter(ArrayList<HashMap<String,String>> facts)
	{
		return new SimpleAdapter(this, facts, android.R.layout.simple_list_item_2, mSA_from, mSA_to);
	}
	
	private ListAdapter makeAndroidTeam()
	{
		ArrayList<HashMap<String,String>> creds = new ArrayList<HashMap<String,String>>();
		
		creds.add(buildEntry("Michael Brown", "Android Developer - Tree Map and Backend"));
		creds.add(buildEntry("Joel Cambon", "Android Developer - User Interface"));
		creds.add(buildEntry("Rachel Determann", "Database design and Web API"));
		creds.add(buildEntry("Jimmy Murphy", "Web API and Server Administration"));
		
		return makeAdapter(creds);
	}
	
	private ListAdapter makeArtworkTeam()
	{
		ArrayList<HashMap<String,String>> creds = new ArrayList<HashMap<String,String>>();
		
		creds.add(buildEntry("Patrick Hill", "Interface Icons and App Icon"));
		
		return makeAdapter(creds);
	}
	
	private ListAdapter makeUWRLTeam()
	{
		ArrayList<HashMap<String,String>> creds = new ArrayList<HashMap<String,String>>();
		
		creds.add(buildEntry("Dr. Tommy S Parker", "Original App Idea, Primary Contact"));
		creds.add(buildEntry("J. Gavin Bradley", "Tree IDs, Tree Height Measurements"));
		creds.add(buildEntry("Shinelle Gonzales", "DBH and Crown Height Measurements"));
		creds.add(buildEntry("Bill Pearson", "Tree IDs, DBH, Crown Ht, Data Quality"));
		creds.add(buildEntry("Maggie Gregg", "GPS Data Collection"));
		creds.add(buildEntry("Jennifer Rice", "DBH, Crown Height Measurements"));
		creds.add(buildEntry("Carson Winkler", "DBH, Crown Height, GPS"));
		
		return makeAdapter(creds);
	}
	
	private ListAdapter makeBIOL263Team()
	{
		ArrayList<HashMap<String,String>> creds = new ArrayList<HashMap<String,String>>();
		
		creds.add(buildEntry("Carri Bartimus-Bibelhauser","Tree Survey"));
		creds.add(buildEntry("Maxwell Mason","Tree Survey"));
		creds.add(buildEntry("Kittrina Bartlett","Tree Survey"));
		
		return makeAdapter(creds);
	}
	
	private ListAdapter makeAnimalDataTeam()
	{
		ArrayList<HashMap<String,String>> creds = new ArrayList<HashMap<String,String>>();
		
		creds.add(buildEntry("Mammalogy 571/671 Classes","Animal Data Collection"));
		creds.add(buildEntry("Urban Wildlife Research Lab","Animal Data Collection"));
		
		return makeAdapter(creds);
	}
	
	private ListAdapter makeGISTeam()
	{
		ArrayList<HashMap<String,String>> creds = new ArrayList<HashMap<String,String>>();
		
		creds.add(buildEntry("Bob Forbes","Director"));
		creds.add(buildEntry("Donald J Biddle","GIS Specialist"));
		
		return makeAdapter(creds);
	}
}
