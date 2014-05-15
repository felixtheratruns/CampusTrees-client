/** This file is part of client-side of the CampusTrees Project. 
It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution. No part of CampusTrees Project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.*/
package com.speedacm.treeview.views;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.speedacm.treeview.R;

public class AboutActivity extends Activity
{
	private static final String nbsp4 = "&nbsp;&nbsp;&nbsp;&nbsp;";
	private static final String aboutText =
		"<p>The CampTree application was originally developed for Android as " +
		"a Computer Engineering Capstone Design project in Spring 2012, for " +
		"the University of Louisville's Urban Wildlife Research Lab.</p>" +
		
		"<p>The idea for the app was provided by Dr. Tommy Parker, who heads up " +
		"the UWRL in the University of Louisville Biology department.</p>" +
					
		"<p>This app uses the following third-party components:" +
		"<br />" + nbsp4 + "<i>cwac-sacklist</i>" +
		"<br />" + nbsp4 + "<i>cwac-merge</i>" +
		"<br />" + nbsp4 + "<i>Jackson JSON</i></p>" +
		
		"<p>CWAC components are available from http://github.com/commonsguy/</p>" +
		"<p>Jackson JSON is available from http://jackson.codehaus.org</p>";
			
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutbox);
		
		TextView tv = (TextView)findViewById(R.id.aboutText);
		tv.setText(Html.fromHtml(aboutText));
	}
}
