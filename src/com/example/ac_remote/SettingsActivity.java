package com.example.ac_remote;


import android.os.Bundle;
import android.preference.PreferenceActivity;

import android.view.MenuItem;

public class SettingsActivity extends PreferenceActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
	getActionBar().setDisplayHomeAsUpEnabled(true);
		
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

    