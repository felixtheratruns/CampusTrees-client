package com.speedacm.treeview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.speedacm.treeview.menu.MenuEntry;


public class MenuItemArrayAdapter extends ArrayAdapter<MenuEntry> {
	private static final String tag = "MenuItemArrayAdapter";
	private Context context;
	private ImageView menuitemIcon;
	private TextView menuitemName;
	private List<MenuEntry> menuitems = new ArrayList<MenuEntry>();

	public MenuItemArrayAdapter(Context context, int textViewResourceId,
			List<MenuEntry> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.menuitems = objects;
	}

	public int getCount() {
		return this.menuitems.size();
	}

	public MenuEntry getItem(int index) {
		return this.menuitems.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.menuitem_listitem, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		MenuEntry menuitem = getItem(position);
		
		// Get reference to ImageView 
		menuitemIcon = (ImageView) row.findViewById(R.id.menuitem_icon);
		
		// Get reference to TextView - country_name
		menuitemName = (TextView) row.findViewById(R.id.menuitem_name);

		//Set menuitem name
		menuitemName.setText(menuitem.toString());
		
		// Set country icon using File path
		String imgFilePath =  menuitem.getResourceId();
		
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(this.context.getResources().getAssets()
					.open(imgFilePath));
			menuitemIcon.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return row;
	}
}
