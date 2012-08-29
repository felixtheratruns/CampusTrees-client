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
	
    /** Ca1lled when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scavhunt_mainmenu);
		mCurrentFetchID = DataStore.getInstance().beginGetAllScavHunt(this);
    }
    
	@Override
	public void onDSResultReceived(int requestID, ScavHunt[] payload) {
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
		
		
		
		//mEnt = menuEntries;
		//menuEntries.add(new ScavHuntMenuItem("test",new ScavHuntActivityStarter(this, ScavHuntListActivity.class)));
		/*
		menuEntries.add(new MenuItem(getString(R.string.mmenu_news), R.drawable.news,
				new ActivityStarter(this, NewsActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_treemap), R.drawable.browse_tree_map,
				new DynamicMapStarter(this, DynamicMapActivity.TREE_MODE)));
		//menuEntries.add(new MenuItem(getString(R.string.mmenu_sustmap), R.drawable.browse_sustainability_map,
		//		new DynamicMapStarter(this, DynamicMapActivity.SUSTAIN_MODE)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_plantfacts), R.drawable.plant_facts,
				new ActivityStarter(this, PlantFactsActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_wildfacts), R.drawable.wildlife_facts,
				new ActivityStarter(this, WildLifeFactsActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_scavhunt), R.drawable.scavenger_hunt,
				new ActivityStarter(this, ScavHuntMainActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_about), R.drawable.about,
				new ActivityStarter(this, AboutActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_creds), R.drawable.about,
				new ActivityStarter(this, CredsActivity.class)));
				*/
	}



}
/*
public class ScavHuntMainActivity extends Activity implements DSResultListener<ScavHunt[]>{

	private static final String tag = "ScavHuntActivity";
	private int mCurrentFetchID = DataStore.NO_REQUEST;
	private ArrayList<ScavHunt> menuEntries = new ArrayList<ScavHunt>();

	private String[] lv_arr = {};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scavhuntmainbox);
		mCurrentFetchID = DataStore.getInstance().beginGetAllScavHunt(this);
    }
   
    /*
        Button btnSimple = (Button) findViewById(R.id.btnSimple);
        btnSimple.setOnClickListener(new OnClickListener() {	
        	
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						" You clicked Custom ListView button", Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(v.getContext(), ScavHuntListActivity.class);
       		  	startActivityForResult(intent, 0);
			}
			
		});
        
      */  
/*
	@Override
	public void onDSResultReceived(int requestID, ScavHunt[] payload) {
		if(payload != null){
			for(ScavHunt p : payload){
				menuEntries.add(p);
			}
		}
		
		Context test = getApplicationContext();
		
		ArrayList<String> moose = new ArrayList<String>();
		
		for(ScavHunt p : menuEntries){
			Log.d(tag,p.toString());
			moose.add(p.toString());
		}
		
		ArrayList<Button> buttons = new ArrayList<Button>();
		
		/*
		for(final String s : moose){
			buttons.add(new Button(getApplicationContext()));
			buttons.get(buttons.size()-1).setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					Toast.makeText(getApplicationContext(),
							" You clicked"+s, Toast.LENGTH_SHORT).show();

				}
			});.
			//buttons.get(buttons.size()-1).setText(s);
		}
		*/
		
		
	/*	
		
		Log.d(tag, "dislay scavenger hunts ");
		
		
		ScavHuntMainArrayAdapter adapter = new ScavHuntMainArrayAdapter(
					getApplicationContext(), R.layout.scavhuntmain_row, moose);
		    
		ListView lv = (ListView) this.findViewById(R.id.scavHuntList);
	
		

		
		
		// Set the ListView adapter
		lv.setAdapter(adapter);
		
		
	}

}
*/