package com.speedacm.treeview.views;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.ScavHunt;
import com.speedacm.treeview.models.ScavHuntSubItem;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.content.*;

public class ScavHuntListActivity extends ListActivity {

	private String[] lv_arr = {};
	private ArrayList<ScavHunt> menuEntries = new ArrayList<ScavHunt>();
	private ScavHuntListAdapter adapter;
	
	
	private ListView mainListView = null;
	final String SETTING_TODOLIST = "todolist";

	private ArrayList<String> selectedItems = new ArrayList<String>();
	
	private String scavHuntTitle = "";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scavhuntbox);
		
		Button btnClear = (Button) findViewById(R.id.btnClear);
		btnClear.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				Toast.makeText(getApplicationContext(), " You clicked Clear button", Toast.LENGTH_SHORT).show(); 
				ClearSelections();
			}
		});
		
		
		
		String position = getIntent().getStringExtra("position");

		System.out.println("the position is: "+ position);
		ScavHunt s_hunt = getIntent().getParcelableExtra("s_hunt");
		//System.out.println("body string: "+ s_hunt.getBody().toString());
		
		scavHuntTitle = s_hunt.getTitle();
		
		
		
		
		
		// Prepare an ArrayList of todo items
		ArrayList<ScavHuntSubItem> sub_menu_items = s_hunt.getSubItems();
		//PrepareListFromSelected(s_hunt);
		
		//ArrayList<String> listTODO = PrepareListFromXML();
		this.mainListView = getListView();
		mainListView.setCacheColorHint(0);

		// Bind the data with the list
		//lv_arr = (String[]) listTODO.toArray(new String[0]);
		
		
		
		
		this.adapter = new ScavHuntListAdapter(getApplicationContext(), R.layout.scavhunt_row, sub_menu_items);
		
		mainListView.setAdapter(adapter);
		mainListView.setItemsCanFocus(false);
		mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		//LoadSelections();
		
		//Button btnSave = (Button) findViewById(R.id.btnSave);
		/*btnSave.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {

				Toast.makeText(getApplicationContext(),
						" You clicked Save button", Toast.LENGTH_SHORT).show();
				SaveSelections();
			}
		});

		Button btnClear = (Button) findViewById(R.id.btnClear);
		btnClear.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						" You clicked Clear button", Toast.LENGTH_SHORT).show();
				ClearSelections();
			}
		});
		*/
	}


	private void ClearSelections() {
		// user has clicked clear button so uncheck all the items
		for(int i = 0; i<this.adapter.getCount(); i++){
			LinearLayout v = (LinearLayout) this.mainListView.getChildAt(i);
			CheckBox chk = (CheckBox) v.getChildAt(0);
			chk.setChecked(false);
		}
		this.adapter.ClearSelections();
		
		/*
		int count = this.mainListView.getAdapter().getCount();
		for (int i = 0; i < count; i++) {
			this.mainListView.setItemChecked(i, false);
		}
		// also clear the saved selections
		SaveSelections();
		*/
	}
/*
	private void LoadSelections() {
		// if the selections were previously saved load them
		SharedPreferences settingsActivity = getPreferences(MODE_PRIVATE);
		if (settingsActivity.contains(scavHuntTitle+SETTING_TODOLIST)) {
			String savedItems = settingsActivity
					.getString(scavHuntTitle+SETTING_TODOLIST, "");

			this.selectedItems.addAll(Arrays.asList(savedItems.split(",")));
			int count = this.mainListView.getAdapter().getCount();

			for (int i = 0; i < count; i++) {
				String currentItem = (String) this.mainListView.getAdapter()
						.getItem(i);
				if (this.selectedItems.contains(currentItem)) {
					this.mainListView.setItemChecked(i, true);
				}
			}
		}
	}
	*/
/*
	private void SaveSelections() {
		// save the selections in the shared preference in private mode for the user
		SharedPreferences settingsActivity = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = settingsActivity.edit();
		String savedItems = getSavedItems();
		prefEditor.putString(scavHuntTitle+SETTING_TODOLIST, savedItems);
		prefEditor.commit();
	}

	private String getSavedItems() {
		String savedItems = "";
		int count = this.mainListView.getAdapter().getCount();
		for (int i = 0; i < count; i++) {
			if (this.mainListView.isItemChecked(i)) {
				if (savedItems.length() > 0) {
					savedItems += "," + this.mainListView.getItemAtPosition(i);
				} else {
					savedItems += this.mainListView.getItemAtPosition(i);
				}
			}

		}
		return savedItems;
	}
	
	

	@Override
	protected void onPause() {
		// always handle the onPause to make sure selections are saved if user clicks back button
		SaveSelections();
		super.onPause();
	}
	
	*/
/*	
	private ArrayList<ScavHuntSubItem> PrepareListFromSelected(ScavHunt s_hunt) {
		ArrayList<String> todoItems = new ArrayList<String>();
		ArrayList<ScavHuntSubItem> items = s_hunt.getSubItems();

		for(Iterator<ScavHuntSubItem> t =items.iterator();  t.hasNext();){
			ScavHuntSubItem test = t.next();
			todoItems.add(test.getTitle() + ": "+ test.getBody());
		}
		
		return todoItems;
	}
*/
}