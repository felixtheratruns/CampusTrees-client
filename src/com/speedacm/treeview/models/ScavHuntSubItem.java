package com.speedacm.treeview.models;

public class ScavHuntSubItem {

	private String title;
	private String body;
	private String png_name;
	
	public ScavHuntSubItem(String t, String b, String p){
		title = t;
		body = b;
		png_name = p;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getBody(){
		return body;
	}
	
	public String getPngName(){
		return png_name;
	}
	
	public String toString(){
		return title;
	}
	
}
