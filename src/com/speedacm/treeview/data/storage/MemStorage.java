package com.speedacm.treeview.data.storage;

import java.util.HashMap;

import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;

public class MemStorage extends AbstractStorage
{
	
	//private HashMap<Integer, Building> mBuildings;
	private HashMap<Integer, Zone> mZones;
	private HashMap<Integer, Tree> mTrees;

	public MemStorage(AbstractStorage fallback)
	{
		super(fallback);
		//mBuildings = new HashMap<Integer, Building>();
		mZones = new HashMap<Integer, Zone>();
		mTrees = new HashMap<Integer, Tree>();
	}

	@Override
	public Zone[] getAllZones()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zone getZone(int id)
	{
		if(mZones.containsKey(id))
		{
			return mZones.get(id);
		}
		else if(mFallback != null)
		{
			Zone z = mFallback.getZone(id);
			if(z != null)
				mZones.put(id, z);
			return z;
		}
		
		return null;
	}

	@Override
	public Tree getTree(int id)
	{
		if(mTrees.containsKey(id))
		{
			return mTrees.get(id);
		}
		else if(mFallback != null)
		{
			Tree t = mFallback.getTree(id);
			if(t != null)
				mTrees.put(id, t);
			return t;
		}
		
		return null;
	}

	@Override
	public Building[] getAllBuildings()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Species[] getAllSpecies()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
