package com.dpavlovsky.task4;

import android.os.Parcel;
import android.os.Parcelable;

public class FuelRec implements Parcelable {
	
	private String		mDate;
	private String 	    mFull;
	private String 		mFuelType;
	private String 		mDescr;
	private String		mVolume;
	
	public FuelRec() {
		mDate		= "";
		mFull 		= "";
		mFuelType	= "";
		mDescr		= "";
		mVolume		= "";
	}
	
	public void set(String dat, String full, String fuelType, String descr, String vol) {
		mDate		= dat;
		mFull 		= full;
		mFuelType	= fuelType;
		mDescr		= descr;
		mVolume		= vol;
	}
	
	public String[] get() {
		String arg[] = new String[5];
		
		arg[0]	= mDate;
		arg[1] 	= mFull; 
		arg[2]	= mFuelType;
		arg[3]	= mDescr; 
		arg[4]	= mVolume;
		
		return arg;
	}

	public int describeContents() {
		return 0;
	}
	
	@Override
	public String toString() {
		String str="";
		if(mFuelType=="D") {
			str = "Дизель";
		}
		else if (mFuelType=="B") {
			str = "Бензин";
		}
		return (mDate + "\n" + str);
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mDate);
		dest.writeString(mFull);
		dest.writeString(mFuelType);
		dest.writeString(mDescr);
		dest.writeString(mVolume);
	}
	
	public FuelRec(Parcel source) {
		mDate = source.readString();
		mFull = source.readString();
		mFuelType = source.readString();
		mDescr = source.readString();
		mVolume		 = source.readString();
	}
	
	public static final Parcelable.Creator<FuelRec> CREATOR = new Parcelable.Creator<FuelRec>() {
		public FuelRec createFromParcel(Parcel source) {
			return new FuelRec(source);
		}

		public FuelRec[] newArray(int size) {
			return new FuelRec[size];
		}
	};
	
}