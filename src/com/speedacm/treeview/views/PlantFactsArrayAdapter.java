package com.speedacm.treeview.views;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.speedacm.treeview.R;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.Tree;



public class PlantFactsArrayAdapter extends ArrayAdapter<PlantFact>{
	private static final String tag = "PlantFactArrayAdapter";
	private Context context;
	private TextView plantFactTitle;
	private TextView plantFactBody;
	DecimalFormat f = new DecimalFormat("###,###.###");
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
		String etitle = ":";
		String sdbh =  "Diameter at breast height: ";
		String edbh = " inches";
		String sheight = "Height: ";
		String eheight = " feet";
		String svol = "Volume: ";
		String evol = " board feet";
		String evolext = " or ";
		String evol2 = " cubic feet";
		String sGreenwt = "Total weight: ";
		String eGreenwt = " pounds";
		String sDrywt = "Weight without moisture: ";
		String eDrywt = " pounds";
		String sCarbonwt = "Weight of Carbon: ";
		String eCarbonwt = " pounds";
		String sAge = "Age : ";
		String eAge = " years";
		String sCo2pyear = "CO2 absorbed in a year: ";
		String eCo2pyear = " pounds";
		String sCrownarea = "Crown area: ";
		String eCrownarea = " square feet";
		
		Tree t = plant_fact.getTree();
		
		s.append(sdbh);
		s.append(f.format(t.getDBH()));
		s.append(edbh);
		s.append("<br>");		
		s.append("<br>");
		s.append(sheight);
		s.append(f.format(t.getHeight()));
		s.append(eheight);
		s.append("<br>");		
		s.append("<br>");
		s.append(svol);
		s.append(f.format(t.getVolume()));		
		s.append(evol);
		// Commenting this out until we can get this merged with the tree fetching code
		//s.append(evolext);
		//s.append(f.format(t.getCubFtVol()));
		//s.append(evol2);
		s.append("<br>");
		s.append("<br>");
		s.append(sGreenwt);
		s.append(f.format(t.getGreenWeight()));
		s.append(eGreenwt);
		s.append("<br>");
		s.append("<br>");
		s.append(sDrywt);
		s.append(f.format(t.getDryWeight()));
		s.append(eDrywt);
		s.append("<br>");
		s.append("<br>");
		s.append(sCarbonwt);
		s.append(f.format(t.getCarbonWeight()));
		s.append(eCarbonwt);
		s.append("<br>");
		s.append("<br>");
		s.append(sAge);
		s.append(f.format(t.getAge()));
		s.append(eAge);
		s.append("<br>");
		s.append("<br>");
		s.append(sCo2pyear);
		s.append(f.format(t.getCO2SeqPerYr()));
		s.append(eCo2pyear);
		s.append("<br>");
		s.append("<br>");
		s.append(sCrownarea);
		s.append(f.format(t.getCrownArea()));
		s.append(eCrownarea);
		
		plantFactTitle.setText(Html.fromHtml(stitle + plant_fact.getTitle() + etitle));
		plantFactBody.setText(Html.fromHtml(s.toString()));
		return row;
	}
}
