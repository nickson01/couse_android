package com.walkingwithdev.nick.helloworld;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by Nick on 5/22/2016 AD.
 */
public class BundleSavesState extends View.BaseSavedState {

    private Bundle bundle;

    public Bundle getBundle() { return bundle;}

    public void setBundle(Bundle bundle) {this.bundle = bundle;}

    public BundleSavesState(Parcel source) {
        super(source);
        bundle = source.readBundle();
    }

    public BundleSavesState(Parcelable superState) {super(superState);}

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeBundle(bundle);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new BundleSavesState(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new BundleSavesState[size];
        }
    };
}
