package com.speedacm.treeview.views;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.speedacm.treeview.R;
import com.speedacm.treeview.data.DSResultListener;
import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.menu.ActivityStarter;
import com.speedacm.treeview.menu.MenuItem;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.ScavHunt;
import com.speedacm.treeview.submenus.ScavHuntActivityStarter;
import com.speedacm.treeview.submenus.ScavHuntMenuItem;
import com.speedacm.treeview.views.ScavHuntMainActivity;


public class ScavHuntMainActivity extends Activity implements DSResultListener<ScavHunt[]>, OnItemClickListener
{	
	
	private int mCurrentFetchID = DataStore.NO_REQUEST;
	private ArrayList<ScavHuntMenuItem> menuEntries = new ArrayList<ScavHuntMenuItem>();
	public static ArrayList<ScavHuntMenuItem> mEnt = new ArrayList<ScavHuntMenuItem>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scavhunt_mainmenu);
		mCurrentFetchID = DataStore.getInstance().beginGetAllScavHunt(this);
    }
    
	@Override
	public void onDSResultReceived(int requestID, ScavHunt[] payload) {
		
		if(payload != null){
			addMenuItems(payload);
		        
		    // bind to our menu items
		    ListView mmenu = (ListView)findViewById(R.id.mainMenuList);
		    mmenu.setOnItemClickListener(this);
	
			// Create a customized ArrayAdapter
			ScavHuntMainArrayAdapter adapter = new ScavHuntMainArrayAdapter(
						getApplicationContext(), R.layout.scavhunt_menuitem_listitem, menuEntries);
				
			ListView lv = (ListView) this.findViewById(R.id.mainMenuList);
			lv.setAdapter(adapter);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		// since each menu entry already has an event handler bound to it,
		// there is no need to do anything fancy, just call its handler
		ScavHuntMenuItem entry = (ScavHuntMenuItem)parent.getItemAtPosition(position);
		entry.action();
	}

	private void addMenuItems(ScavHunt[] items)
	{
		
		for(int i=0; i<items.length; i++){
			menuEntries.add(new ScavHuntMenuItem(items[i].getTitle(), items[i], new ScavHuntActivityStarter(this, ScavHuntListActivity.class)));
		}
	}
}
