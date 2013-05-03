package org.maventy.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.crypto.Crypto;
import org.maventy.R;
import org.maventy.android.db.SettingsDataSource;
import org.maventy.android.interoperability.CheckLogin;
import org.maventy.android.utils.SettingsDB;
import org.maventy.android.utils.Tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Settings Activity is used to allow the user store his preferences as well as his maventy login and password
 * @author jcancela
 */
public class Settings extends Activity {
    private String email, username, password , language, sDateStored;
    private EditText etEmail, etUsername, etPassword;
    private Button buttonSaveSettings, backButton, buttonShowLanguages;
    private SettingsDataSource s_datasource;
    private Calendar dateOfStored;
    private AlertDialog alert;
    private String[] availableLanguages = {Tools.getStringResource(R.string.settings_language_english), 
	    Tools.getStringResource(R.string.settings_language_french),
	    Tools.getStringResource(R.string.settings_language_spanish),
	    Tools.getStringResource(R.string.settings_language_malagasy)};

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
		
	// Read the last configuration stored in the database and setup the configuration	
	Tools.setContext(this.getApplicationContext());
	Tools.setSettings(this.getApplicationContext());
	Tools.setActivity(Settings.this);

	setContentView(R.layout.settings);

	buttonSaveSettings = (Button) findViewById(R.id.button_save_settings);
	buttonShowLanguages = (Button) findViewById(R.id.button_show_language);
	backButton = (Button) findViewById(R.id.button_back_settings);

	etEmail = (EditText) findViewById(R.id.editText_settings_email);
	etUsername = (EditText) findViewById(R.id.editText_settings_username);
	etPassword = (EditText) findViewById(R.id.editText_settings_password);

	ArrayList<String> lanStrings = new ArrayList<String>(Arrays.asList(availableLanguages));

	// Show current configuration (if any) in the form
	showConfig();

	// This Alert Dialog shows the language options. It is the input for to set up the language preferences
	AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
	builder.setTitle(Tools.getStringResource(R.string.settings_choose_language));
	builder.setIcon(R.drawable.location_web_site);
	builder.setSingleChoiceItems(availableLanguages, lanStrings.indexOf(buttonShowLanguages.getText()), new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int item) {
		buttonShowLanguages.setText(availableLanguages[item]);
		language = Tools.languageStringToCode(availableLanguages[item]);
	    }
	});

	builder.setPositiveButton(Tools.getStringResource(R.string.tools_yes),
		new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {

	    }
	}); 

	builder.setNegativeButton(Tools.getStringResource(R.string.tools_no), 
		new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {
	    }
	});

	alert = builder.create();	

	backButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View view) {
		try {	
		    Intent k = new Intent(Settings.this, Main.class);
		    startActivity(k);
		} catch(Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.settings_error_message_starting_main_class), 
			    "Settings", "Error trying to go back: " + e.toString());
		}      		
	    }
	});  

	buttonShowLanguages.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View view) {
		try {	
		    alert.show();
		} catch(Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error), 
			    "Settings", "Error on buttonShowLanguages: " + e.toString());
		}      		
	    }
	});  

	buttonSaveSettings.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {

		dateOfStored = new GregorianCalendar();
		dateOfStored = Calendar.getInstance();

		sDateStored = Tools.CalendarToString(dateOfStored);

		email = etEmail.getText().toString().trim();
		username = etUsername.getText().toString().trim();
		password = etPassword.getText().toString();

		String[] inputString = {username, password};

		// If the device is connected to Internet the app will check if the login details are correct 
		// if not store the login and show a message
		if(Tools.isOnline()) {
		    CheckLogin myCheckLogin = new CheckLogin();
		    try {
			myCheckLogin.execute(inputString);

			if(myCheckLogin.get()) {
			    storeSettings();
			} else {
			    Tools.showToastMessageAndLog(Tools.getContext() , Tools.getStringResource(R.string.settings_login_error), 
				    "Settings" , "Login details are wrong.");
			}

		    } catch(Exception e) {
			Log.e("Settings" , "Exception checking the Login details: " + e.toString());
		    }

		} else {    

		    // When Internet is not available the app stores the login details but it informs the user that It was not possible
		    // to check that the login details are OK.
		    storeSettings();
		    Tools.showToastMessage(Tools.getContext(), Tools.getStringResource(R.string.settings_login_stored_not_checked));   
		}	
	    }
	});
    }

    // Reads the last configuration available and shows it 
    private void showConfig() {
	SettingsDB currentSet = new SettingsDB();

	try {
	    s_datasource = new SettingsDataSource(this);
	    s_datasource.open();
	    currentSet = s_datasource.getLastSettings();
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.settings_error_message_datasource_reading), 
		    "Settings", "Error opening Settings datasource" + e.toString());
	}	

	if(currentSet.getLanguage() == null) {
	    etEmail.setText("");
	    etUsername.setText("");
	    etPassword.setText("");	
	    buttonShowLanguages.setText(Tools.getStringResource(R.string.settings_language_english));

	} else {

	    buttonShowLanguages.setText(Tools.languageCodeToString(currentSet.getLanguage()));

	    etEmail.setText(currentSet.getEmail());
	    etUsername.setText(currentSet.getUsername());

	    try {
		etPassword.setText(Crypto.decrypt(currentSet.getPassword()));	
	    } catch(Exception e) {
		Tools.showToastMessageAndLog(this.getApplicationContext(), Tools.getStringResource(R.string.settings_error_message_decrypting_password),
			"Settings" , "Exception in the decrypt process " + e.toString());
	    }
	}
	s_datasource.close();
    }

    // Stores the settings in the local database 
    private void storeSettings() {
	try {		    
	    s_datasource = new SettingsDataSource(Tools.getContext());
	    s_datasource.open();  
	    s_datasource.createSettings(email, username, Crypto.encrypt(password), language, sDateStored);
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(this, Tools.getStringResource(R.string.settings_error_message_datasource_saving_new_settings), 
		    "Settings", "Error saving a new Settings: " + e.toString());
	}

	try {
	    s_datasource.close();
	    Intent k = new Intent(Settings.this, Main.class);
	    startActivity(k);  
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(this, Tools.getStringResource(R.string.settings_error_message_datasource_saving_new_settings), 
		    "Settings", "Error trying to start main screen: " + e.toString());
	}
    }
}
