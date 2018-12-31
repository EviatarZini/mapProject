package com.example.mapsappfirst.ui;

import android.app.Application;

public class NearByPlacesApplication extends Application {

    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication= this;

    }

    public static Application getApplication () {

        return mApplication;
    }
}
