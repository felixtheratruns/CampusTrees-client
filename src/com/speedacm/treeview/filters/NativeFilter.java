package com.speedacm.treeview.filters;

import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Species.NativeType;

public class NativeFilter extends Filter
{

	private NativeType mNative;
	
	public NativeFilter(NativeType type)
	{
		type = mNative;
	}
	
	@Override
	public boolean withinFilter(Tree tree)
	{
		switch(tree.getSpecies().getNativity())
		{
		case None:
			return (mNative == NativeType.None);
		case KY:
			return (mNative == NativeType.KY);
		case US:
			return (mNative == NativeType.KY || mNative == NativeType.US);
		default:
			return false;
		}
	}
}
