package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.List;

import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.PlantFact;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlantFactsActivity extends Activity implements DSResultListener<PlantFact[]>
{
	
	private ArrayList<PlantFact> menuEntries = new ArrayList<PlantFact>();

	private static final String tag = "PlantFactsActivity";
	private int mCurrentFetchID = DataStore.NO_REQUEST;

	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plantfactsbox);
		mCurrentFetchID = DataStore.getInstance().beginGetAllPlantFacts(this);

		
	}
	
    // bind to our menu items
	@Override
	public void onDSResultReceived(int requestID, PlantFact[] payload) {
		
		for(PlantFact p : payload){
			menuEntries.add(p);
		}
	    ListView mmenu = (ListView)findViewById(R.id.plantFactsList);
	    mmenu.setAdapter(new ArrayAdapter<PlantFact>(this, android.R.layout.simple_list_item_1, menuEntries));

	}

}
