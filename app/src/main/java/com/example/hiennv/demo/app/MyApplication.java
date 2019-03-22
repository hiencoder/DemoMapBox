package com.example.hiennv.demo.app;

import android.support.multidex.MultiDexApplication;

import com.example.hiennv.demo.R;
import com.mapbox.mapboxsdk.Mapbox;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Mapbox.getInstance(getApplicationContext(),getString(R.string.mapbox_access_token));
    }
}
