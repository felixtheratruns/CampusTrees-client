/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
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
