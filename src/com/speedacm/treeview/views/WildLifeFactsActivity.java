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
import com.speedacm.treeview.models.WildLifeFact;

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
