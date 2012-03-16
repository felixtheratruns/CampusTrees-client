package com.speedacm.treeview.menu;

public class MenuEntry {
	
	private String mName;
//	private int mIcon;
	private String mResourceId;
	private MenuActionListener mListener;
/*	
	public MenuEntry(String name, MenuActionListener listener)
	{
		this(name, 0, listener);
	}
*/	

	
	public MenuEntry(String name, MenuActionListener listener, String assets_dir, String ext)
	{
		mName = name;
		mListener = listener;
		mResourceId = assets_dir + name + ext;
	}
	
	
/*	
	public MenuEntry(String name, int drawableId, MenuActionListener listener)
	{
		mName = name;
		mIcon = drawableId;
		mListener = listener;
		this.name = name;
		this.resourceId = name;
	}
*/	
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
	public String getResourceId()
	{
		return mResourceId;
	}
/*	
	public int getDrawable()
	{
		return mIcon;
	}
	*/
	public String getName()
	{
		return mName;
	}
}
