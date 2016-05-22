package com.walkingwithdev.nick.helloworld;

import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;

/**
 * Created by Nick on 5/22/2016 AD.
 */
public class CustomViewSavedState extends Preference.BaseSavedState {

    private boolean blue ;

    public boolean isBlue() {
        return blue;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }

    public CustomViewSavedState(Parcel source) {
        super(source);
        blue = source.readInt() == 1;
    }

    public CustomViewSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(blue ? 1:0);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new CustomViewSavedState(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new  CustomViewSavedState[0];
        }
    };
}
