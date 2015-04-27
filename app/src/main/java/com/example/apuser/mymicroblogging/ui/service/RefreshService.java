package com.example.apuser.mymicroblogging.ui.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apuser on 4/27/15.
 */
public class RefreshService extends IntentService {
    private static final String TAG = RefreshService.class.getSimpleName();

    public RefreshService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreated");
    }

    // Executes on a worker thread
    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        final String username = prefs.getString("username", "");
        final String password = prefs.getString("password", "");

        // Check that username and password are not empty
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please update your username and password",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Log.d(TAG, "onStarted");

        return;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroyed");
    }

}

