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
		if(payload != null){
			for(WildLifeFact p : payload){
				menuEntries.add(p);
			}
		}
	    WildLifeFactsArrayAdapter adapter = new WildLifeFactsArrayAdapter(
				getApplicationContext(), R.layout.wildlifefacts_row, menuEntries);
	    
		ListView lv = (ListView) this.findViewById(R.id.wildLifeFactsList);
		
		// Set the ListView adapter
		lv.setAdapter(adapter);
	}
}
