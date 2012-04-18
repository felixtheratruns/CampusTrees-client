package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.List;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.ScavHunt;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class ScavHuntArrayAdapter extends ArrayAdapter<ScavHunt>{
	private static final String titleFieldStart = "";
	private static final String dateFieldStart = "Date: ";
	private static final String bodyFieldStart = "";		
	
	
	private static final String tag = "ScavHuntArrayAdapter";
	private Context context;
	private TextView scavhuntTitle;
	private TextView scavhuntDate;
	private TextView scavhuntBody;
	private List<ScavHunt> scavhunt_arr = new ArrayList<ScavHunt>();

	public ScavHuntArrayAdapter(Context context, int textViewResourceId,
			List<ScavHunt> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.scavhunt_arr = objects;
	}

	public int getCount() {
		return this.scavhunt_arr.size();
	}

	public ScavHunt getItem(int index) {
		return this.scavhunt_arr.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.scavhunt_row, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		ScavHunt scavhunt = getItem(position);
		
		// get references to subviews
		scavhuntTitle= (TextView) row.findViewById(R.id.title);
		scavhuntDate = (TextView) row.findViewById(R.id.date);
		scavhuntBody = (TextView) row.findViewById(R.id.body);
		
		// set up final values
		scavhuntTitle.setText(Html.fromHtml(titleFieldStart + scavhunt.getTitle()));
		scavhuntDate.setText(Html.fromHtml(dateFieldStart + scavhunt.getDate()));
		scavhuntBody.setText(Html.fromHtml(bodyFieldStart + scavhunt.getBody()));

		return row;
	}
}
