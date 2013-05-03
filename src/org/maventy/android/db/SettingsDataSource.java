package org.maventy.android.db;

import java.util.ArrayList;
import java.util.List;

import org.maventy.R;
import org.maventy.android.utils.SettingsDB;
import org.maventy.android.utils.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SettingsDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {
	    MySQLiteHelper.COLUMN_S_ID,
	    MySQLiteHelper.COLUMN_S_EMAIL, 
	    MySQLiteHelper.COLUMN_S_USERNAME,
	    MySQLiteHelper.COLUMN_S_PASSWORD,
	    MySQLiteHelper.COLUMN_S_LANGUAGE,
	    MySQLiteHelper.COLUMN_S_DATESTORED};

    public SettingsDataSource(Context context) {

	try {
	    dbHelper = new MySQLiteHelper(context);
	} catch (Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "SettingsDataSource", "Exception SettingsDataSource: " + e.toString());
	}
    }

    public void close() {	
	try {
	    dbHelper.close();
	} catch (Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "SettingsDataSource", "Error closing Settings datasource: " + e.toString());
	}
    }

    public SettingsDB createSettings(String email, String username, String password, 
	    String language, String dateStored) {

	SettingsDB newSettings = null;
	ContentValues values = new ContentValues();

	values.put(MySQLiteHelper.COLUMN_S_EMAIL, email);
	values.put(MySQLiteHelper.COLUMN_S_USERNAME, username);
	values.put(MySQLiteHelper.COLUMN_S_PASSWORD, password);
	values.put(MySQLiteHelper.COLUMN_S_LANGUAGE, language);
	values.put(MySQLiteHelper.COLUMN_S_DATESTORED, dateStored);

	//database  = myTool.getContext().openOrCreateDatabase("maventy.db", Context.MODE_PRIVATE, null);

	try {

	    long insertId = database.insertOrThrow(MySQLiteHelper.TABLE_SETTINGS, null, values);

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_SETTINGS, allColumns, MySQLiteHelper.COLUMN_S_ID + " = " + insertId, null, null, null, null);
	    cursor.moveToFirst();
	    newSettings = cursorToSettings(cursor);
	    cursor.close();

	    database.close();

	} catch(Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "SettingsDataSource", "Exception opening database: " + e.toString());
	}

	return newSettings;
    }

    private SettingsDB cursorToSettings(Cursor cursor) {
	SettingsDB setting = new SettingsDB();

	try {
	    setting.setId(cursor.getInt(0));
	    setting.setEmail(cursor.getString(1));
	    setting.setUsername(cursor.getString(2));
	    setting.setPassword(cursor.getString(3));
	    setting.setLanguage(cursor.getString(4));
	    setting.setDateStored(cursor.getString(5));
	} catch (Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "SettingsDataSource", "Error cursorToSettings: " + e.toString());
	}
	return setting;
    }

    public void deleteSettings(SettingsDB setting) {
	long id = setting.getId();
	database.delete(MySQLiteHelper.TABLE_SETTINGS, MySQLiteHelper.COLUMN_ID
		+ " = " + id, null);
    }

    public List<SettingsDB> getAllSetting() {
	List<SettingsDB> setting = new ArrayList<SettingsDB>();

	try {
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_SETTINGS, allColumns, null, null, null, null, MySQLiteHelper.COLUMN_S_DATESTORED + "ASC");

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
		SettingsDB set = cursorToSettings(cursor);
		setting.add(set);
		cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	} catch (Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "SettingsDataSource", "Error getAllSettings: " + e.toString());
	}
	return setting;
    }

    public SettingsDB getLastSettings() {
	Cursor cursor;
	SettingsDB newSet = new SettingsDB();

	try {
	    cursor = database.query(MySQLiteHelper.TABLE_SETTINGS, allColumns, null, null, null, null, MySQLiteHelper.COLUMN_S_ID + " DESC");

	    if(cursor.getCount() == 0) {
		cursor.close();
		return newSet;  
	    } else {
		cursor.moveToFirst();
		newSet = cursorToSettings(cursor);
		cursor.close();
		return newSet;
	    }
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "SettingsDataSource", "Exception getLastSettings: " + e.toString());
	    return newSet;
	}

    }

    public Boolean isOpen() {
	return database.isOpen();
    }

    public Boolean isReadOnly() {
	return database.isReadOnly();
    }

    public void open() throws SQLException {
	try {
	    database = dbHelper.getWritableDatabase();
	} catch(Exception e) {
	    Tools.showToastMessageAndLog(Tools.getContext(), Tools.getStringResource(R.string.unknown_error),
		    "SettingsDataSource", "Exception opening database: " + e.toString());
	}
    }
}
