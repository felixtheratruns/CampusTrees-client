package com.speedacm.treeview.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class ScavHunt implements Parcelable {

	private String title;
	private ArrayList<String> item_titles;
	private ArrayList<String> item_bodies;
	
	public ScavHunt(String pTitle, ArrayList<String> p_titles, ArrayList<String> p_bodies)
	{
		title = pTitle;
		item_titles = p_titles;
		item_bodies = p_bodies;
	}

	public String getTitle(){
		return title;
	}
	
	public ArrayList<String> getBodies(){
		return item_bodies;
	}
	
	public ArrayList<String> getTitles(){
		return item_titles;
	}
	
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
       
    	out.writeInt(item_titles.size());
    	
    	for(Iterator<String> i = item_titles.iterator(); i.hasNext();){
    		out.writeString(i.next());
    	}
    	
    	out.writeInt(item_bodies.size());
    	
    	for(Iterator<String> i = item_bodies.iterator(); i.hasNext();){
    		out.writeString(i.next());
    	}
    	  	
    	out.writeString(title);
    }

    public static final Parcelable.Creator<ScavHunt> CREATOR
            = new Parcelable.Creator<ScavHunt>() {
        public ScavHunt createFromParcel(Parcel in) {
            return new ScavHunt(in);
        }

        public ScavHunt[] newArray(int size) {
            return new ScavHunt[size];
        }
    };
    
    private JsonNode createJson(String json){
    	ObjectMapper mMapper = new ObjectMapper();
		try
		{
			JsonParser jp = mMapper.getJsonFactory().createJsonParser(json);
			return mMapper.readTree(jp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.e("json parse exception?", "exception", e);
			return null;
		}
    }
    
    private ScavHunt(Parcel in) {  
    	int titles_size = in.readInt();
    	ArrayList<String> titles = new ArrayList<String>();
    	for(int i = 0; i < titles_size ; i++){
    		titles.add(in.readString());
    	}
    	item_titles = titles;
    	
    	int bodies_size = in.readInt();
    	ArrayList<String> bodies = new ArrayList<String>();
    	for(int i = 0; i < bodies_size; i++){
    		bodies.add(in.readString());
    	}
    	
    	item_bodies = bodies;
    	
    	title = in.readString();
    }
}
