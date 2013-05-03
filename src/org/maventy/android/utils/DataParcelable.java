package org.maventy.android.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class DataParcelable implements Parcelable {
    public int intParcelable;

    public static final Parcelable.Creator<DataParcelable> CREATOR = new Parcelable.Creator<DataParcelable>() {
	@Override
	public DataParcelable createFromParcel(Parcel in) {
	    return new DataParcelable(in);
	}

	@Override
	public DataParcelable[] newArray(int size) {
	    return new DataParcelable[size];
	}
    };

    public DataParcelable() {
	intParcelable = 0;
    }

    public DataParcelable(int intParcelable) {
	this.intParcelable = intParcelable;
    }

    public DataParcelable(Parcel in) {
	this.intParcelable = in.readInt();
    }

    @Override
    public int describeContents() {
	return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
	out.writeInt(intParcelable);
    }
}