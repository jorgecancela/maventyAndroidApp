package org.maventy.android.db;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_PATIENTS = "patients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CAREGIVER = "caregiver";
    public static final String COLUMN_RESIDENCE = "residence";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_DATEOFBIRTH = "dateOfBirth";
    public static final String COLUMN_ALREADY_IN_DB = "alreadyInDatabase";
    public static final String COLUMN_USERNAME_IN_DB = "usernameInDb";

    public static final String TABLE_SETTINGS = "settings";
    public static final String COLUMN_S_ID = "_id";
    public static final String COLUMN_S_EMAIL = "email";
    public static final String COLUMN_S_USERNAME = "username";
    public static final String COLUMN_S_PASSWORD = "password";
    public static final String COLUMN_S_LANGUAGE = "language";
    public static final String COLUMN_S_DATESTORED = "dateStored";

    public static final String TABLE_VISITS = "visits";
    public static final String COLUMN_V_ID = "_id";
    public static final String COLUMN_V_WEIGHT_MEASURED = "weight_measured";
    public static final String COLUMN_V_WEIGHT_PERCENTILE = "weight_percentile";
    public static final String COLUMN_V_WEIGHT_ZSCORE = "weight_zscore";
    public static final String COLUMN_V_LENGTH_MEASURED = "length_measured";
    public static final String COLUMN_V_LENGTH_PERCENTILE = "length_percentile";
    public static final String COLUMN_V_LENGTH_ZSCORE = "length_zscore";
    public static final String COLUMN_V_HEAD_MEASURED = "head_measured";
    public static final String COLUMN_V_HEAD_PERCENTILE = "head_percentile";
    public static final String COLUMN_V_HEAD_ZSCORE = "head_zscore";
    public static final String COLUMN_V_BMI_MEASURED = "bmi_measured";
    public static final String COLUMN_V_BMI_PERCENTILE = "bmi_percentile";
    public static final String COLUMN_V_BMI_ZSCORE = "bmi_zscore";
    public static final String COLUMN_V_WH_PERCENTILE = "wh_percentile";
    public static final String COLUMN_V_WH_ZSCORE = "wh_zscore";
    public static final String COLUMN_V_COMMENT = "comment";
    public static final String COLUMN_V_SEX = "sex";
    public static final String COLUMN_V_POSITION = "position";
    public static final String COLUMN_V_DATEOFBIRTH = "dateOfBirth";
    public static final String COLUMN_V_DATEOFVISIT = "dateOfVisit";
    public static final String COLUMN_V_DATESTORED = "dateStored";
    public static final String COLUMN_V_DATEUPLOADED = "dateUploaded";
    public static final String COLUMN_V_PATIENT_ID = "patient_id";
    public static final String COLUMN_V_FOREIGN_KEY = "FOREIGN KEY(patient_id) REFERENCES patient(_id)";

    public static final String DATABASE_NAME = "maventy.db";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_PATIENTS = "create table "
	+ TABLE_PATIENTS + "( " 
	+ COLUMN_ID + " integer primary key autoincrement, " 
	+ COLUMN_CAREGIVER + " text not null," 
	+ COLUMN_RESIDENCE + " text not null," 
	+ COLUMN_SEX + " text not null," 
	+ COLUMN_NAME + " text not null," 
	+ COLUMN_LASTNAME + " text not null," 
	+ COLUMN_DATEOFBIRTH + " text not null," 
	+ COLUMN_ALREADY_IN_DB + " text,"
	+ COLUMN_USERNAME_IN_DB + " text"
	+ ");";

    private static final String DATABASE_CREATE_SETTINGS = "create table "
	+ TABLE_SETTINGS + "( " 
	+ COLUMN_S_ID + " integer primary key autoincrement, " 
	+ COLUMN_S_EMAIL + " text," 
	+ COLUMN_S_USERNAME + " text," 
	+ COLUMN_S_PASSWORD + " text," 
	+ COLUMN_S_LANGUAGE + " text," 
	+ COLUMN_S_DATESTORED + " text"
	+ ");";

    private static final String DATABASE_CREATE_VISITS = "create table "
	+ TABLE_VISITS + "( " 
	+ COLUMN_V_ID + " integer primary key autoincrement, " 
	+ COLUMN_V_WEIGHT_MEASURED + " numeric," 
	+ COLUMN_V_WEIGHT_PERCENTILE + " numeric," 
	+ COLUMN_V_WEIGHT_ZSCORE + " numeric,"  
	+ COLUMN_V_LENGTH_MEASURED + " numeric," 
	+ COLUMN_V_LENGTH_PERCENTILE + " numeric," 
	+ COLUMN_V_LENGTH_ZSCORE + " numeric," 
	+ COLUMN_V_HEAD_MEASURED + " numeric,"  
	+ COLUMN_V_HEAD_PERCENTILE + " numeric," 
	+ COLUMN_V_HEAD_ZSCORE + " numeric," 
	+ COLUMN_V_BMI_MEASURED + " numeric," 
	+ COLUMN_V_BMI_PERCENTILE + " numeric,"  
	+ COLUMN_V_BMI_ZSCORE + " numeric," 
	+ COLUMN_V_WH_PERCENTILE + " numeric," 
	+ COLUMN_V_WH_ZSCORE + " numeric,"  
	+ COLUMN_V_COMMENT + " text not null," 
	+ COLUMN_V_SEX + " text not null," 
	+ COLUMN_V_POSITION + " text not null," 
	+ COLUMN_V_DATEOFBIRTH + " text not null," 
	+ COLUMN_V_DATEOFVISIT + " text not null," 
	+ COLUMN_V_DATESTORED + " text not null," 
	+ COLUMN_V_DATEUPLOADED + " text," 
	+ COLUMN_V_PATIENT_ID + " integer," 
	+ COLUMN_V_FOREIGN_KEY
	+ ");";

    /** Constructor */
    public MySQLiteHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);	
    }	

    /**  Checks if a database already exists   */
    public boolean databaseExist() {
	File dbFile = new File(DATABASE_NAME);
	return dbFile.exists();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

	try {
	    database.execSQL(DATABASE_CREATE_PATIENTS);	
	} catch(Exception e) {
	    Log.v("MySQLiteHelper", "Error creating PATIENTS database:" + e.toString());
	}
	try {
	    database.execSQL(DATABASE_CREATE_VISITS);
	} catch(Exception e) {
	    Log.v("MySQLiteHelper", "Error creating VISITS database:" + e.toString());
	}
	try {
	    database.execSQL(DATABASE_CREATE_SETTINGS);
	} catch(Exception e) {
	    Log.v("MySQLiteHelper", "Error creating SETTINGS database:" + e.toString());
	}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

