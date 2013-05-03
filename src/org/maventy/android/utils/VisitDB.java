package org.maventy.android.utils;

import java.util.Calendar;

import org.maventy.R;
import org.maventy.android.db.PatientsDataSource;
import org.maventy.android.db.VisitsDataSource;
import org.maventy.anthro.Anthro.Measured;
import org.maventy.anthro.Anthro.Sex;

public class VisitDB {
    private Integer id;

    private Sex sex;
    private Measured position;

    private Calendar birthDate; 
    private Calendar visitDate;
    private Calendar storedDate;
    private Calendar uploadDate;

    private Double weight;
    private Double head;
    private Double length;;
    private Double weight_percentile;
    private Double weight_zscore;
    private Double length_percentile;
    private Double length_zscore;
    private Double head_percentile;
    private Double head_zscore;
    private Double bmi;
    private Double bmi_percentile;
    private Double bmi_zscore;
    private Double wh_percentile;
    private Double wh_zscore;

    private String comment;
    private Integer patient_id;

    public Calendar getBirthDate() {
	return this.birthDate;
    }

    public String getBirthDateString() {
	return Tools.CalendarToString(this.birthDate);
    }

    public Double getBMI() {
	return this.bmi;
    }

    public Double getBMIPercentile() {
	return this.bmi_percentile;
    } 

    public Double getBMIZscore() {
	return this.bmi_zscore;
    }

    public String getComment() {
	if (this.comment == null) {
	    return "";
	} else {
	    return this.comment;
	}
    }

    public Double getHead() {
	return this.head;
    }

    public Double getHeadPercentile() {
	return this.head_percentile;
    }

    public Double getHeadZscore() {
	return this.head_zscore;
    }

    public long getId() {
	return id;
    }

    public Double getLength() {
	return this.length;
    }

    public Double getLengthPercentile() {
	return this.length_percentile;
    }

    public Double getLengthZscore() {
	return this.length_zscore;
    }

    public Patient getPatient() {
	PatientsDataSource database = new PatientsDataSource(Tools.getContext());
	database.open();

	Patient myPatient = database.getPatientById(this.patient_id);
	database.close();
	return myPatient;
    }

    public Integer getPID() {
	return this.patient_id;   	    
    }

    public Measured getPosition() {
	return this.position;
    }

    public Sex getSex() {
	return this.sex;
    }

    public Calendar getStoredDate() {
	return this.storedDate;
    }

    public String getStoredDateString() {
	return Tools.CalendarToString(this.storedDate);
    }

    public Calendar getUploadDate() {
	return this.uploadDate;
    }

    public String getUploadDateString() {
	return Tools.CalendarToString(this.uploadDate);
    }

    public Calendar getVisitDate() {	
	return this.visitDate;
    }

    public String getVisitDateString() {	
	return Tools.CalendarToString(this.visitDate);
    }

    public Double getWeight() {
	return this.weight;
    }

    public Double getWeightPercentile() {
	return this.weight_percentile;
    }

    public Double getWeightZscore() {
	return this.weight_zscore;
    }

    public Double getWHPercentile() {
	return this.wh_percentile;
    }

    public Double getWHZscore() {
	return this.wh_zscore;
    }

    public void setBirthDate(Calendar birthDate) {
	this.birthDate = birthDate;  
    }

    public void setBirthDate(String birthDate) {
	this.birthDate = Tools.StringToCalendar(birthDate);  
    }

    public void setBMI(Double bmi) {
	this.bmi = bmi;
    }

    public void setBMIPercentile(Double bmi_percentile) {
	this.bmi_percentile = bmi_percentile;
    }

    public void setBMIZscore(Double bmi_zscore) {
	this.bmi_zscore = bmi_zscore;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public void setHead(Double head) {
	this.head = head;
    }

    public void setHeadPercentile(Double head_percentile) {
	this.head_percentile = head_percentile;
    }

    public void setHeadZscore(Double head_zscore) {
	this.head_zscore = head_zscore;
    }

    public void setID(Integer id) {
	this.id = id;   	    
    }

    public void setLength(Double length) {
	this.length = length;
    }

    public void setLengthPercentile(Double length_percentile) {
	this.length_percentile = length_percentile;
    }

    public void setLengthZscore(Double length_zscore) {
	this.length_zscore = length_zscore;
    }

    public void setPID(Integer pid) {
	this.patient_id = pid;   	    
    }

    public void setPosition(Measured position) {
	this.position = position;
    }

    public void setPosition(String position) {
	if(position.equals(Measured.STANDING.toString())) {
	    this.position = Measured.STANDING;
	} else {
	    this.position = Measured.RECUMBENT;
	}
    }

    public void setSex(Sex sex) {
	this.sex = sex;
    }

    public void setSex(String sex) {
	if(sex.equals(Sex.MALE.toString())) {
	    this.sex = Sex.MALE;
	} else {
	    this.sex = Sex.FEMALE;
	}
    }

    public void setStoredDate(Calendar storedDate) {
	this.storedDate = storedDate; 
    }

    public void setStoredDate(String storedDate) {
	this.storedDate = Tools.StringToCalendar(storedDate); 
    }

    public void setUploadDate(Calendar uploadDate) {
	this.uploadDate = uploadDate;  
    }

    public void setUploadDate(String uploadDate) {
	this.uploadDate = Tools.StringToCalendar(uploadDate);  
    }

    public void setUploadDateToday() {
	VisitsDataSource datasource = new VisitsDataSource(Tools.getContext());
	datasource.open();
	datasource.setUploadDate(Long.valueOf(this.id));
	datasource.close();
	this.uploadDate = Calendar.getInstance();
    }

    public void setVisitDate(Calendar visitDate) {
	this.visitDate = visitDate; 
    }

    public void setVisitDate(String visitDate) {
	this.visitDate = Tools.StringToCalendar(visitDate);  
    }

    public void setWeight(Double weight) {
	this.weight = weight;
    }

    public void setWeightPercentile(Double weight_percentile) {
	this.weight_percentile = weight_percentile;
    }

    public void setWeightZscore(Double weight_zscore) {
	this.weight_zscore = weight_zscore;
    }

    public void setWHPercentile(Double wh_percentile) {
	this.wh_percentile = wh_percentile;
    }

    public void setWHZscore(Double wh_zscore) {
	this.wh_zscore = wh_zscore;  	    
    }

    public Boolean hasRaisedAnAlarm() {
	
	if ((this.bmi_percentile > Constants.OVERWEIGHT_THRESHOLD) || (this.bmi_percentile < Constants.MALNOURISED_THRESHOLD) || (this.bmi_percentile.isNaN()) ||
		(this.head_percentile > Constants.OVERWEIGHT_THRESHOLD) || (this.head_percentile < Constants.MALNOURISED_THRESHOLD) || (this.head_percentile.isNaN()) ||
		(this.length_percentile > Constants.OVERWEIGHT_THRESHOLD) || (this.length_percentile < Constants.MALNOURISED_THRESHOLD) || (this.length_percentile.isNaN()) ||
		(this.wh_percentile > Constants.OVERWEIGHT_THRESHOLD) || (this.wh_percentile < Constants.MALNOURISED_THRESHOLD) || (this.wh_percentile.isNaN()) ||
		(this.weight_percentile > Constants.OVERWEIGHT_THRESHOLD) || (this.weight_percentile < Constants.MALNOURISED_THRESHOLD) || (this.weight_percentile.isNaN())) {

	    return true;
	} else {
	    return false;
	}
    }
    
    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
	if(hasRaisedAnAlarm()){
	    return Tools.getStringResource(R.string.visit_to_string_text) + " " + Tools.CalendarToStringToShow(this.visitDate) + " " + Tools.getStringResource(R.string.visit_to_string_alarm);
	} else {
	    return Tools.getStringResource(R.string.visit_to_string_text) + " " + Tools.CalendarToStringToShow(this.visitDate);
	}
    }

    public void visit(Integer id, Sex sex, 
	    Calendar birthDate, Calendar visitDate, Calendar storedDate, Calendar uploadDate,
	    Double weight, Double head, Double length, Measured position, 
	    Double weight_percentile, Double weight_zscore, 
	    Double length_percentile, Double length_zscore,
	    Double head_percentile, Double head_zscore, 
	    Double bmi, Double bmi_percentile, Double bmi_zscore, 
	    Double wh_percentile, Double wh_zscore, 
	    String comment, Integer patient_id) {
	this.id = id;
	this.sex = sex;
	this.position = position;
	this.birthDate = birthDate;
	this.visitDate = visitDate;
	this.storedDate = storedDate;
	this.uploadDate = uploadDate;
	this.weight = weight;
	this.head = head;
	this.length = length;
	this.weight_percentile = weight_percentile;
	this.weight_zscore = weight_zscore;
	this.length_percentile = length_percentile;
	this.length_zscore = length_zscore;
	this.head_percentile = head_percentile;
	this.head_zscore = head_zscore;
	this.bmi = bmi;
	this.bmi_percentile = bmi_percentile;
	this.bmi_zscore = bmi_zscore;
	this.wh_percentile = wh_percentile;
	this.wh_zscore = wh_zscore;
	this.comment = comment;
	this.patient_id = patient_id;
    }
}
