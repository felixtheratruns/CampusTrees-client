package com.speedacm.treeview.views;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.Species;

public class SpeciesSearchActivity extends Activity implements OnItemClickListener, TextWatcher
{
	
	private ArrayAdapter<Species> mAdapter = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speciessearch);
		
		// load species and sort alphabetically
		List<Species> speciesList = Arrays.asList(DataStore.getInstance().getAllSpecies());
		Collections.sort(speciesList, new Comparator<Species>() {
			@Override
			public int compare(Species lhs, Species rhs) {
				return lhs.getName().compareTo(rhs.getName());
			}
		});
		
		// set up our TextChanged filter
		TextView tv = (TextView)findViewById(R.id.ssearch_text);
		tv.addTextChangedListener(this);
		
		// bind the species data to the ListView
		mAdapter = new ArrayAdapter<Species>(this, android.R.layout.simple_list_item_1, speciesList);
		ListView lv = (ListView)findViewById(R.id.ssearch_list);
		lv.setOnItemClickListener(this);
		lv.setAdapter(mAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Species spec = (Species)parent.getItemAtPosition(position);
		Intent resultIntent = new Intent();
		resultIntent.putExtra("sid", spec.getID());
		setResult(RESULT_OK, resultIntent);
		finish();
	}

	@Override
	public void afterTextChanged(Editable s)
	{
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		mAdapter.getFilter().filter(s);
	}
}
