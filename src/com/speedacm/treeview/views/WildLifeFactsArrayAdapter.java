/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.views;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.WildLifeFact;

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
		wildLifeTitle.setText(Html.fromHtml(titleFieldStart + wild_life_fact.getTitle()));
		wildLifeBody.setText(Html.fromHtml(bodyFieldStart + wild_life_fact.getBody()));
		return row;
	}
}
