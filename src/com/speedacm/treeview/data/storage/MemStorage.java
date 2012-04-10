package com.speedacm.treeview.data.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.WildLifeFact;
import com.speedacm.treeview.models.Zone;

public class MemStorage extends AbstractStorage
{
	
	//private HashMap<Integer, Building> mBuildings;	
	private WildLifeFact[] wildLifeFactsArray;
	private News[] newsArray;
	private PlantFact[] plantFactsArray;
	private Zone[] mZoneArray;
	private Species[] mSpeciesArray;
	private HashMap<Integer, Zone> mZones;
	private HashMap<Integer, Tree> mTrees;
	private HashMap<Integer, Species> mSpecies;
	
	private Map<Integer, List<Integer>> mFruitMonths;
	private Map<Integer, List<Integer>> mFlowerMonths;

	public MemStorage(AbstractStorage fallback)
	{
		super(fallback);
		//mBuildings = new HashMap<Integer, Building>();
		mZones = new HashMap<Integer, Zone>();
		mTrees = new HashMap<Integer, Tree>();
		mFruitMonths = new HashMap<Integer, List<Integer>>();
		mFlowerMonths = new HashMap<Integer, List<Integer>>();
	}
	
	private void fetchAndBuildZones()
	{
		// fetch the zone array
		if(mZoneArray == null && mFallback != null)
			mZoneArray = mFallback.getAllZones();
		
		if(mZoneArray == null)
			return;
		
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
		
		// update tree mapping
		if(target.isFetched())
			for(Tree t : target.getTrees()) 
				mTrees.put(t.getID(), t);
		
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
		{
			mSpeciesArray = mFallback.getAllSpecies();
			mSpecies = new HashMap<Integer, Species>();
			
			if(mSpeciesArray == null)
				return null;
			
			for(Species s : mSpeciesArray)
				mSpecies.put(s.getID(), s);
		}
		
		return mSpeciesArray;
	}
	
	@Override
	public Species getSpecies(int id)
	{
		if(mSpecies == null)
			getAllSpecies();
		
		return mSpecies.get(id);
	}

	@Override
	public List<Integer> getFloweringSpecies(int month) {
		if(!mFlowerMonths.containsKey(month) && mFallback != null)
			mFlowerMonths.put(month, mFallback.getFloweringSpecies(month));
		
		return mFlowerMonths.get(month);
	}

	@Override
	public List<Integer> getFruitingSpecies(int month) {
		if(!mFruitMonths.containsKey(month) && mFallback != null)
			mFruitMonths.put(month, mFallback.getFruitingSpecies(month));
		
		return mFruitMonths.get(month);
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
	
	@Override
	public WildLifeFact[] getAllWildLifeFacts() {
		// TODO Auto-generated method stub
		if(wildLifeFactsArray == null && mFallback != null)
			wildLifeFactsArray = mFallback.getAllWildLifeFacts();
		return wildLifeFactsArray;
	}

}
