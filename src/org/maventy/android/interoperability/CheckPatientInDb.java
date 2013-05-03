package org.maventy.android.interoperability;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

/**
 * This class is used to check if the login details are correct 
 * This network activities need to be done in a AsyncTask class
 * 
 * @author jcancela
 */
public class CheckPatientInDb extends AsyncTask<String[], Void, Boolean> {	 
    private Boolean loginOK = false;
    private Boolean patientFound = false;

    @Override
    protected Boolean doInBackground(String[]... params) {
	String output_result = new String();
	NetworkTools myNetworkTools = new NetworkTools();

	try {
	    String[] inputStrings = params[0];

	    // Login process 
	    // POST request /account/login/ 
	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	    nameValuePairs.add(new BasicNameValuePair(myNetworkTools.PARAM_USERNAME, inputStrings[0]));   
	    nameValuePairs.add(new BasicNameValuePair(myNetworkTools.PARAM_PASSWORD, inputStrings[1])); 

	    output_result = myNetworkTools.executePOST(myNetworkTools.URI_LOGIN, nameValuePairs); 
	    loginOK = !myNetworkTools.stringWithinResponse(output_result, myNetworkTools.LOGIN_FAIL);

	    if(loginOK) {
		List<NameValuePair> nameValuePairs_patient_search = new ArrayList<NameValuePair>(3);
		nameValuePairs_patient_search.add(new BasicNameValuePair(myNetworkTools.PARAM_NAME, inputStrings[2]));  
		nameValuePairs_patient_search.add(new BasicNameValuePair(myNetworkTools.PARAM_SEX, inputStrings[3]));   
		nameValuePairs_patient_search.add(new BasicNameValuePair(myNetworkTools.PARAM_BIRTHDATE, inputStrings[4]));

		output_result = new String();
		output_result = myNetworkTools.loginAndExecutePOST(myNetworkTools.URI_LOGIN, myNetworkTools.URI_SEARCH_PATIENT, nameValuePairs, nameValuePairs_patient_search);

		patientFound = !myNetworkTools.stringWithinResponse(output_result, myNetworkTools.NO_PATIENT_FOUND);

		return patientFound;
	    }

	} catch (Exception e) {
	    Log.v("CheckPatientInDb" , "Exception: " + e.toString()); 
	    return false;
	}
	return patientFound;
    }
}