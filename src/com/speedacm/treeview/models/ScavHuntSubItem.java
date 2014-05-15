/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
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
