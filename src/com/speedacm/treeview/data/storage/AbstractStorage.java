/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.data.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.News;
import com.speedacm.treeview.models.ScavHunt;
import com.speedacm.treeview.models.PlantFact;
import com.speedacm.treeview.models.ScavHuntSubItem;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.WildLifeFact;
import com.speedacm.treeview.models.Zone;

public abstract class AbstractStorage
{
	protected AbstractStorage mFallback;
	
	public AbstractStorage(AbstractStorage fallback)
	{
		mFallback = fallback;
	}
	
	public abstract PlantFact[] getAllPlantFacts();
	public abstract Zone[] getAllZones();
	public abstract void getZoneDetails(Zone target);
	public abstract Tree getTree(int id);
	public abstract Building[] getAllBuildings();
	public abstract Species[] getAllSpecies();
	public abstract Species getSpecies(int id);
	public abstract List<Integer> getFloweringSpecies(int month);
	public abstract List<Integer> getFruitingSpecies(int month);
	public abstract News[] getAllNews();
	public abstract ScavHunt[] getAllScavHunt();
	public abstract ScavHuntSubItem[] getSubItemsForScavHunt(int scav_id);
	public abstract WildLifeFact[] getAllWildLifeFacts();

	public Object fetch(String address) throws MalformedURLException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
