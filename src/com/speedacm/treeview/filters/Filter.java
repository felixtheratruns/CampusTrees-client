package com.speedacm.treeview.filters;

import com.speedacm.treeview.models.Tree;

public abstract class Filter
{
	public abstract boolean withinFilter(Tree tree);
}
