package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.Arrays;

import com.speedacm.treeview.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomToDoListAdapter extends ArrayAdapter<String> implements
		OnClickListener {

	private ArrayList<String> todoItems;
	private Context context;
	private ArrayList<String> selectedItems = new ArrayList<String>();

	final String SETTING_TODOLIST = "todolist";

	public CustomToDoListAdapter(Context context, int textViewResourceId,
			ArrayList<String> dataItems) {
		super(context, textViewResourceId, dataItems);
		this.context = context;
		this.todoItems = dataItems;
		LoadSelections();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.single_item, null);
		}
		String itemText = this.todoItems.get(position);

		TextView bTitle = (TextView) v.findViewById(R.id.txtTitle);
		CheckBox bCheck = (CheckBox) v.findViewById(R.id.bcheck);

		bCheck.setTag(itemText);

		if (this.selectedItems.contains(itemText))
			bCheck.setChecked(true);

		bCheck.setOnClickListener(this);

		bTitle.setText(itemText);
		return (v);
	}

	@Override
	public void onClick(View v) {
		CheckBox cBox = (CheckBox) v;
		String itemText = (String) cBox.getTag();

		Log.d("debug", "message from click listener");
		if (cBox.isChecked()) {
			if (!this.selectedItems.contains(itemText))
				this.selectedItems.add(itemText);
		} else {
			if (this.selectedItems.contains(itemText))
				this.selectedItems.remove(itemText);
		}

		SaveSelections();

	}

	public void ClearSelections() {
		this.selectedItems.clear();
		SaveSelections();
	}

	private String getSavedItems() {
		String savedItems = "";

		for (int i = 0; i < selectedItems.size(); i++) {

			if (savedItems.length() > 0) {
				savedItems += "," + this.selectedItems.get(i);
			} else {
				savedItems += this.selectedItems.get(i);
			}

		}
		return savedItems;
	}

	private void SaveSelections() {

		// save the selections in the shared preference in private mode for the
		// user

		SharedPreferences settingsActivity = this.context.getSharedPreferences(
				SETTING_TODOLIST, android.content.Context.MODE_PRIVATE);

		SharedPreferences.Editor prefEditor = settingsActivity.edit();

		String savedItems = getSavedItems();

		prefEditor.putString(SETTING_TODOLIST, savedItems);

		prefEditor.commit();
	}

	private void LoadSelections() {
		// if the selections were previously saved load them

		SharedPreferences settingsActivity = this.context.getSharedPreferences(
				SETTING_TODOLIST, android.content.Context.MODE_PRIVATE);

		if (settingsActivity.contains(SETTING_TODOLIST)) {
			String savedItems = settingsActivity
					.getString(SETTING_TODOLIST, "");

			this.selectedItems.addAll(Arrays.asList(savedItems.split(",")));

		}
	}

}