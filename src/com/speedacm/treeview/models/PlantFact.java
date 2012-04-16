package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;

public class PlantFact {
	



	private GeoPoint latLong;
	private String title;
	private int id;
	private int sid;
	private double latitude;
	private double longitude;
	private double dbh;
	private double height;
	private double vol;
	private double greenwt;
	private double drywt;
	private double carbonwt;
	private double age;
	private double co2pyear;
	private double crownarea;
	
	
	
	public PlantFact(GeoPoint pLatLong, String ptitle, int pid, int psid, double platitude, double plongitude, double pdbh, double pheight, double pvol, double pgreenwt, double pdrywt, double pcarbonwt, double page, double pco2pyear, double pcrownarea)
	{
		latLong = pLatLong;
		title = ptitle;
		id = pid;
		sid = psid;
		latitude = platitude;
		longitude = plongitude;
		dbh = pdbh;
		height = pheight;
		vol = pvol;
		greenwt = pgreenwt;
		drywt = pdrywt;
		carbonwt = pcarbonwt;
		age = page;
		co2pyear = pco2pyear;
		crownarea = pcrownarea;
		
		
	}
	
	public GeoPoint getLatLong() {
		return latLong;
	}



	public String getTitle() {
		return title;
	}



	public int getId() {
		return id;
	}



	public int getSid() {
		return sid;
	}



	public double getLatitude() {
		return latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public double getDbh() {
		return dbh;
	}



	public double getHeight() {
		return height;
	}



	public double getVol() {
		return vol;
	}



	public double getGreenwt() {
		return greenwt;
	}



	public double getDrywt() {
		return drywt;
	}



	public double getCarbonwt() {
		return carbonwt;
	}



	public double getAge() {
		return age;
	}



	public double getCo2pyear() {
		return co2pyear;
	}



	public double getCrownarea() {
		return crownarea;
	}

	
}
