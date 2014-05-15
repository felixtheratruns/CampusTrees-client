/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.views;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.PlantFact;

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
		
		if(payload != null){
			for(PlantFact p : payload){
				menuEntries.add(p);
			}
		}
	    PlantFactsArrayAdapter adapter = new PlantFactsArrayAdapter(
				getApplicationContext(), R.layout.plantfacts_row, menuEntries);
	    
		ListView lv = (ListView) this.findViewById(R.id.plantFactsList);
		
		// Set the ListView adapter
		lv.setAdapter(adapter);
	}

}
