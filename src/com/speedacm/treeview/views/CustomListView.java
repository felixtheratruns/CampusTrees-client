package com.speedacm.treeview.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.xmlpull.v1.XmlPullParserException;

import com.speedacm.treeview.R;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.content.*;
import android.content.res.XmlResourceParser;

public class CustomListView extends ListActivity {

	private ListView mainListView = null;
	CustomToDoListAdapter customTODOAdapter = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple);

		Button btnClear = (Button) findViewById(R.id.btnClear);
		btnClear.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Toast.makeText(getApplicationContext(),
						" You clicked Clear button", Toast.LENGTH_SHORT).show();
				ClearSelections();

			}
		});

		// Prepare an ArrayList of todo items
		ArrayList<String> listTODO = PrepareListFromXml();

		this.mainListView = getListView();

		mainListView.setCacheColorHint(0);

		// Bind the data with the list
		this.customTODOAdapter = new CustomToDoListAdapter(CustomListView.this,
				R.layout.single_item, listTODO);
		mainListView.setAdapter(this.customTODOAdapter);

		mainListView.setItemsCanFocus(false);
		mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}

	private void ClearSelections() {

		for (int i = 0; i < this.customTODOAdapter.getCount(); i++) {
			LinearLayout v = (LinearLayout) this.mainListView.getChildAt(i);
			CheckBox chk = (CheckBox) v.getChildAt(0);
			chk.setChecked(false);

		}

		this.customTODOAdapter.ClearSelections();

	}

	private ArrayList<String> PrepareListFromXml() {
		ArrayList<String> todoItems = new ArrayList<String>();
		XmlResourceParser todolistXml = getResources().getXml(R.xml.todolist);

		int eventType = -1;
		while (eventType != XmlResourceParser.END_DOCUMENT) {
			if (eventType == XmlResourceParser.START_TAG) {

				String strNode = todolistXml.getName();
				if (strNode.equals("item")) {
					todoItems.add(todolistXml.getAttributeValue(null, "title"));
				}
			}

			try {
				eventType = todolistXml.next();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return todoItems;
	}
}