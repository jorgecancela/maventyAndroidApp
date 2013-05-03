package org.maventy.android.interoperability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class NetworkTools {

    /*
     * HTTPS URIs to test. A different login/password is required.
     *      
    public final String URI_LOGIN = "https://childdb.appspot.com/account/login/";
    public final String URI_ADD_VISIT_START = "https://childdb.appspot.com/test/patients/";
    public final String URI_ADD_PATIENT_AND_VISIT = "https://childdb.appspot.com/test/patients/new-with-visit1";
    public final String URI_SEARCH_PATIENT = "https://childdb.appspot.com/test/patients/search";
  **/
    
    /*
     * HTTPS URIs to access maventy database. It requires the same login/password to login in the website.
     * 
     *     **/
    public final String URI_LOGIN = "https://childdb.appspot.com/account/login/";
    public final String URI_ADD_VISIT_START = "https://childdb.appspot.com/maventy/patients/";
    public final String URI_ADD_PATIENT_AND_VISIT = "https://childdb.appspot.com/maventy/patients/new-with-visit1";
    public final String URI_SEARCH_PATIENT = "https://childdb.appspot.com/maventy/patients/search";

    
    /*
     * URIs to test. A different login/password is required.
     * 
    public final String URI_LOGIN = "http://childhealth.maventy.org/account/login/";
    public final String URI_ADD_VISIT_START = "http://childhealth.maventy.org/test/patients/";
    public final String URI_ADD_PATIENT_AND_VISIT = "http://childhealth.maventy.org/test/patients/new-with-visit1";
    public final String URI_SEARCH_PATIENT = "http://childhealth.maventy.org/test/patients/search";
    **/
 
    /**
     * URIs to access maventy database. It requires the same login/password to login in the website
     * http://childhealth.maventy.org/test/patients/[PATIENT ID]/visit/new
    public final String URI_LOGIN = "http://childhealth.maventy.org/account/login/";
    public final String URI_ADD_VISIT_START = "http://childhealth.maventy.org/maventy/patients/";
    public final String URI_ADD_PATIENT_AND_VISIT = "http://childhealth.maventy.org/maventy/patients/new-with-visit1";
    public final String URI_SEARCH_PATIENT = "http://childhealth.maventy.org/maventy/patients/search";   
    **/
    
    
    public final String URI_ADD_VISIT_END = "/visit/new";
    
    public final String LOGIN_FAIL = "Please enter a correct username and password";
    public final String NO_PATIENT_FOUND = "No results found.";

    public final String PARAM_USERNAME = "username";
    public final String PARAM_PASSWORD = "password";

    public final String PARAM_NAME = "name";
    public final String PARAM_SEX = "sex";
    public final String PARAM_BIRTHDATE = "birth_date";
    public final String PARAM_RESIDENCE = "residence";
    public final String PARAM_COUNTRY = "country";
    public final String PARAM_CAREGIVER_NAME = "caregiver_name";

    public final String PARAM_EVALUATOR_NAME = "evaluator_name";
    public final String PARAM_VISIT_DATE = "visit_date";
    public final String PARAM_WEIGHT = "weight";
    public final String PARAM_HEAD_CIRCUMFERENCE = "head_circumference";
    public final String PARAM_HEIGHT = "height";
    public final String PARAM_HEIGHT_POSITION = "height_position";
    public final String PARAM_NOTES = "notes";

    public final String PARAM_POLIO_VACC_0 = "polio_vaccinations_0";
    public final String PARAM_POLIO_VACC_1 = "polio_vaccinations_1";
    public final String PARAM_POLIO_VACC_2 = "polio_vaccinations_2";
    public final String PARAM_POLIO_VACC_3 = "polio_vaccinations_3";
    public final String PARAM_POLIO_VACC_4 = "polio_vaccinations_4";
    public final String PARAM_DTC_VACC_0 = "dtc_vaccinations_0";
    public final String PARAM_DTC_VACC_1 = "dtc_vaccinations_1";
    public final String PARAM_DTC_VACC_2 = "dtc_vaccinations_2";
    public final String PARAM_HEPB_VACC_0 = "hepb_vaccinations_0";
    public final String PARAM_HEPB_VACC_1 = "hepb_vaccinations_1";
    public final String PARAM_HEPB_VACC_2 = "hepb_vaccinations_2";
    public final String PARAM_MEASLES_VACC_0 = "measles_vaccinations_0";
    public final String PARAM_MEASLES_VACC_1 = "measles_vaccinations_1";
    public final String PARAM_MEASLES_VACC_2 = "measles_vaccinations_2";
    public final String PARAM_HIB_VACC_0 = "hib_vaccinations_0";
    public final String PARAM_HIB_VACC_1 = "hib_vaccinations_1";
    public final String PARAM_BCG_VACC_0 = "bcg_vaccinations_0";
    public final String PARAM_VITA_VACC_0 = "vita_vaccinations_0";
    public final String PARAM_VITA_VACC_1 = "vita_vaccinations_1";
    public final String PARAM_VITA_VACC_2 = "vita_vaccinations_2";
    public final String PARAM_DEWORMING_VACC_0 = "deworming_vaccinations_0";						
    public final String ORGANIZATION = "organization";				

    /** This method builds a POST request with specific parameters  */
    public String buildHttpHostRequest(String Uri, String params) {
	StringBuilder myHttpHost = new StringBuilder(Uri);
	myHttpHost.append("?");
	myHttpHost.append(params);

	return myHttpHost.toString().trim();
    }

    /** This method takes a input stream and reads it to generate a String version */
    public String convertStreamToString(HttpResponse response) {
	BufferedReader is = null;
	StringBuffer sb = null;

	try {
	    is = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    sb = new StringBuffer("");

	    String line = "";
	    String NL = System.getProperty("line.separator");

	    while ((line = is.readLine()) != null) {
		sb.append(line + NL);
	    }

	    is.close();

	} catch(Exception e) {
	    Log.v("NetworkTools", "Error converting stream to string: " + e.toString());
	} finally {
	    if (is != null) {
		try {
		    is.close();
		} catch (IOException e) {
		    Log.v("NetworkTools", "IO Error converting stream to string: " + e.toString());
		}
	    }
	}
	return sb.toString();
    }

    /** This method takes a input stream and reads it to generate a String version */
    public String convertStreamToString(InputStream is) {
	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	StringBuilder sb = new StringBuilder();

	String line = null;
	try {
	    while ((line = reader.readLine()) != null) {
		sb.append(line + "\n");
	    }
	} catch (IOException e) {
	    Log.v("NetworkTools", "Error converting stream to string (receiving input InputStream): " + e.toString());
	} finally {
	    try {
		is.close();
	    } catch (IOException e) {
		Log.v("NetworkTools", "IO Error converting stream to string (receiving input InputStream): " + e.toString());
	    }
	}
	return sb.toString();
    }

    /**
     * This method receives a URI address and the parameters of the action and it builds and executes a POST request
     */
    public String executePOST(String URI, List<NameValuePair> nameValuePairs) {
	String result = new String();
	HttpPost httppost;
	HttpClient httpclient = new DefaultHttpClient();
	HttpResponse response;
	HttpEntity entity;

	try {
	    httppost = new HttpPost(URI);
	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 

	    response = httpclient.execute(httppost);      

	    entity = response.getEntity();

	    if (entity != null) {
		InputStream instream = entity.getContent();
		result = convertStreamToString(instream);

		instream.close();
		entity.consumeContent();

		return result;
	    } else {
		return "";
	    }
	} catch(Exception e) {
	    Log.v("NetworkTools", "Error excuting POST request: " + e.toString());
	    return "";
	}
    }

    /**
     * This method reads an HTML response and looks for a specific line of the HTML where
     * the patient id is defined
     * 
     * @param receives a complete html response as String
     * @return the patient identifier as String
     */
    public String getPatientIDString(String htmlWeb) {
	// We need to find this line 
	// <td><a href="/maventy/patients/PATIENT_ID/visit/new">Add visit</a></td>
	StringBuilder sb = new StringBuilder();
	Boolean found = false;

	Integer endPoint = htmlWeb.indexOf("/visit/new");
	Integer startPoint = endPoint - 1;

	while(!found) {            
	    if(htmlWeb.substring(startPoint - 1, startPoint).equals("/")) {
		found = true;
	    } else {
		startPoint = startPoint - 1;
	    }
	}
	sb.append(htmlWeb.substring(startPoint, endPoint));

	return sb.toString();
    }

    /**
     * This method sends a login POST request to ensure the authentication
     * and then send the POST request with the action needed
     */
    public String loginAndExecuteBuilder(String URI_LOGIN, String URI_ACTION , List<NameValuePair> nameValuePairsLogin, List<NameValuePair> nameValuePairsAction) {
	String result = new String();
	HttpPost httppost;

	HttpClient httpclient = new DefaultHttpClient();
	HttpResponse response, response_action;
	HttpEntity entity, entity_action;

	try {
	    httppost = new HttpPost(URI_LOGIN);
	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsLogin)); 
	    response = httpclient.execute(httppost);      

	    entity = response.getEntity();
	    entity.getContent();
	    entity.consumeContent();

	    httppost = new HttpPost(URI_ACTION);

	    // Url Encoding the POST parameters
	    try {
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsAction));
	    } catch (UnsupportedEncodingException e) {
		// writing error to Log
		Log.v("NetworkTools", "Exception executing POST: " + URI_ACTION + " Exception: " + e.toString());
	    }

	    //Log.v("Interoperabilty","URI para buscar  " + buildHttpHostRequest(URI_ACTION, convertStreamToString(httppost.getEntity().getContent())));
	    HttpPost httppost_p = new HttpPost(buildHttpHostRequest(URI_ACTION, convertStreamToString(httppost.getEntity().getContent())));

	    response_action = httpclient.execute(httppost_p);           
	    entity_action = response_action.getEntity(); 

	    if (entity_action != null) {
		result = new String();
		result = convertStreamToString(response_action);
		entity_action.consumeContent(); 
	    }
	} catch(Exception e) {
	    Log.v("NetworkTools", "Exception in POST request: " + URI_ACTION + " Exception: " + e.toString());
	    return "";
	}
	return result;
    }

    /**
     * This method sends a login POST request to ensure the authentication
     * and then send the POST request with the action needed
     */
    public String loginAndExecutePOST(String URI_LOGIN, String URI_ACTION , List<NameValuePair> nameValuePairsLogin, List<NameValuePair> nameValuePairsAction) {
	String result = new String();
	HttpPost httppost;

	HttpClient httpclient = new DefaultHttpClient();
	HttpResponse response, response_action;
	HttpEntity entity, entity_action;

	try {
	    httppost = new HttpPost(URI_LOGIN);
	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsLogin)); 

	    response = httpclient.execute(httppost);      

	    entity = response.getEntity();
	    entity.getContent();
	    entity.consumeContent();

	    httppost = new HttpPost(URI_ACTION);
	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsAction));

	    response_action = httpclient.execute(httppost);    
	    entity_action = response_action.getEntity();

	    if (entity_action != null) {
		result = new String();
		result = convertStreamToString(response_action);
		entity_action.consumeContent(); 
	    }

	} catch(Exception e) {
	    Log.v("NetworkTools", "Exception in POST request: " + URI_ACTION + " Exception: " + e.toString());
	    return "";
	}
	return result;
    }

    /** This method looks for a string within a HTML response. */
    public Boolean stringWithinResponse(String response, String string) {
	return response.indexOf(string) != -1;
    }
}
