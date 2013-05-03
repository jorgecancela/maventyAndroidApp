package org.maventy.android.interoperability;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

/**
 * This class is used to check if the login details are correct 
 * All network Activities are done in an AsyncTask way
 * 
 * @author jcancela
 */
public class CheckLogin extends AsyncTask<String[], Void, Boolean> {	 
    private Boolean loginOK = false;

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
	    nameValuePairs.add(new BasicNameValuePair(myNetworkTools.PARAM_USERNAME, inputStrings[1])); 

	    output_result = myNetworkTools.executePOST(myNetworkTools.URI_LOGIN, nameValuePairs); 
	    loginOK = !myNetworkTools.stringWithinResponse(output_result, myNetworkTools.LOGIN_FAIL);

	} catch (Exception e) {
	    Log.v("CheckLogin", "Exception: " + e.toString());
	    return false;
	}
	return loginOK;
    }
}