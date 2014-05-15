/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.List;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.News;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class NewsArrayAdapter extends ArrayAdapter<News>{
	private static final String titleFieldStart = "";
	private static final String dateFieldStart = "Date: ";
	private static final String bodyFieldStart = "";		
	
	
	private static final String tag = "NewsArrayAdapter";
	private Context context;
	private TextView newsTitle;
	private TextView newsDate;
	private TextView newsBody;
	private List<News> news_arr = new ArrayList<News>();

	public NewsArrayAdapter(Context context, int textViewResourceId,
			List<News> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.news_arr = objects;
	}

	public int getCount() {
		return this.news_arr.size();
	}

	public News getItem(int index) {
		return this.news_arr.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.news_row, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");

		}

		// Get item
		News news = getItem(position);
		
		// get references to subviews
		newsTitle= (TextView) row.findViewById(R.id.title);
		newsDate = (TextView) row.findViewById(R.id.date);
		newsBody = (TextView) row.findViewById(R.id.body);
		
		// set up final values
		newsTitle.setText(Html.fromHtml(titleFieldStart + news.getTitle()));
		newsDate.setText(Html.fromHtml(dateFieldStart + news.getDate()));
		newsBody.setText(Html.fromHtml(bodyFieldStart + news.getBody()));

		return row;
	}
}
