package com.speedacm.treeview.filters;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.speedacm.treeview.data.DataStore;
import com.speedacm.treeview.models.Tree;

public class SeasonalFilter extends Filter
{
	public enum SeasonalType {
		Flowering,
		Fruiting,
	};
	
	private List<Integer> mSpecies;
	
	public SeasonalFilter(SeasonalType type)
	{	
		// the database uses 1-based months, whereas Calendar
		// uses 0-based months. Quick fix without changing DB
		int month = GregorianCalendar.getInstance().get(Calendar.MONTH) + 1;
		
		switch(type)
		{
		case Fruiting:
			mSpecies = DataStore.getInstance().getFruitingSpecies(month);
			break;
			
		case Flowering:
			mSpecies = DataStore.getInstance().getFloweringSpecies(month);
			break;
		}
	}
	
	public SeasonalFilter(List<Integer> species)
	{
		mSpecies = species;
	}

	@Override
	public boolean withinFilter(Tree tree)
	{
		return mSpecies.contains(tree.getSpeciesID());
	}
}
