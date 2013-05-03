package org.maventy.android.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SettingsDB {
    private long id;
    private String email;
    private String username;
    private String password;
    private String language;
    private Date dateStored;

    public String getDateStored() {		
	SimpleDateFormat textFormat = new SimpleDateFormat(Constants.DATE_FORMAT_TO_STORE);

	return textFormat.format(this.dateStored);
    }

    public String getEmail() {
	return this.email;
    }

    public long getId() {
	return id;
    }

    public String getLanguage() {
	return this.language;
    }

    public String getPassword() {
	return this.password;
    }

    public String getUsername() {
	return this.username;
    }

    public void setDateStored(String dateStored) {		
	SimpleDateFormat textFormat = new SimpleDateFormat(Constants.DATE_FORMAT_TO_STORE);

	try {
	    this.dateStored = textFormat.parse(dateStored);
	} catch (Exception ex) {
	    this.dateStored = Calendar.getInstance().getTime();
	}
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setLanguage(String language) {
	this.language = language;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void settings(long id, String email, String username,
	    String password, String language, Date dateStored) {

	this.id = id;
	this.email = email;
	this.username = username;
	this.password = language;
	this.dateStored = dateStored;
    }

    public void setUsername(String username) {
	this.username = username;
    }
}
