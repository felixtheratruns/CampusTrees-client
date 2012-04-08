package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;
import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;

public class TreeInfoActivity extends Activity
{
	
	private static final String[] mSA_from = new String[] { "line1", "line2" };
	private static final int[] mSA_to = new int[] { android.R.id.text1, android.R.id.text2 };
	
	private Tree mTree;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		int treeID = getIntent().getExtras().getInt("tree");
		mTree = DataStore.getInstance().getTree(treeID);
		
		setContentView(R.layout.treeinfo);
		ListView lview = (ListView)findViewById(R.id.treeInfoList);
		MergeAdapter adapter = new MergeAdapter();
		
		adapter.addView(buildLabel("Species Info"));
		adapter.addAdapter(buildSpeciesStats());
		adapter.addView(buildLabel("Physical Info"));
		adapter.addAdapter(buildPhysicalStats());
		adapter.addView(buildLabel("Conservation Info"));
		adapter.addAdapter(buildConservationInfo());

		lview.setAdapter(adapter);
	}
	
	private View buildLabel(String text)
	{
		TextView tview = new TextView(this);
		tview.setTextSize(20f);
		tview.setText(text);
		return tview;
	}
	
	private HashMap<String,String> buildFactoid(String text1, Object text2)
	{
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("line1", text1);
		hm.put("line2",	text2.toString());
		return hm;
	}
	
	private ListAdapter buildSpeciesStats()
	{
		Species s = mTree.getSpecies();
		ArrayList<HashMap<String,String>> stats = new ArrayList<HashMap<String,String>>();
		
		String nativity = "[unknown]";
		switch(s.getNativity())
		{
		case KY:
			nativity = "Native to Kentucky";
			break;
		case US:
			nativity = "Native to US (non-KY)";
			break;
		case None:
			nativity = "Non-native to US"; 
			break;
		}
		
		String edible = "No";
		if(s.isEdible())
			edible = "Yes";
		
		stats.add(buildFactoid("Common Name", s.getName()));
		stats.add(buildFactoid("Native", nativity));
		stats.add(buildFactoid("Fruit Type",  s.getFruitType()));
		stats.add(buildFactoid("Edible (eat at own risk)", edible));
		stats.add(buildFactoid("Number on campus", s.getCount()));
		
		return makeFactAdapter(stats);
	}
	
	private ListAdapter buildPhysicalStats()
	{
		ArrayList<HashMap<String,String>> stats = new ArrayList<HashMap<String,String>>();
		
		stats.add(buildFactoid("Latitude", mTree.getLocation().getLatitudeE6() / 1E6f));
		stats.add(buildFactoid("Longitude", mTree.getLocation().getLongitudeE6() / 1E6f));
		stats.add(buildFactoid("Height (ft)", mTree.getHeight()));
		stats.add(buildFactoid("Diam. at Breast Height (ft)", mTree.getDBH()));
		stats.add(buildFactoid("Green Weight (tons)", mTree.getGreenWeight()));
		stats.add(buildFactoid("Dry Weight (tons)", mTree.getDryWeight()));
		stats.add(buildFactoid("Age (yrs)", mTree.getAge()));
		
		return makeFactAdapter(stats);
	}
	
	private ListAdapter buildConservationInfo()
	{
		ArrayList<HashMap<String,String>> stats = new ArrayList<HashMap<String,String>>();
		
		stats.add(buildFactoid("CO2 Sequestered", mTree.getCO2Seq()));
		
		return makeFactAdapter(stats);
	}
	
	private SimpleAdapter makeFactAdapter(ArrayList<HashMap<String,String>> facts)
	{
		return new SimpleAdapter(this, facts, android.R.layout.simple_list_item_2, mSA_from, mSA_to);
	}
	
}
