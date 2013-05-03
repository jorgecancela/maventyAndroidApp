package org.maventy.android;

import org.maventy.R;
import org.maventy.android.app.ExploreDatabase;
import org.maventy.android.app.visit.MaventyAndroid;
import org.maventy.android.db.SettingsDataSource;
import org.maventy.android.utils.SettingsDB;
import org.maventy.android.utils.Tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Main Activity. Starting point of the application.
 * @author jcancela
 */
public class Main extends Activity {
    private Button buttonNewVisit, buttonDatabase, buttonSettings, buttonTOS;
    private SettingsDataSource s_datasource;
    private TextView etWelcomeUsername;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// This function creates a STATIC instance of a Anthro class. 
	// This class will be used to calculate the outcome of each visit. 
	// This class needs to load some CSV files and that is a heavy task 
	// for this reason the work is done in background.
	Tools.init();

	// Read the last configuration stored in the database and setup the
	// configuration
	Tools.setContext(this.getApplicationContext());
	Tools.setSettings(this.getApplicationContext());
	Tools.setActivity(Main.this);

	setContentView(R.layout.main);

	// Show Username in the welcome message
	etWelcomeUsername = (TextView) findViewById(R.id.editText_welcome_username);
	buttonSettings = (Button) findViewById(R.id.button_main_settings);
	buttonDatabase = (Button) findViewById(R.id.button_main_database);
	buttonNewVisit = (Button) findViewById(R.id.button_main_new_visit);
	buttonTOS = (Button) findViewById(R.id.button_main_tos);
	
	showUsername();

	buttonNewVisit.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		try {
		    Intent k = new Intent(Main.this, MaventyAndroid.class);
		    startActivity(k);
		} catch (Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.main_error_message_starting_newvisit_activity), 
			    "Main", "Error trying to start MaventyAndroid activity: "+ e.toString());
		}
	    }
	});

	buttonDatabase.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		try {
		    Intent k = new Intent(Main.this, ExploreDatabase.class);
		    startActivity(k);
		} catch (Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.main_error_message_starting_exploredatabase_activity), 
			    "Main", "Error trying to start ExploreDatabase activity: "+ e.toString());
		}
	    }
	});

	buttonSettings.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		try {
		    Intent k = new Intent(Main.this, Settings.class);
		    startActivity(k);
		} catch (Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.main_error_message_starting_settings_activity), 
			    "Main", "Error trying to start Settings activity: "+ e.toString());
		}
	    }
	});

	buttonTOS.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		try {
		    Intent k = new Intent(Main.this, Tos.class);
		    startActivity(k);
		} catch (Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.main_error_message_starting_tos_activity), 
			    "Main", "Error trying to start TOS activity: "+ e.toString());
		}
	    }
	});
    }

    private void showUsername() {
	SettingsDB currentSet = new SettingsDB();

	try {
	    s_datasource = new SettingsDataSource(this);
	    s_datasource.open();
	    currentSet = s_datasource.getLastSettings();
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(this, Tools.getStringResource(R.string.settings_error_message_datasource_reading),
		    "Main", "Error opening Settings datasource" + e.toString());
	}	

	if((currentSet.getUsername() != null) && (currentSet.getUsername().compareTo("") != 0)) {
	    etWelcomeUsername.setText(currentSet.getUsername());
	} else {
	    etWelcomeUsername.setText(Tools.getStringResource(R.string.welcome_username));
	}

	try {
	    s_datasource.close();
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(this, Tools.getStringResource(R.string.settings_error_message_datasource_reading), 
		    "Main", "Error opening Settings datasource" + e.toString());
	}	
    }
}
    
