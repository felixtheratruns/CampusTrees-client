package com.speedacm.treeview.filters;

import com.speedacm.treeview.models.Tree;

public class EdibleFilter extends Filter {

	@Override
	public boolean withinFilter(Tree tree) {
		return tree.getSpecies().isEdible();
	}

}
