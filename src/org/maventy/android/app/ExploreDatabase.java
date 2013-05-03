package org.maventy.android.app;

import java.util.ArrayList;
import java.util.List;

import org.maventy.R;
import org.maventy.android.Main;
import org.maventy.android.db.PatientsDataSource;
import org.maventy.android.utils.Constants;
import org.maventy.android.utils.DataParcelable;
import org.maventy.android.utils.MyCustomAnimation;
import org.maventy.android.utils.Patient;
import org.maventy.android.utils.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * This activity reads all the patients in the database and shows them in a ListView
 * @author jcancela
 */
public class ExploreDatabase extends Activity {
    private ArrayList<Patient> patient_sorted = new ArrayList<Patient>();
    private PatientsDataSource datasource;
    private ArrayAdapter<Patient> adapter_patient;
    private Button addPatient, backButton, cloudButton, uploadButton, downloadButton;
    private List<Patient> values;
    private static ListView lv;
    private EditText et;
    private View extraFeaturesLayout; 
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// Read the last configuration stored in the database and setup the
	// configuration	
	try {
	    Tools.setSettings(this);
	    Tools.setContext(this.getApplicationContext());
	    Tools.setActivity(ExploreDatabase.this);
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "ExploreDatase", "Exception setting up context: " + e.toString());
	}
	
	setContentView(R.layout.db_explore_patients);  

	try {
  	    datasource = new PatientsDataSource(this.getApplicationContext());
	    datasource.open();
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "ExploreDatase", "Exception opening database: " + e.toString());
	}
	
	values = datasource.getAllPatients();

	// Use the SimpleCursorAdapter to show the elements in a ListView
	adapter_patient = new PatientAdapter(this, R.layout.item_list_patient, values);
	
        lv=(ListView)findViewById(R.id.list_patients);
	
	lv.setAdapter(adapter_patient);
	lv.setTextFilterEnabled(true);
	
	lv.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Patient patient = (Patient) lv.getAdapter().getItem(position);

		try {			         	 
		    DataParcelable dparcelable = new DataParcelable((int) patient.getId());  
		    datasource.close();

		    // When a element on the List is pressed ExplorePatient is called with the patient ID as argument
		    Intent k = new Intent(ExploreDatabase.this, ExplorePatient.class);
		    k.putExtra(Constants.PATIENT_ID_PARCEL_STRING, dparcelable);
		    startActivity(k);

		} catch(Exception e) {
		    Log.v("ExploreDatabase", "Error trying to start ExplorePatient activity: " + e.toString());
		}
	    }
	});

	extraFeaturesLayout = (LinearLayout) findViewById(R.id.layout_extra_features);
	
	backButton = (Button) findViewById(R.id.button_back_db_explore_patients);
	backButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View view) {
		try {	
		    datasource.close();
		    Intent k = new Intent(ExploreDatabase.this, Main.class);
		    startActivity(k);

		} catch(Exception e) {
		    Log.e("ExploreDatabase", "Error at button_back_db_explore_patients");
		}      		
	    }
	});  
	
	uploadButton = (Button) findViewById(R.id.button_db_explore_upload);
	uploadButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		try {
		    Intent k = new Intent(ExploreDatabase.this, UploadVisit.class);
		    startActivity(k);
		} catch (Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.main_error_message_starting_upload_activity),
			    "ExploreDatabase", "Error trying to start Upload activity: "+ e.toString());
		}
	    }
	});
	
	downloadButton = (Button) findViewById(R.id.button_db_explore_download);
	downloadButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View view) {
		try {	
		    Intent k = new Intent(ExploreDatabase.this, Download.class);
		    startActivity(k);  
		} catch(Exception e) {
		    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.main_error_message_starting_upload_activity),
			    "ExploreDatabase", "Error trying to start Download activity: "+ e.toString());
		}      		
	    }
	});  
	
	cloudButton = (Button) findViewById(R.id.button_db_cloud);
	cloudButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View view) {
		try {	
		   		    
		    if(extraFeaturesLayout.getVisibility() == View.VISIBLE){
			
			MyCustomAnimation.collapse(extraFeaturesLayout);
			  
		    }else{

			MyCustomAnimation.expand(extraFeaturesLayout);
			
		    }
		   
		} catch(Exception e) {
		    Log.e("ExploreDatabase", "Error on cloudButton");
		}      		
	    }
	});  

	addPatient = (Button) findViewById(R.id.button_db_add_new_patient);
	addPatient.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View view) {
		try {	
		    datasource.close();
		    Intent k = new Intent(ExploreDatabase.this, AddNewPatient.class);
		    startActivity(k);

		} catch(Exception e) {
		    Log.v("ExploreDatabase", "Error trying to start AddNewPatient activity: " + e.toString());
		}      		
	    }
	});  
	
	//
	// et is the search field, when the user starts typing this listener looks for patients in the list who match
	// and the ListView is automatically updated just with these patients
	//
	et = (EditText) findViewById(R.id.EditText01);
	et.addTextChangedListener(new TextWatcher()
	{
	    @Override
	    public void afterTextChanged(Editable s) {
		// Abstract Method of TextWatcher Interface.
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s,
		    int start, int count, int after) {
		// Abstract Method of TextWatcher Interface.
	    }

	    @Override
	    public void onTextChanged(CharSequence s,
		    int start, int before, int count) {
		//textlength = et.getText().length();
		patient_sorted.clear();
	
		 datasource.open();

		 for(Patient p : datasource.getAllPatients()) {
		     if((p.toString().toLowerCase().contains(s.toString().toLowerCase()))||(s.toString().compareTo("")==0)) {
	                     patient_sorted.add(p);
		     }
		}
		
		datasource.close();

		adapter_patient.setNotifyOnChange(true);
		adapter_patient.clear();
		
		for (Patient p : patient_sorted) {
		    adapter_patient.add(p);
		    adapter_patient.notifyDataSetChanged();
		}

		lv.setAdapter(adapter_patient);
	    }
	});
	
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
	super.onPause();
    }

    @Override
    protected void onResume() {	
	/**datasource = new PatientsDataSource(this);
	datasource.open();

	List<Patient> values = datasource.getAllPatients();

	ArrayAdapter<Patient> adapter = new ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, values);

	ListView lv = getListView();
	lv.setListAdapter(adapter);

	lv.setTextFilterEnabled(true);
*/
	super.onResume();
    }
    
    // Helps to customize the ListView
    public class PatientAdapter extends ArrayAdapter<Patient> {
	private List<Patient> patients;

	public PatientAdapter(Context context, int view, List<Patient> patientsToShow) {
	    super(context, view, patientsToShow);

	    patients = patientsToShow;
	}

	@Override
	public int getCount() {
	    return patients.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View currentView = convertView;

	    LayoutInflater currentViewInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    currentView = currentViewInflater.inflate(R.layout.item_list_patient, null);

	    Patient currentPatient = patients.get(position);

	    TextView name = (TextView) currentView.findViewById(R.id.textView_item_list_patient_name);
	    TextView residence = (TextView) currentView.findViewById(R.id.textView_item_list_patient_residence);
	    
	    name.setText(currentPatient.getName());
	    residence.setText(currentPatient.getResidence() + " - " + Tools.CalendarToStringToShow(currentPatient.getDateOfBirth()));

	    return currentView;
	}
    }
} 

