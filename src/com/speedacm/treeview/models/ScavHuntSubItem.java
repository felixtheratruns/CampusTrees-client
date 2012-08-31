package com.speedacm.treeview.models;

public class ScavHuntSubItem {

	private String title;
	private String body;
	
	public ScavHuntSubItem(String t, String b){
		title = t;
		body = b;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getBody(){
		return body;
	}
	
	public String toString(){
		return title;
	}
	
}
