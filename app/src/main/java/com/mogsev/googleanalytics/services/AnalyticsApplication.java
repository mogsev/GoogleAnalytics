package com.mogsev.googleanalytics.services;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.mogsev.googleanalytics.R;

public class AnalyticsApplication extends Application {
    private static final String TAG = "AnalyticsApplication";

    private Tracker mTracker;

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        //Log.i(TAG, "start getDefaultTracker()");
        if (mTracker == null) {
            //Log.i(TAG, "Create Tracker");
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

}