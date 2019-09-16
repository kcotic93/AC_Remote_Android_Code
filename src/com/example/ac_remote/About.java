package com.example.ac_remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView txtDetails = (TextView) findViewById(R.id.txtDetails);
		txtDetails.setText("The purpose of this application is to control your air conditioning from anywhere in the world using the Arduino.Before using the application you need to set the IP address and PORT in Settings.");
		TextView txtDetails2 = (TextView) findViewById(R.id.txtDetails2);
		txtDetails2.setText("This application will work only with the corresponding Arduino device and software.");
		TextView txtDetails3 = (TextView) findViewById(R.id.txtDetails3);
		txtDetails3.setText("All rights reserved");
		TextView txtDetails4 = (TextView) findViewById(R.id.txtDetails4);
		txtDetails4.setText("Kristijan Èotiæ"+"\u00a9");
}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        finish();

	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}   
}


