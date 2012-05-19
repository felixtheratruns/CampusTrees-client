package com.speedacm.treeview.views;

import java.util.ArrayList;
import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.ScavHunt;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ScavHuntActivity extends Activity implements DSResultListener<ScavHunt[]>{
	
	
	private ArrayList<ScavHunt> menuEntries = new ArrayList<ScavHunt>();
	private static final String tag = "ScavHuntActivity";
	private int mCurrentFetchID = DataStore.NO_REQUEST;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scavhuntbox);
		mCurrentFetchID = DataStore.getInstance().beginGetAllScavHunt(this);

	}
	//test

	@Override
	public void onDSResultReceived(int requestID, ScavHunt[] payload) {
		// TODO Auto-generated method stub
		if(payload != null){
			for(ScavHunt p : payload){
				menuEntries.add(p);
			}
		}
	    ScavHuntArrayAdapter adapter = new ScavHuntArrayAdapter(
				getApplicationContext(), R.layout.scavhunt_row, menuEntries);
		
		ListView lv = (ListView) this.findViewById(R.id.scavhuntList);
		
		// Set the ListView adapter
		lv.setAdapter(adapter);

	}
}
