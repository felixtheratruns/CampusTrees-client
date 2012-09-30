package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.List;

import com.speedacm.treeview.R;
import com.speedacm.treeview.menu.MenuItem;
import com.speedacm.treeview.submenus.ScavHuntMenuItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;




public class ScavHuntMainArrayAdapter extends ArrayAdapter<ScavHuntMenuItem>{
	private static final String tag = "ScavHuntMainArrayAdapter";
	private Context context;
	private ImageView menuitemIcon;
	private TextView menuitemName;
	private List<ScavHuntMenuItem> menuitems = new ArrayList<ScavHuntMenuItem>();

	public ScavHuntMainArrayAdapter(Context context, int textViewResourceId,
			List<ScavHuntMenuItem> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.menuitems = objects;
	}

	public int getCount() {
		return this.menuitems.size();
	}

	public ScavHuntMenuItem getItem(int index) {
		return this.menuitems.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.menuitem_listitem, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		ScavHuntMenuItem menuitem = getItem(position);
		
		// get references to subviews
		//menuitemIcon = (ImageView) row.findViewById(R.id.menuitem_icon);
		menuitemName = (TextView) row.findViewById(R.id.menuitem_name);
		
		//Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), menuitem.getDrawable());
		
		// set up final values
		menuitemName.setText(menuitem.toString());
		//menuitemIcon.setImageBitmap(bitmap);

		return row;
	}
}
