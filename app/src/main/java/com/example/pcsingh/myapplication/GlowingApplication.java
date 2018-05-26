package com.example.pcsingh.myapplication;

import android.app.Application;

public class GlowingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageUtil.init(this);
    }
}
