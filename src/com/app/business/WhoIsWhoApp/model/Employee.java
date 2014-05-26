package com.app.business.WhoIsWhoApp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {

    private String mName;
    private String mTitle;
    private String mPhotoUrl;
    private String mBiography;

    public Employee(String mName, String mTitle, String mPhotoUrl, String mBiography) {
        this.mName = mName;
        this.mTitle = mTitle;
        this.mPhotoUrl = mPhotoUrl;
        this.mBiography = mBiography;
    }

    private Employee(Parcel in) {
        mName = in.readString();
        mTitle = in.readString();
        mPhotoUrl = in.readString();
        mBiography = in.readString();
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPhotoUrl() {
        return mPhotoUrl;
    }

    public void setmPhotoUrl(String mPhotoUrl) {
        this.mPhotoUrl = mPhotoUrl;
    }

    public String getmBiography() {
        return mBiography;
    }

    public void setmBiography(String mBiography) {
        this.mBiography = mBiography;
    }


    public static final Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mTitle);
        dest.writeString(mPhotoUrl);
        dest.writeString(mBiography);
    }
}
