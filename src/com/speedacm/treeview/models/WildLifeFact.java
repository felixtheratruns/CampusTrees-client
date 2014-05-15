/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.models;


public class WildLifeFact {

	private String title;
	private String body;
	private String date;

	

	public WildLifeFact(String pTitle, String pBody)
	{
		title = pTitle;
		body = pBody;
		
	}

	public String toString()
	{
		return title + ":\n"  + date + ":\n" + body;
	}

	public String getTitle()
	{
		return title;
	}
	
	public String getBody()
	{
		return body;
	}
	
}
