package com.speedacm.treeview.views;

import com.speedacm.treeview.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Start extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnSimple = (Button) findViewById(R.id.btnSimple);
        btnSimple.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Toast.makeText(getApplicationContext(),
						" You clicked Simple ListView button", Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(v.getContext(), SimpleListView.class);
       		  	startActivityForResult(intent, 0);
			}
		});

    }
}

