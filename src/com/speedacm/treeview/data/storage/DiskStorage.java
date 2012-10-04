package com.speedacm.treeview.data.storage;

import java.util.List;

import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.ScavHunt;
import com.speedacm.treeview.models.ScavHuntSubItem;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.WildLifeFact;
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
	public void getZoneDetails(Zone target)
	{
		// TODO: implement this
		if(mFallback != null)
			mFallback.getZoneDetails(target);
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
	
	@Override
	public Species getSpecies(int id)
	{
		// TODO: implement this
		if(mFallback != null)
			return mFallback.getSpecies(id);
		return null;
	}

	@Override
	public List<Integer> getFloweringSpecies(int month)
	{
		// TODO: implement this
		if(mFallback != null)
			return mFallback.getFloweringSpecies(month);
		return null;
	}

	@Override
	public List<Integer> getFruitingSpecies(int month)
	{
		// TODO: implement this
		if(mFallback != null)
			return mFallback.getFloweringSpecies(month);
		return null;
	}

	@Override
	public PlantFact[] getAllPlantFacts() {
		//TODO: implement this
		if(mFallback !=null)
			return mFallback.getAllPlantFacts();
		return null;
	}

	@Override
	public News[] getAllNews() {
		// TODO Auto-generated method stub
		if(mFallback !=null)
			return mFallback.getAllNews();
		return null;
	}
	
	@Override
	public ScavHunt[] getAllScavHunt() {
		// TODO Auto-generated method stub
		if(mFallback !=null)
			return mFallback.getAllScavHunt();
		return null;
	}
	

	public ScavHuntSubItem[] getSubItemsForScavHunt(int scav_id) {
		// TODO Auto-generated method stub
		if(mFallback !=null)
			return mFallback.getSubItemsForScavHunt(scav_id);
		return null;
	}
	
	@Override
	public WildLifeFact[] getAllWildLifeFacts() {
		// TODO Auto-generated method stub
		if(mFallback !=null)
			return mFallback.getAllWildLifeFacts();
		return null;
	}

}
