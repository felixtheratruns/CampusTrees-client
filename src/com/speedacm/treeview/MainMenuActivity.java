package com.speedacm.treeview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.speedacm.treeview.menu.ActivityStarter;
import com.speedacm.treeview.menu.DynamicMapStarter;
import com.speedacm.treeview.menu.MenuActionListener;
import com.speedacm.treeview.menu.MenuEntry;
import com.speedacm.treeview.views.AboutActivity;
import com.speedacm.treeview.views.CredsActivity;
import com.speedacm.treeview.views.NewsActivity;
import com.speedacm.treeview.views.PlantFactsActivity;
import com.speedacm.treeview.views.ScavHuntActivity;
import com.speedacm.treeview.views.DynamicMapActivity;
import com.speedacm.treeview.views.WildLifeFactsActivity;

public class MainMenuActivity extends Activity implements OnItemClickListener
{	
	
	private ArrayList<MenuEntry> menuEntries = new ArrayList<MenuEntry>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        
        addMenuItems();
        
        // bind to our menu items
        ListView mmenu = (ListView)findViewById(R.id.mainMenuList);
        mmenu.setOnItemClickListener(this);
        mmenu.setAdapter(new ArrayAdapter<MenuEntry>(this, android.R.layout.simple_list_item_1, menuEntries));
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
		// for now, just define a default event handler for any unimplemented
		// events, that will just display "TODO: Whatever" on the phone
		
		final Context baseCtx = this; // need this to go inside onMenuAction
		MenuActionListener todoHandler = new MenuActionListener() {
			public void onMenuAction(MenuEntry item) {
				Toast.makeText(baseCtx, "TODO: " + item.getName(), Toast.LENGTH_SHORT).show();
			}
		};
		
		// later on, put specific event handlers for each menu entry
		
		menuEntries.add(new MenuEntry(getString(R.string.mmenu_news),  new ActivityStarter(this, NewsActivity.class)));
		menuEntries.add(new MenuEntry(getString(R.string.mmenu_treemap), new DynamicMapStarter(this, DynamicMapActivity.TREE_MODE)));
		menuEntries.add(new MenuEntry(getString(R.string.mmenu_sustmap), new DynamicMapStarter(this, DynamicMapActivity.SUSTAIN_MODE)));
		menuEntries.add(new MenuEntry(getString(R.string.mmenu_plantfacts), new ActivityStarter(this, PlantFactsActivity.class)));
		menuEntries.add(new MenuEntry(getString(R.string.mmenu_wildfacts), new ActivityStarter(this, WildLifeFactsActivity.class)));
		menuEntries.add(new MenuEntry(getString(R.string.mmenu_scavhunt), new ActivityStarter(this, ScavHuntActivity.class)));
		menuEntries.add(new MenuEntry(getString(R.string.mmenu_about), new ActivityStarter(this, AboutActivity.class)));
		menuEntries.add(new MenuEntry(getString(R.string.mmenu_creds), new ActivityStarter(this, CredsActivity.class)));
	}
}