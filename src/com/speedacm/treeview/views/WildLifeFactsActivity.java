package com.speedacm.treeview.views;

import java.util.ArrayList;

import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.WildLifeFact;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WildLifeFactsActivity extends Activity implements DSResultListener<WildLifeFact[]>{
	private ArrayList<WildLifeFact> menuEntries = new ArrayList<WildLifeFact>();

	private static final String tag = "WildLifeFactsActivity";
	private int mCurrentFetchID = DataStore.NO_REQUEST;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wildlifefactsbox);
		mCurrentFetchID = DataStore.getInstance().beginGetAllWildLifeFacts(this);

	}
	
	@Override
	public void onDSResultReceived(int requestID, WildLifeFact[] payload) {
		for(WildLifeFact p : payload){
			menuEntries.add(p);
		}
	    ListView mmenu = (ListView)findViewById(R.id.wildLifeFactsList);
	    mmenu.setAdapter(new ArrayAdapter<WildLifeFact>(this, android.R.layout.simple_list_item_1, menuEntries));

	}
}
