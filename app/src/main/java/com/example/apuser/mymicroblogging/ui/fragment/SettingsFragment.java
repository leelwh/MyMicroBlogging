package com.example.apuser.mymicroblogging.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BasePreferenceFragment;

/**
 * Created by apuser on 4/23/15.
 */
public class SettingsFragment extends BasePreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onStart() {
        super.onStart();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        getActivity().sendBroadcast( new Intent("com.marakana.android.yamba.action.UPDATED_INTERVAL") );
    }
}
