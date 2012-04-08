package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.List;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.PlantFact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class PlantFactsArrayAdapter extends ArrayAdapter<PlantFact>{
	private static final String tag = "PlantFactArrayAdapter";
	private Context context;
	private TextView plantFactTitle;
	private TextView plantFactBody;
	private List<PlantFact> plantFactsArr = new ArrayList<PlantFact>();

	public PlantFactsArrayAdapter(Context context, int textViewResourceId,
			List<PlantFact> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.plantFactsArr = objects;
	}

	public int getCount() {
		return this.plantFactsArr.size();
	}

	public PlantFact getItem(int index) {
		return this.plantFactsArr.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.plantfacts_row, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		PlantFact plant_fact = getItem(position);
		
		// get references to subviews
		plantFactTitle= (TextView) row.findViewById(R.id.toptext);
		plantFactBody = (TextView) row.findViewById(R.id.bottomtext);
		//Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), PlantFact.getDrawable());
		
		// set up final values
		plantFactTitle.setText(plant_fact.getTitle());
		plantFactBody.setText(plant_fact.getBody());

		return row;
	}
}
