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
	private int scav_id;
//	private ArrayList<ScavHuntSubItem> sub_items;

	
	public ScavHunt(String pTitle, int num){
		title = pTitle;
		scav_id = num;
	//	sub_items = p_sub_items;
	}
	
	public String toString(){
		return title;
	}

	public String getTitle(){
		return title;
	}
	
	public int getScavId(){
		return scav_id;
	}
	
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
    	out.writeString(title);
    	out.writeInt(scav_id);
    }
    /*
    public void writeToParcel(Parcel out, int flags) {
    	out.writeString(title);
    	out.writeInt(sub_items.size());
    	
    	for(Iterator<ScavHuntSubItem> i = sub_items.iterator(); i.hasNext();){
    		ScavHuntSubItem item = i.next();
    		out.writeString(item.getTitle());
    		out.writeString(item.getBody());
    	}
    }
*/
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
    	scav_id = in.readInt();
    }
}
