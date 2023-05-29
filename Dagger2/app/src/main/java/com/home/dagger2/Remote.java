package com.home.dagger2;

import android.util.Log;

import javax.inject.Inject;

public class Remote {

    private static final String TAG = "Remote";

    @Inject
    public Remote() {
    }


    public void setListening(Car car){
        Log.d(TAG, "Remote connecting");
    }
}
