package org.maventy.android.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.maventy.android.utils.Constants;
import org.maventy.android.utils.Tools;
import org.maventy.android.utils.VisitDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class VisitsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = { 
	    MySQLiteHelper.COLUMN_V_ID,
	    MySQLiteHelper.COLUMN_V_WEIGHT_MEASURED, 
	    MySQLiteHelper.COLUMN_V_WEIGHT_PERCENTILE, 
	    MySQLiteHelper.COLUMN_V_WEIGHT_ZSCORE, 
	    MySQLiteHelper.COLUMN_V_LENGTH_MEASURED, 
	    MySQLiteHelper.COLUMN_V_LENGTH_PERCENTILE, 
	    MySQLiteHelper.COLUMN_V_LENGTH_ZSCORE, 
	    MySQLiteHelper.COLUMN_V_HEAD_MEASURED,
	    MySQLiteHelper.COLUMN_V_HEAD_PERCENTILE,
	    MySQLiteHelper.COLUMN_V_HEAD_ZSCORE,
	    MySQLiteHelper.COLUMN_V_BMI_MEASURED,
	    MySQLiteHelper.COLUMN_V_BMI_PERCENTILE,
	    MySQLiteHelper.COLUMN_V_BMI_ZSCORE,
	    MySQLiteHelper.COLUMN_V_WH_PERCENTILE,
	    MySQLiteHelper.COLUMN_V_WH_ZSCORE,
	    MySQLiteHelper.COLUMN_V_COMMENT,
	    MySQLiteHelper.COLUMN_V_SEX,
	    MySQLiteHelper.COLUMN_V_POSITION,
	    MySQLiteHelper.COLUMN_V_DATEOFBIRTH,
	    MySQLiteHelper.COLUMN_V_DATEOFVISIT,
	    MySQLiteHelper.COLUMN_V_DATESTORED,
	    MySQLiteHelper.COLUMN_V_DATEUPLOADED, 
	    MySQLiteHelper.COLUMN_V_PATIENT_ID };

    public VisitsDataSource(Context context) {
	dbHelper = new MySQLiteHelper(context);
    }

    private Double checkNaN(Double input) {

	if (input.isNaN()) {
	    return Constants.DOUBLE_NAN_VALUE_TO_STORE;	   
	} else {
	    return input;
	}
    }

    public void close() {
	dbHelper.close();
    }

    public VisitDB createVisit(Double weight, Double weight_percentile, Double weight_zscore, 
	    Double length, Double length_percentile, Double length_zscore, 
	    Double head, Double head_percentile, Double head_zscore, 
	    Double bmi, Double bmi_percentile, Double bmi_zscore, 
	    Double wh_percentile, Double wh_zscore, 
	    String comment, String sex, String position, 
	    String birthDate, String visitDate, String storedDate, String uploadDate, 
	    int patient_id) {

	ContentValues values = new ContentValues();
	try {
	    values.put(MySQLiteHelper.COLUMN_V_WEIGHT_MEASURED, checkNaN(weight));
	    values.put(MySQLiteHelper.COLUMN_V_WEIGHT_PERCENTILE, checkNaN(weight_percentile));
	    values.put(MySQLiteHelper.COLUMN_V_WEIGHT_ZSCORE, checkNaN(weight_zscore));
	    values.put(MySQLiteHelper.COLUMN_V_LENGTH_MEASURED, checkNaN(length));
	    values.put(MySQLiteHelper.COLUMN_V_LENGTH_PERCENTILE, checkNaN(length_percentile));
	    values.put(MySQLiteHelper.COLUMN_V_LENGTH_ZSCORE, checkNaN(length_zscore));
	    values.put(MySQLiteHelper.COLUMN_V_HEAD_MEASURED, checkNaN(head));
	    values.put(MySQLiteHelper.COLUMN_V_HEAD_PERCENTILE, checkNaN(head_percentile));
	    values.put(MySQLiteHelper.COLUMN_V_HEAD_ZSCORE, checkNaN(head_zscore));
	    values.put(MySQLiteHelper.COLUMN_V_BMI_MEASURED, checkNaN(bmi));
	    values.put(MySQLiteHelper.COLUMN_V_BMI_PERCENTILE, checkNaN(bmi_percentile));
	    values.put(MySQLiteHelper.COLUMN_V_BMI_ZSCORE, checkNaN(bmi_zscore));
	    values.put(MySQLiteHelper.COLUMN_V_WH_PERCENTILE, checkNaN(wh_percentile));
	    values.put(MySQLiteHelper.COLUMN_V_WH_ZSCORE, checkNaN(wh_zscore));
	    values.put(MySQLiteHelper.COLUMN_V_COMMENT, comment);
	    values.put(MySQLiteHelper.COLUMN_V_SEX, sex);
	    values.put(MySQLiteHelper.COLUMN_V_POSITION, position);
	    values.put(MySQLiteHelper.COLUMN_V_DATEOFBIRTH, birthDate);
	    values.put(MySQLiteHelper.COLUMN_V_DATEOFVISIT, visitDate);
	    values.put(MySQLiteHelper.COLUMN_V_DATESTORED, storedDate);
	    values.put(MySQLiteHelper.COLUMN_V_DATEUPLOADED, uploadDate);
	    values.put(MySQLiteHelper.COLUMN_V_PATIENT_ID, patient_id);
	} catch (Exception e) { 
	    Log.v("VisitDataSource", "Error putting values: " + e.toString());	
	}

	VisitDB newVisit = null;
	try {
	    long insertId = database.insert(MySQLiteHelper.TABLE_VISITS, null, values);
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_VISITS, allColumns, MySQLiteHelper.COLUMN_V_ID + " = " + insertId, null, null, null, null);
	    cursor.moveToFirst();
	    newVisit = cursorToVisit(cursor);
	    cursor.close();

	} catch (Exception e) {
	    Log.v("VisitDataSource", "Error inserting data: " + e.toString());	
	}

	return newVisit;
    }

    private VisitDB cursorToVisit(Cursor cursor) {
	VisitDB visit = new VisitDB();

	visit.setID(cursor.getInt(0));
	visit.setWeight(ifNaN(cursor.getDouble(1)));
	visit.setWeightPercentile(ifNaN(cursor.getDouble(2)));
	visit.setWeightZscore(ifNaN(cursor.getDouble(3)));
	visit.setLength(ifNaN(cursor.getDouble(4)));
	visit.setLengthPercentile(ifNaN(cursor.getDouble(5)));
	visit.setLengthZscore(ifNaN(cursor.getDouble(6)));
	visit.setHead(ifNaN(cursor.getDouble(7)));
	visit.setHeadPercentile(ifNaN(cursor.getDouble(8)));
	visit.setHeadZscore(ifNaN(cursor.getDouble(9)));
	visit.setBMI(ifNaN(cursor.getDouble(10)));
	visit.setBMIPercentile(ifNaN(cursor.getDouble(11)));
	visit.setBMIZscore(ifNaN(cursor.getDouble(12)));
	visit.setWHPercentile(ifNaN(cursor.getDouble(13)));
	visit.setWHZscore(ifNaN(cursor.getDouble(14)));

	visit.setComment(cursor.getString(15));
	visit.setSex(cursor.getString(16));
	visit.setPosition(cursor.getString(17));

	visit.setBirthDate(cursor.getString(18));
	visit.setVisitDate(cursor.getString(19));
	visit.setStoredDate(cursor.getString(20));
	visit.setUploadDate(cursor.getString(21));

	visit.setPID(cursor.getInt(22));

	return visit;
    }

    public void deleteVisit(VisitDB visit) {
	long id = visit.getId();
	database.delete(MySQLiteHelper.TABLE_VISITS, MySQLiteHelper.COLUMN_V_ID
		+ " = " + id, null);
    }

    public List<VisitDB> getAllVisits() {
	List<VisitDB> visits = new ArrayList<VisitDB>();

	Cursor cursor = database.query(MySQLiteHelper.TABLE_VISITS, allColumns, null, null, null, null, null);

	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    VisitDB visit = cursorToVisit(cursor);
	    visits.add(visit);
	    cursor.moveToNext();
	}
	// Make sure to close the cursor
	cursor.close();
	return visits;
    }

    public List<VisitDB> getAllVisitsByID(int patientID) {
	List<VisitDB> visits = new ArrayList<VisitDB>();

	Cursor cursor = database.query(MySQLiteHelper.TABLE_VISITS, allColumns, MySQLiteHelper.COLUMN_V_PATIENT_ID + " = " + (long) patientID, null, null, null, null);

	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    VisitDB visit = cursorToVisit(cursor);
	    visits.add(visit);
	    cursor.moveToNext();
	}

	// Make sure to close the cursor
	cursor.close();
	return visits;
    }

    public List<VisitDB> getAllVisitsToUpload() {
	List<VisitDB> visits = new ArrayList<VisitDB>();

	Cursor cursor = database.query(MySQLiteHelper.TABLE_VISITS, 
		allColumns, 
		MySQLiteHelper.COLUMN_V_DATEUPLOADED + "=? ",
		new String[] {"null"}, 
		null, 
		null, 
		null);

	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    VisitDB visit = cursorToVisit(cursor);
	    visits.add(visit);
	    cursor.moveToNext();
	}
	// Make sure to close the cursor
	cursor.close();
	return visits;
    }

    public VisitDB getVisitById(int visitID) {
	Cursor cursor = database.query(MySQLiteHelper.TABLE_VISITS, allColumns, MySQLiteHelper.COLUMN_V_ID + " = " + (long) visitID, null, null, null, MySQLiteHelper.COLUMN_V_DATEOFVISIT + " DESC");

	cursor.moveToFirst();
	VisitDB newVisit = cursorToVisit(cursor);
	cursor.close();
	database.close();

	return newVisit;
    }

    private Double ifNaN(Double input) {

	if (input.equals(Constants.DOUBLE_NAN_VALUE_TO_STORE)) {
	    return Double.NaN;	   
	} else {
	    return input;
	}
    }

    public void open() throws SQLException {
	database = dbHelper.getWritableDatabase();
    }

    public VisitDB setUploadDate(Long id) {	
	ContentValues values = new ContentValues();

	Calendar today = Calendar.getInstance();

	values.put(MySQLiteHelper.COLUMN_V_DATEUPLOADED , Tools.CalendarToString(today));

	long insertId = database.update(MySQLiteHelper.TABLE_VISITS, values, MySQLiteHelper.COLUMN_ID + " = " + id, null);

	Cursor cursor = database.query(MySQLiteHelper.TABLE_VISITS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
	cursor.moveToFirst();
	VisitDB myVisit = cursorToVisit(cursor);
	cursor.close();

	return myVisit;
    }
}

