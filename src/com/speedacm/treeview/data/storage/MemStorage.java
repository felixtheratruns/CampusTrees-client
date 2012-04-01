package com.speedacm.treeview.data.storage;

import java.util.HashMap;

import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;

public class MemStorage extends AbstractStorage
{
	
	//private HashMap<Integer, Building> mBuildings;	
	private News[] newsArray;
	private PlantFact[] plantFactsArray;
	private Zone[] mZoneArray;
	private Species[] mSpeciesArray;
	private HashMap<Integer, Zone> mZones;
	private HashMap<Integer, Tree> mTrees;

	public MemStorage(AbstractStorage fallback)
	{
		super(fallback);
		//mBuildings = new HashMap<Integer, Building>();
		mZones = new HashMap<Integer, Zone>();
		mTrees = new HashMap<Integer, Tree>();
	}
	
	private void fetchAndBuildZones()
	{
		// fetch the zone array
		if(mZoneArray == null && mFallback != null)
			mZoneArray = mFallback.getAllZones();
		
		// build a map of it
		for(Zone z : mZoneArray)
			mZones.put(z.getID(), z);
	}

	@Override
	public Zone[] getAllZones()
	{
		if(mZoneArray == null)
			fetchAndBuildZones();
		
		return mZoneArray;
	}

	@Override
	public void getZoneDetails(Zone target)
	{
		// if it isn't already fetched, there's no way to retrieve it
		// from our cache, so defer to the fallback
		
		if(target.isFetched())
			return;
		
		if(mFallback != null)
			mFallback.getZoneDetails(target);
		
		// TODO: update tree mapping from resultant zone details
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
		if(mSpeciesArray == null && mFallback != null)
			mSpeciesArray = mFallback.getAllSpecies();
		
		return mSpeciesArray;
	}

	@Override
	public PlantFact[] getAllPlantFacts() {
		// TODO Auto-generated method stub
		if(plantFactsArray == null && mFallback != null)
			plantFactsArray = mFallback.getAllPlantFacts();
		return plantFactsArray;
	}

	@Override
	public News[] getAllNews() {
		// TODO Auto-generated method stub
		if(newsArray == null && mFallback != null)
			newsArray = mFallback.getAllNews();
		return newsArray;
	}

}
