package com.mogsev.googleanalytics.services;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.mogsev.googleanalytics.R;

import java.util.HashMap;
import java.util.Map;

public class AnalyticsTrackers {

    public enum Target {
        APP
    }

    private static AnalyticsTrackers analyticsTrackers;

    public static synchronized void initialize(Context context) {
        if (analyticsTrackers != null) {
            throw new IllegalStateException("Extra call to initialize analytics trackers");
        }

        analyticsTrackers = new AnalyticsTrackers(context);
    }

    public static synchronized AnalyticsTrackers getInstance() {
        if (analyticsTrackers == null) {
            throw new IllegalStateException("Call initialize() before getInstance()");
        }

        return analyticsTrackers;
    }

    private final Map<Target, Tracker> mTrackers = new HashMap<Target, Tracker>();
    private final Context mContext;

    /**
     * Don't instantiate directly - use {@link #getInstance()} instead.
     */
    private AnalyticsTrackers(Context context) {
        mContext = context.getApplicationContext();
    }

    public synchronized Tracker get(Target target) {
        if (!mTrackers.containsKey(target)) {
            Tracker tracker;
            switch (target) {
                case APP:
                    tracker = GoogleAnalytics.getInstance(mContext).newTracker(R.xml.app_tracker);
                    break;
                default:
                    throw new IllegalArgumentException("Unhandled analytics target " + target);
            }
            mTrackers.put(target, tracker);
        }

        return mTrackers.get(target);
    }
}
