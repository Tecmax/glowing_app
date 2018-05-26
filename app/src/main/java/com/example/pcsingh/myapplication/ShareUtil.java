package com.example.pcsingh.myapplication;

import android.util.Log;

public class ShareUtil {
    private static final String TAG = ShareUtil.class.getSimpleName();

    public static void shareData(String imageLink, String caption) {
        Log.d(TAG, "imageLink : " + imageLink);
        Log.d(TAG, "caption : " + caption);
    }
}
