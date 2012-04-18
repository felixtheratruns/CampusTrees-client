package com.speedacm.treeview.views;

import java.util.ArrayList;
import java.util.List;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.PlantFact;
import android.text.Html;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class PlantFactsArrayAdapter extends ArrayAdapter<PlantFact>{
	private static final String tag = "PlantFactArrayAdapter";
	private Context context;
	private TextView plantFactTitle;
	private TextView plantFactBody;
	private List<PlantFact> plantFactsArr = new ArrayList<PlantFact>();

	public PlantFactsArrayAdapter(Context context, int textViewResourceId,
			List<PlantFact> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.plantFactsArr = objects;
	}

	public int getCount() {
		return this.plantFactsArr.size();
	}

	public PlantFact getItem(int index) {
		return this.plantFactsArr.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.plantfacts_row, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		PlantFact plant_fact = getItem(position);
		
		// get references to subviews
		plantFactTitle= (TextView) row.findViewById(R.id.title);
		plantFactBody = (TextView) row.findViewById(R.id.body);
		//Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), PlantFact.getDrawable());
		
		// set up final values
		StringBuffer s = new StringBuffer();
		
/*		s.append(plant_fact.getId());
		s.append("\n");
		s.append(plant_fact.getSid());
		s.append("\n");		
		s.append(plant_fact.getLatitude());
		s.append("\n");
		s.append(plant_fact.getLongitude());
		s.append("\n");
*/		String stitle = "";
		String etitle = " tree on campus has these facts:";
		String sdbh =  "Diameter at breast height: ";
		String edbh = " inches";
		String sheight = "Height: ";
		String eheight = " feet";
		String svol = "Volume: ";
		String evol = " board feet";
		String evolext = " or ";
		String evol2 = " cubic feet";
		String sGreenwt = "Weight with leaves: ";
		String eGreenwt = " pounds";
		String sDrywt = "Weight without leaves: ";
		String eDrywt = " pounds";
		String sCarbonwt = "Weight of Carbon: ";
		String eCarbonwt = " pounds";
		String sAge = "Age : ";
		String eAge = " years";
		String sCo2pyear = "Co2 asborbed in a year: ";
		String eCo2pyear = " pounds";
		String sCrownarea = "Crown area: ";
		String eCrownarea = " square feet";
		
		s.append(sdbh);
		s.append(plant_fact.getDbh());
		s.append(edbh);
		s.append("<br>\n");		
		s.append("<br>\n");
		s.append(sheight);
		s.append(plant_fact.getHeight());
		s.append(eheight);
		s.append("<br>\n");		
		s.append("<br>\n");
		s.append(svol);
		s.append(plant_fact.getVol());		
		s.append(evol);
		s.append(evolext);
		s.append(plant_fact.getCubFtVol());
		s.append(evol2);
		s.append("<br>\n");
		s.append("<br>\n");
		s.append(sGreenwt);
		s.append(plant_fact.getGreenwt());
		s.append(eGreenwt);
		s.append("<br>\n");
		s.append("<br>\n");
		s.append(sDrywt);
		s.append(plant_fact.getDrywt());
		s.append(eDrywt);
		s.append("<br>\n");
		s.append("<br>\n");
		s.append(sCarbonwt);
		s.append(plant_fact.getCarbonwt());
		s.append(eCarbonwt);
		s.append("<br>\n");
		s.append("<br>\n");
		s.append(sAge);
		s.append(plant_fact.getAge());
		s.append(eAge);
		s.append("<br>\n");
		s.append("<br>\n");
		s.append(sCo2pyear);
		s.append(plant_fact.getCo2pyear());
		s.append(eCo2pyear);
		s.append("<br>\n");
		s.append("<br>\n");
		s.append(sCrownarea);
		s.append(plant_fact.getCrownarea());
		s.append(eCrownarea);
		
		plantFactTitle.setText(Html.fromHtml(stitle + plant_fact.getTitle() + etitle));
		plantFactBody.setText(Html.fromHtml(s.toString()));
		return row;
	}
}
