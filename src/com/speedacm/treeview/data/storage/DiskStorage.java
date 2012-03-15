package com.speedacm.treeview.data.storage;

import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;

public class DiskStorage extends AbstractStorage
{

	public DiskStorage(AbstractStorage fallback)
	{
		super(fallback);
	}

	@Override
	public Zone[] getAllZones()
	{
		// TODO: implement this
		if(mFallback != null)
			return mFallback.getAllZones();
		return null;
	}

	@Override
	public Zone getZone(int id)
	{
		// TODO: implement this
		if(mFallback != null)
			return mFallback.getZone(id);
		return null;
	}

	@Override
	public Tree getTree(int id)
	{
		// TODO: implement this
		if(mFallback != null)
			return mFallback.getTree(id);
		return null;
	}

	@Override
	public Building[] getAllBuildings()
	{
		// TODO: implement this
		if(mFallback != null)
			return mFallback.getAllBuildings();
		return null;
	}

	@Override
	public Species[] getAllSpecies()
	{
		// TODO: implement this
		if(mFallback != null)
			return mFallback.getAllSpecies();
		return null;
	}

}
