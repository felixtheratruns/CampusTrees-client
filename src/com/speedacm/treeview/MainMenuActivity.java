package com.speedacm.treeview;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.speedacm.treeview.menu.ActivityStarter;
import com.speedacm.treeview.menu.DynamicMapStarter;
import com.speedacm.treeview.menu.MenuEntry;
import com.speedacm.treeview.views.AboutActivity;
import com.speedacm.treeview.views.CredsActivity;
import com.speedacm.treeview.views.DynamicMapActivity;
import com.speedacm.treeview.views.NewsActivity;
import com.speedacm.treeview.views.PlantFactsActivity;
import com.speedacm.treeview.views.ScavHuntActivity;
import com.speedacm.treeview.views.WildLifeFactsActivity;

public class MainMenuActivity extends Activity implements OnItemClickListener
{	
	
	private ArrayList<MenuEntry> menuEntries = new ArrayList<MenuEntry>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        
 		//I think this function adds menuItems... not completely sure though
        addMenuItems();
        
        // bind to our menu items
        ListView mmenu = (ListView)findViewById(R.id.mainMenuList);
        mmenu.setOnItemClickListener(this);

		// Create a customized ArrayAdapter
		MenuItemArrayAdapter adapter = new MenuItemArrayAdapter(
				getApplicationContext(), R.layout.menuitem_listitem, menuEntries);
		
		// Get reference to ListView holder
		ListView lv = (ListView) this.findViewById(R.id.mainMenuList);
		
		// Set the ListView adapter
		lv.setAdapter(adapter);
    }
    
    @Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		// since each menu entry already has an event handler bound to it,
		// there is no need to do anything fancy, just call its handler
		MenuEntry entry = (MenuEntry)parent.getItemAtPosition(position);
		entry.action();
	}

	private void addMenuItems()
	{
		String ext = getString(R.string.mmenu_file_ext);
		String assets_dir = getString(R.string.mmenu_assets_dir);
		
		menuEntries.add(	
				new MenuEntry(getString(R.string.mmenu_news), new ActivityStarter(this, NewsActivity.class), assets_dir, ext)	
			);
		menuEntries.add(
				new MenuEntry(getString(R.string.mmenu_treemap), new DynamicMapStarter(this, DynamicMapActivity.TREE_MODE), assets_dir, ext)
			);
		menuEntries.add(
				new MenuEntry(getString(R.string.mmenu_sustmap), new DynamicMapStarter(this, DynamicMapActivity.SUSTAIN_MODE), assets_dir, ext)
			);
		menuEntries.add(
				new MenuEntry(getString(R.string.mmenu_plantfacts), new ActivityStarter(this, PlantFactsActivity.class), assets_dir, ext)
			);
		menuEntries.add(
				new MenuEntry(getString(R.string.mmenu_wildfacts), 	new ActivityStarter(this, WildLifeFactsActivity.class), assets_dir, ext)
			);
		menuEntries.add(
				new MenuEntry(getString(R.string.mmenu_scavhunt), new ActivityStarter(this, ScavHuntActivity.class),assets_dir, ext)	
			);
		menuEntries.add(
				new MenuEntry(getString(R.string.mmenu_about), new ActivityStarter(this, AboutActivity.class), assets_dir, ext)	
			);
		menuEntries.add(
				new MenuEntry(getString(R.string.mmenu_creds), new ActivityStarter(this, CredsActivity.class), assets_dir, ext)	
			);
	}

}