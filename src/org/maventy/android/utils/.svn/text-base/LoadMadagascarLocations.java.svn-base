package org.maventy.android.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.util.Log;

public class LoadMadagascarLocations {
    private static ArrayList<String> newArrayList = new ArrayList<String>();

    public ArrayList<String> getArrayList() {
	return newArrayList;
    }

    public LoadMadagascarLocations() {
	String readLine = "";

	try {
	    InputStream in = Tools.getContext().getAssets().open(Constants.LOCATIONS_FILE);
	    BufferedReader  br = new BufferedReader(new InputStreamReader(in));

	    StringTokenizer st = null;
	    int lineNumber = 0, tokenNumber = 0;

	    while((readLine = br.readLine()) != null) {
		lineNumber++;
		st = new StringTokenizer(readLine, ",");

		while(st.hasMoreTokens()) {
		    tokenNumber++;
		    newArrayList.add(st.nextToken());
		}
		tokenNumber = 0;
	    } 
	}

	catch (Exception e) {
	    Log.e("LoadMadagascarLocations","Exception: " + e.toString());
	} 
    }  
}


