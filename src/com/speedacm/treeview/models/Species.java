/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.models;

public class Species
{
	
	public enum NativeType
	{
		KY,
		US,
		None
	}
	
	private int mID;
	private String mName;
	private String mFruitType;
	private boolean mFruitEdible;
	private NativeType mNativity;
	private int mCount;
	
	public Species(int id, String name, String fruitType, boolean edible, NativeType nat, int count)
	{
		mID = id;
		mName = name;
		mNativity = nat;
		mFruitType = fruitType;
		mFruitEdible = edible;
		mCount = count;
	}
	
	public int getID() { return mID; }
	public String getName() { return mName; }
	public String getFruitType() { return mFruitType; }
	public boolean isEdible() { return mFruitEdible; }
	public NativeType getNativity() { return mNativity; }
	public int getCount() { return mCount; }
	
	@Override
	public String toString() {
		return mName;
	}
}
