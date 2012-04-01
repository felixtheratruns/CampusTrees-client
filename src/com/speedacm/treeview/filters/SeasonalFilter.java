package com.speedacm.treeview.filters;

import com.speedacm.treeview.models.Tree;

public class SeasonalFilter extends Filter
{
	public enum SeasonalType {
		Flowering,
		Fruiting,
	};
	
	private SeasonalType mType;
	
	public SeasonalFilter(SeasonalType type)
	{
		mType = type;
	}

	@Override
	public boolean withinFilter(Tree tree)
	{
		// TODO implement seasonal trees, etc
		return false;
	}
}
