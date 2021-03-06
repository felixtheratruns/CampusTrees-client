/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.views;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import com.speedacm.treeview.R;
import com.speedacm.treeview.data.storage.NetStorage;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.ScavHunt;
import com.speedacm.treeview.models.ScavHuntSubItem;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.ScavHuntSubItem;

public class ScavHuntListAdapter extends ArrayAdapter<ScavHuntSubItem> implements
		OnClickListener {
	final private String tag = "ScavHuntListAdapter";
	private ArrayList<ScavHuntSubItem> todoItems;
	private String scavHuntTitle;
	private Context context;
	private ArrayList<String> selectedItems = new ArrayList<String>();
	final String SETTING_TODOLIST = "scavhuntlist";

	public ScavHuntListAdapter(Context context, int textViewResourceId,
			ArrayList<ScavHuntSubItem> dataItems, String p_title) {
		super(context, textViewResourceId, dataItems);
		this.context = context;
		this.todoItems = dataItems;
		this.scavHuntTitle = p_title;
		LoadSelections();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
			if(v != null){
			CheckBox cBox = (CheckBox) v;
			String itemText = (String) cBox.getTag();
			if (cBox.isChecked()){
				if(!this.selectedItems.contains(itemText))
					this.selectedItems.add(itemText);
			} else {
				if(this.selectedItems.contains(itemText))
					this.selectedItems.remove(itemText);
			}
		SaveSelections();
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		// TODO Auto-generated method stub
		
		
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.scavhunt_row, null);
		}
		
		ScavHuntSubItem item = this.todoItems.get(position);
		TextView bTitle = (TextView) v.findViewById(R.id.title);
		TextView bBody = (TextView) v.findViewById(R.id.body);
		CheckBox bCheck = (CheckBox) v.findViewById(R.id.bcheck);
		ImageView Image01 = (ImageView) v.findViewById(R.id.ImageView01);
		
		try{
	        String url =  NetStorage.getImageBaseURL() + item.getPngName();           
	        Drawable image = ImageOperations(this,url);
	        Image01.setImageDrawable(image);
	    }
	    catch(Exception ex)
	    {
	        ex.printStackTrace();
	    }

		bCheck.setTag(item.getTitle());
		if (this.selectedItems.contains(item.getTitle())){
			bCheck.setChecked(true);
		} else {
			bCheck.setChecked(false);
		}

		bCheck.setOnClickListener(this);
		bTitle.setText(item.getTitle());
		bBody.setText(item.getBody());
		
		Log.d("POSITION_:", new Integer(position).toString());
		
		return (v);
		
	}
	
	public Object fetch(String address) throws MalformedURLException,
    IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }  
	
	
	public Drawable ImageOperations(ScavHuntListAdapter ctx, String url) {
        try {
            InputStream is = (InputStream) this.fetch(url);
            Drawable d = Drawable.createFromStream(is, "src");
            return d;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }


	public void ClearSelections(){
		this.selectedItems.clear();
		SaveSelections();
	}
	
	private String getSavedItems() {
		String savedItems = "";
		for(int i =0; i <selectedItems.size(); i++){
			Log.d("debug","for loop select: "+ this.selectedItems.get(i));
			if(savedItems.length() >0){
				savedItems += "," + this.selectedItems.get(i);
			} else {
				savedItems += this.selectedItems.get(i);
			}
		}
		Log.d("debug", "Saved items: "+ savedItems);
		return savedItems;
	}
	
	
	private void SaveSelections(){
		SharedPreferences settingsActivity =
			this.context.getSharedPreferences(
					this.scavHuntTitle+SETTING_TODOLIST,
					android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = settingsActivity.edit();
		String savedItems = getSavedItems();
		prefEditor.putString(this.scavHuntTitle+SETTING_TODOLIST, savedItems);
		prefEditor.commit();
	}
	
	public ArrayList<ScavHuntSubItem> arrayValue(){
		return todoItems;
	}
	
	private void LoadSelections() {
		// TODO Auto-generated method stub
		
		//Log.d("debug", "itemText: "+itemText);

		
		SharedPreferences settingsActivity =
			this.context.getSharedPreferences(
					this.scavHuntTitle+SETTING_TODOLIST,
			android.content.Context.MODE_PRIVATE);
		
		if(settingsActivity.contains(this.scavHuntTitle+SETTING_TODOLIST)){
			String savedItems = settingsActivity.getString(this.scavHuntTitle+SETTING_TODOLIST, "");
			this.selectedItems.addAll(Arrays.asList(savedItems.split(",")));
			}	
		}
	}


	
/*
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.scavhunt_row, null);
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
		StringBuffer s = new StringBuffer();
		for(String test : selectedItems){
			s.append(test + " , ");
		}
		
		Log.d(tag, "selected items" + s.toString());
		if (cBox.isChecked()) {
			if (!this.selectedItems.contains(itemText))
				this.selectedItems.add(itemText);
				Log.d(tag, "added " + itemText);

		} else {
			if (this.selectedItems.contains(itemText))
				this.selectedItems.remove(itemText);
				Log.d(tag, "removed " + itemText);
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
			Log.d(tag, "loadsettings triggered, saved items: " + savedItems);			
			this.selectedItems.addAll(Arrays.asList(savedItems.split(",")));

		}
	}
*/
