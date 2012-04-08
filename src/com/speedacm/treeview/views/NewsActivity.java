package com.speedacm.treeview.views;

import java.util.ArrayList;

import com.speedacm.treeview.MenuItemArrayAdapter;
import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.WildLifeFact;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsActivity extends Activity implements DSResultListener<News[]>{
	
	
	private ArrayList<News> menuEntries = new ArrayList<News>();
	private static final String tag = "NewsActivity";
	private int mCurrentFetchID = DataStore.NO_REQUEST;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsbox);
		mCurrentFetchID = DataStore.getInstance().beginGetAllNews(this);

	}
	//test

	@Override
	public void onDSResultReceived(int requestID, News[] payload) {
		// TODO Auto-generated method stub
		for(News p : payload){
			menuEntries.add(p);
		}
		
		
	    NewsArrayAdapter adapter = new NewsArrayAdapter(
				getApplicationContext(), R.layout.news_row, menuEntries);
	//    ListView mmenu = (ListView)findViewById(R.id.newsbox);
	    
		ListView lv = (ListView) this.findViewById(R.id.newsList);
		
		// Set the ListView adapter
		lv.setAdapter(adapter);
	    
//	    mmenu.setAdapter(new ArrayAdapter<News>(this, android.R.layout.simple_list_item_1, menuEntries));


	    
	    
	}

	
	
}
