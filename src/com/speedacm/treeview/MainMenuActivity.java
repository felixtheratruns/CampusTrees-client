package com.speedacm.treeview;

import java.util.ArrayList;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.speedacm.treeview.menu.ActivityStarter;
import com.speedacm.treeview.menu.DynamicMapStarter;
import com.speedacm.treeview.menu.MenuActionListener;
import com.speedacm.treeview.menu.MenuItem;
import com.speedacm.treeview.views.AboutActivity;
import com.speedacm.treeview.views.CredsActivity;
import com.speedacm.treeview.views.DynamicMapActivity;
import com.speedacm.treeview.views.NewsActivity;
import com.speedacm.treeview.views.PlantFactsActivity;
import com.speedacm.treeview.views.ScavHuntActivity;
import com.speedacm.treeview.views.WildLifeFactsActivity;

public class MainMenuActivity extends Activity implements OnItemClickListener
{	
	
	private ArrayList<MenuItem> menuEntries = new ArrayList<MenuItem>();
	
	// in order for "this" to be accessible to the inner class, define
	// it here so mScanListener can get a proper pointer to the outer activity
	private Activity mThisPtr = this;
	private Pattern mURLPattern = Pattern.compile("^http://treetest/tree/\\d+$");
	private MenuActionListener mScanListener = new MenuActionListener() {
		
		@Override
		public void onMenuAction(MenuItem item) {
			IntentIntegrator ii = new IntentIntegrator(mThisPtr);
			ii.initiateScan();
		}
	};
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
			String url = scanResult.getContents();
			if(!mURLPattern.matcher(url).matches())
			{
				Toast.makeText(this, "Invalid URL: ".concat(url), Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(this, url, Toast.LENGTH_LONG).show();
			}
			
		}
	}
	
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

		// Create a customized ArrayAdapter
		MenuItemArrayAdapter adapter = new MenuItemArrayAdapter(
				getApplicationContext(), R.layout.menuitem_listitem, menuEntries);
		
		ListView lv = (ListView) this.findViewById(R.id.mainMenuList);
		lv.setAdapter(adapter);
    }
    
    @Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		// since each menu entry already has an event handler bound to it,
		// there is no need to do anything fancy, just call its handler
		MenuItem entry = (MenuItem)parent.getItemAtPosition(position);
		entry.action();
	}

	private void addMenuItems()
	{
		menuEntries.add(new MenuItem(getString(R.string.mmenu_news), R.drawable.news,
				new ActivityStarter(this, NewsActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_treemap), R.drawable.browse_tree_map,
				new DynamicMapStarter(this, DynamicMapActivity.TREE_MODE)));
		//menuEntries.add(new MenuItem(getString(R.string.mmenu_sustmap), R.drawable.browse_sustainability_map,
		//		new DynamicMapStarter(this, DynamicMapActivity.SUSTAIN_MODE)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_scanbarcode), R.drawable.about, mScanListener));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_plantfacts), R.drawable.plant_facts,
				new ActivityStarter(this, PlantFactsActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_wildfacts), R.drawable.wildlife_facts,
				new ActivityStarter(this, WildLifeFactsActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_scavhunt), R.drawable.scavenger_hunt,
				new ActivityStarter(this, ScavHuntActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_about), R.drawable.about,
				new ActivityStarter(this, AboutActivity.class)));
		menuEntries.add(new MenuItem(getString(R.string.mmenu_creds), R.drawable.about,
				new ActivityStarter(this, CredsActivity.class)));
	}

}
