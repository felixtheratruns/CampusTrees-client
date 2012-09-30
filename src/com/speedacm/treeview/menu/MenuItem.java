package com.speedacm.treeview.menu;


public class MenuItem {	
	private String mName;
	private int mIcon;
	private MenuActionListener mListener;
		
	public MenuItem(String name, int drawableId, MenuActionListener listener)
	{
		mName = name;
		mIcon = drawableId;
		mListener = listener;
	}
	
	public void action()
	{
		if(mListener != null)
			mListener.onMenuAction(this);
	}
	
	public String toString()
	{
		return mName;
	}

	// getters
	
	public int getDrawable()
	{
		return mIcon;
	}
	
	public String getName()
	{
		return mName;
	}
}
