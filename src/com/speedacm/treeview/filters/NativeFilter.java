/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.filters;

import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Species.NativeType;

public class NativeFilter extends Filter
{

	private NativeType mNative;
	
	public NativeFilter(NativeType type)
	{
		mNative = type;
	}
	
	@Override
	public boolean withinFilter(Tree tree)
	{
		switch(tree.getSpecies().getNativity())
		{
		case None:
			return (mNative == NativeType.None);
		case KY:
			return (mNative == NativeType.KY || mNative == NativeType.US);
		case US:
			return (mNative == NativeType.US);
		default:
			return false;
		}
	}
}
