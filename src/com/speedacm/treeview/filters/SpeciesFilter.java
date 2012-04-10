package com.speedacm.treeview.filters;

import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;

public class SpeciesFilter extends Filter
{
	private Species mSpecies;
	
	public SpeciesFilter(Species s)
	{
		mSpecies = s;
	}
	
	@Override
	public boolean withinFilter(Tree tree)
	{
		return (tree.getSpeciesID() == mSpecies.getID());
	} 
}
