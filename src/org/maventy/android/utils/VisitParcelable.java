package org.maventy.android.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class VisitParcelable implements Parcelable {
    public String sex;
    public int bYear;
    public int bMonth;
    public int bDay;
    public int vYear;
    public int vMonth;
    public int vDay;
    public Double weight;
    public Double head;
    public Double length;
    public Double bmi;
    public String position;

    public Double weight_percentile;
    public Double weight_zscore;

    public Double length_percentile;
    public Double length_zscore;

    public Double head_percentile;
    public Double head_zscore;

    public Double bmi_percentile;
    public Double bmi_zscore;

    public Double wh_percentile;
    public Double wh_zscore;

    public static final Parcelable.Creator<VisitParcelable> CREATOR = new Parcelable.Creator<VisitParcelable>() {
	@Override
	public VisitParcelable createFromParcel(Parcel in) {
	    return new VisitParcelable(in);
	}

	@Override
	public VisitParcelable[] newArray(int size) {
	    return new VisitParcelable[size];
	}
    };

    public VisitParcelable() {
	sex = null;
	bYear = 0;
	bMonth = 0;
	bDay = 0;
	vYear = 0;
	vMonth = 0;
	vDay = 0;
	weight = Double.valueOf(0);
	head  = Double.valueOf(0);
	length  = Double.valueOf(0);
	bmi  = Double.valueOf(0);
	position = null;

	weight_percentile = Double.valueOf(0);
	weight_zscore = Double.valueOf(0);
	length_percentile = Double.valueOf(0);
	length_zscore = Double.valueOf(0);
	head_percentile = Double.valueOf(0);
	head_zscore = Double.valueOf(0);
	bmi_percentile = Double.valueOf(0);
	bmi_zscore = Double.valueOf(0);
	wh_percentile = Double.valueOf(0);
	wh_zscore = Double.valueOf(0);
    }

    public VisitParcelable(Parcel in) {
	sex = in.readString();
	bYear  = in.readInt();
	bMonth = in.readInt();
	bDay = in.readInt();
	vYear = in.readInt();
	vMonth = in.readInt();
	vDay = in.readInt();
	weight = in.readDouble();
	head = in.readDouble();
	length = in.readDouble();
	bmi = in.readDouble();
	position = in.readString();

	weight_percentile = in.readDouble();
	weight_zscore = in.readDouble();
	length_percentile = in.readDouble();
	length_zscore = in.readDouble();
	head_percentile = in.readDouble();
	head_zscore = in.readDouble();
	bmi_percentile = in.readDouble();
	bmi_zscore = in.readDouble();
	wh_percentile = in.readDouble();
	wh_zscore = in.readDouble();
    }

    public VisitParcelable(String mySex, int mybYear, int mybMonth, int mybDay,
	    int myvYear, int myvMonth, int myvDay, Double myweight,
	    Double myhead, Double mylength, Double mybmi,
	    String myposition,  Double myWeight_percentile,
	    Double myWeight_zscore, Double myLength_percentile, Double myLength_zscore,
	    Double myHead_percentile, Double myHead_zscore, Double myBmi_percentile,
	    Double myBmi_zscore, Double myWh_percentile, Double myWh_zscore) {
	sex = mySex;
	bYear = mybYear;
	bMonth = mybMonth;
	bDay = mybDay;
	vYear = myvYear;
	vMonth = myvMonth;
	vDay = myvDay;
	weight = myweight;
	head  = myhead;
	length  = mylength;
	bmi = mybmi;
	position = myposition;

	weight_percentile = myWeight_percentile;
	weight_zscore = myWeight_zscore;
	length_percentile = myLength_percentile;
	length_zscore = myLength_zscore;
	head_percentile = myHead_percentile;
	head_zscore = myHead_zscore;
	bmi_percentile = myBmi_percentile;
	bmi_zscore = myBmi_zscore;
	wh_percentile = myWh_percentile;
	wh_zscore = myWh_zscore;
    }

    @Override
    public int describeContents() {
	return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
	out.writeString(sex);
	out.writeInt(bYear);
	out.writeInt(bMonth);
	out.writeInt(bDay);
	out.writeInt(vYear);
	out.writeInt(vMonth);
	out.writeInt(vDay);
	out.writeDouble(weight);
	out.writeDouble(head);
	out.writeDouble(length);
	out.writeDouble(bmi);
	out.writeString(position);

	out.writeDouble(weight_percentile);
	out.writeDouble(weight_zscore);
	out.writeDouble(length_percentile);
	out.writeDouble(length_zscore);
	out.writeDouble(head_percentile);
	out.writeDouble(head_zscore);
	out.writeDouble(bmi_percentile);
	out.writeDouble(bmi_zscore);
	out.writeDouble(wh_percentile);
	out.writeDouble(wh_zscore);

    }
}