package org.maventy.android.app;

import org.maventy.R;
import org.maventy.android.Main;
import org.maventy.android.db.PatientsDataSource;
import org.maventy.android.db.VisitsDataSource;
import org.maventy.android.utils.Tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * 
 * @author jcancela
 */
public class Download extends Activity {     
    private PatientsDataSource datasource;
    private VisitsDataSource v_datasource;
    
    private Button backButton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// Read the last configuration stored in the database and setup the
	// configuration
	Tools.setContext(this.getApplicationContext());
	Tools.setSettings(this);
	Tools.setActivity(Download.this);

	setContentView(R.layout.download);  

	try {
	    datasource = new PatientsDataSource(this);
	    datasource.open();

	    v_datasource = new VisitsDataSource(this);
	    v_datasource.open();
	} catch (Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "Download", "Error opening the databases: " + e.toString());
	}

	backButton = (Button) findViewById(R.id.button_back_download);
	backButton.setOnClickListener(new View.OnClickListener() { 
	    @Override
	    public void onClick(View view) {
		try {	
		    Intent k = new Intent(Download.this, Main.class);
		    startActivity(k);
		} catch (Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
			    "Download", "Error trying to go back: " + e.toString());
		}      		
	    }
	});
	

	Spinner spin;
	String spin_val;
        String[] gender = {"Any", "Male", "Female" };
	
	spin = (Spinner) findViewById(R.id.spinner_download_sex);
	
	//Register a callback to be invoked when an item in this AdapterView has been selected	
	
	spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {
	        public void onItemSelected(AdapterView<?> arg0, View v, int position, long id)
	        {
	                //spin_val = gender[position];//saving the value selected
	                
	                Log.v("Tracking", "Selected: " + position);
	        }

	        public void onNothingSelected(AdapterView<?> arg0)
	        {
	        }
	    });
	

	 
	//setting array adaptors to spinners 
	//ArrayAdapter is a BaseAdapter that is backed by an array of arbitrary objects
	        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(Download.this, android.R.layout.simple_spinner_item, gender);

	        // setting adapteers to spinners
	        spin.setAdapter(spin_adapter);
	    
 
	
    
	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menu_button, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	Intent k = Tools.menuSwitcher(this.getApplicationContext(), item);

	if(k == null) {
	    return false;
	} else {
	    startActivity(k);
	    return true;
	}
    }

    @Override
    protected void onPause() {	 
	datasource.close();
	v_datasource.close();
	super.onPause();
    }

    @Override
    protected void onResume() {	
	super.onResume();
    }
    
   
}
