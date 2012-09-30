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
	private ArrayList<ScavHuntSubItem> sub_items;
	
	
	public ScavHunt(String pTitle, ArrayList<ScavHuntSubItem> p_sub_items){
		title = pTitle;
		sub_items = p_sub_items;
	}
	
	public String toString(){
		return title;
	}
	
	
	public ArrayList<ScavHuntSubItem> getSubItems(){
		return sub_items;
	}

	public String getTitle(){
		return title;
	}
	
	
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
    	out.writeString(title);
    	out.writeInt(sub_items.size());
    	
    	for(Iterator<ScavHuntSubItem> i = sub_items.iterator(); i.hasNext();){
    		ScavHuntSubItem item = i.next();
    		out.writeString(item.getTitle());
    		out.writeString(item.getBody());
    	}
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
    
    private ScavHunt(Parcel in) {
    	title = in.readString();
    	int titles_size = in.readInt();
    	
    	sub_items = new ArrayList<ScavHuntSubItem>();
    	
    	for(int i = 0; i < titles_size; i++){
    		String title = in.readString();
    		String body = in.readString();
    		sub_items.add(new ScavHuntSubItem(title, body));
    	}
    }
}
