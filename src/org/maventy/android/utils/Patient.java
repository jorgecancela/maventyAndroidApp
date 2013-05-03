package org.maventy.android.utils;

import java.util.Calendar;

import org.maventy.R;
import org.maventy.android.db.PatientsDataSource;

/**
 * This class implements a Patient object
 * @author jcancela
 */
public class Patient {
    private long id;
    private String caregiver, residence, sex, name, lastname;
    private Calendar dateOfBirth;
    private String alreadyInDb, usernameInDb;

    public String getAlreadyInDb() {
	return alreadyInDb;
    }

    public Boolean getAlreadyInDbBoolean() {	
	try {	
	    if(this.alreadyInDb.equals(Constants.BOOLEAN_TRUE)) {
		return true;
	    } else {
		return false;
	    }
	} catch(Exception e) {
	    return false;
	}
    }

    public String getCaregiver() {
	return this.caregiver;
    }

    public Calendar getDateOfBirth() {	
	return this.dateOfBirth;
    }

    public long getId() {
	return id;
    }

    public String getLastName() {
	return this.lastname;
    }

    public String getName() {
	return this.name;
    }

    public String getResidence() {
	return this.residence;
    }

    public String getSex() {
	return this.sex;
    }

    public String getUsernameInDb() {
	return usernameInDb;
    }

    public void patient(long id, String caregiver, String residence,
	    String sex, String name, String lastname,
	    Calendar dateOfBirth, String alreadyInDb,  String usernameInDb) {

	this.id = id;
	this.caregiver = caregiver;
	this.residence = residence;
	this.sex = sex;
	this.name = name;
	this.lastname = lastname;
	this.dateOfBirth = dateOfBirth;
	this.alreadyInDb = alreadyInDb;
	this.usernameInDb = usernameInDb;
    }

    public void setAlreadyInDb(String alreadyInDb) {
	this.alreadyInDb = alreadyInDb;
    }

    public void setAlreadyInDbValue(String alreadyInDb) {
	PatientsDataSource datasource = new PatientsDataSource(Tools.getContext());
	datasource.open();
	datasource.setAlreadyInDbTrue(this.id);
	datasource.close();
	this.alreadyInDb = alreadyInDb;
    }

    public void setCaregiver(String caregiver) {
	this.caregiver = caregiver;
    }

    public void setDateOfBirth(String dateOfBirth) {	
	this.dateOfBirth = Tools.StringToCalendar(dateOfBirth);
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setLastName(String lastname) {
	this.lastname = lastname;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setResidence(String residence) {
	this.residence = residence;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public void setUsernameInDb(String usernameInDb) {
	this.usernameInDb = usernameInDb;
    }

    public void setUsernameInDbValue(String usernameInDb) {
	PatientsDataSource datasource = new PatientsDataSource(Tools.getContext());
	datasource.open();
	datasource.setDbUsername(this.id, usernameInDb);
	datasource.close();
	this.usernameInDb = usernameInDb;
    }

    @Override
    public String toString() {
	return name;
	//return name + " " + lastname;
    }

    public String toStringCaregiver() {
	return Tools.getStringResource(R.string.db_patient_info_caregiver_to_string) + caregiver;
    }

    public String toStringResidenceBirthdate() {
	return Tools.sexToString(Tools.stringToSex(sex)) + ", " + residence + ", " + Tools.CalendarToStringToShow(dateOfBirth);
    }

    public String toStringToSearchDb() {
	return name;
	//return name + " " + lastname;
    }
}
