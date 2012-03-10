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
	private boolean mFlowering;
	private boolean mFruiting;
	private NativeType mNativity;
	
	public Species(String jsonText)
	{
		
	}
	
	public Species(int id, String name, boolean flowers, boolean fruits, NativeType nat)
	{
		mID = id;
		mName = name;
		mFlowering = flowers;
		mFruiting = fruits;
		mNativity = nat;
	}
	
	public int getID() { return mID; }
	public String getName() { return mName; }
	public boolean isFlowering() { return mFlowering; }
	public boolean isFruiting() { return mFruiting; }
	public NativeType getNativity() { return mNativity; }
}
