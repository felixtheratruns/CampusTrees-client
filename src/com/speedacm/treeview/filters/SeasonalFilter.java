/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
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
