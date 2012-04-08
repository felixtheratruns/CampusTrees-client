package com.speedacm.treeview.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.commonsware.cwac.merge.MergeAdapter;
import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.Tree;

public class TreeInfoActivity extends Activity
{
	
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
		
		lview.setAdapter(adapter);
	}
}
