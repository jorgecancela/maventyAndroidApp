package org.maventy.android.utils;

/**
 * This class contains all the constants used within the the application  
 * except Constants values related to the Interoperability package
 * @author jcancela
 */
public class Constants {
    //SQLite3 does not support NaN as valid value
    public static final Double DOUBLE_NAN_VALUE_TO_STORE = Double.valueOf(9999999); 

    public static final String DATE_FORMAT_TO_STORE = "yyyy-MM-dd";
    public static final String DATE_FORMAT_TO_SHOW = "yyyy-MM-dd";
    public static final String DATE_FORMAT_TO_INTEROPERABILITY = "MM/dd/yyyy";
    
    public static final String DOUBLE_FORMAT_TO_PRINT = "%.2f";
    
    public static final String PATIENT_ID_PARCEL_STRING = "patientID";
    public static final String VISIT_ID_PARCEL_STRING = "visitID";
    
    public static final String PATIENT_PARCEL_STRING = "myPatient";
    public static final String VISIT_PARCEL_STRING = "myVisit";

    public static final int OVERWEIGHT_THRESHOLD = 90;
    public static final int MALNOURISED_THRESHOLD = 10;
        
    // The min/max weight for a child, in kg. 
    public static final Double WEIGHT_MINIMUM_VALUE = Double.valueOf(0.9);
    public static final Double WEIGHT_MAXIMUM_VALUE = Double.valueOf(58);
    
    // The min/max length/height for a child, in cm. 
    public static final Double LENGTH_MINIMUM_VALUE = Double.valueOf(38);
    public static final Double LENGTH_MAXIMUM_VALUE = Double.valueOf(150);
    
    // The min/max Head Circunferences values for a child, in cm. 
    public static final Double HEAD_MINIMUM_VALUE = Double.valueOf(25);
    public static final Double HEAD_MAXIMUM_VALUE = Double.valueOf(64);
     
    public static final String DOUBLE_INPUT_PATTERN_OPTION_1 = "\\b(\\d+)";
    public static final String DOUBLE_INPUT_PATTERN_OPTION_2 = "\\b(\\d+)\\.\\d+\\b";
    
    public static final String LANGUAGE_ES = "es";
    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_FR = "fr";
    public static final String LANGUAGE_MG = "mg";
    public static final String LANGUAGE_DEFAULT = "en";
    
    public static final String LESS_THAN_ONE_PERCENT = "<1%";
    public static final String MORE_THAN_99_PERCENT = ">99%";
    public static final String PERCENTAGE = "%";
    
    public static final String BOOLEAN_TRUE = "true";
    public static final String BOOLEAN_FALSE = "false";
    
    public static final String COUNTRY_MADAGASCAR = "Madagascar";
    
    public static final String DATABASE_NAME = "maventy.db";
    public static final int DATABASE_VERSION = 1;
    
    public static final int OPTIONS_SHOWN_IN_LOCATIONS_AUTOCOMPLETE_EDITTEXT = 3;
    public static final String LOCATIONS_FILE = "MG_LARGE.csv";
}
