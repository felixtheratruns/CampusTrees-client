package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.List;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.WildLifeFact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WildLifeFactsArrayAdapter extends ArrayAdapter<WildLifeFact>{
	private static final String titleFieldStart = "";
	private static final String bodyFieldStart = "";	
	
	private static final String tag = "NewsArrayAdapter";
	private Context context;
	private TextView wildLifeTitle;
	private TextView wildLifeBody;
	private List<WildLifeFact> wildLifeFactsArr = new ArrayList<WildLifeFact>();

	public WildLifeFactsArrayAdapter(Context context, int textViewResourceId,
			ArrayList<WildLifeFact> menuEntries) {
		super(context, textViewResourceId, menuEntries);
		this.context = context;
		this.wildLifeFactsArr = menuEntries;
	}
	

	public int getCount() {
		return this.wildLifeFactsArr.size();
	}

	public WildLifeFact getItem(int index) {
		return this.wildLifeFactsArr.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.wildlifefacts_row, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		WildLifeFact wild_life_fact = getItem(position);
		
		// get references to subviews
		wildLifeTitle= (TextView) row.findViewById(R.id.title);
		wildLifeBody = (TextView) row.findViewById(R.id.body);
		
		// set up final values
		wildLifeTitle.setText(titleFieldStart + wild_life_fact.getTitle());
		wildLifeBody.setText(bodyFieldStart + wild_life_fact.getBody());
		return row;
	}
}
