package com.example.apuser.mymicroblogging.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BasePreferenceFragment;
import com.example.apuser.mymicroblogging.ui.presenter.SettingsPresenterImpl;
import com.example.apuser.mymicroblogging.ui.view.SettingsView;

import javax.inject.Inject;

/**
 * Created by apuser on 4/23/15.
 */
public class SettingsFragment extends BasePreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener, SettingsView {
    @Inject SettingsPresenterImpl settingsPresenter;
    @Inject SharedPreferences prefs;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingsPresenter.setView(this);
        settingsPresenter.initialize();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onStart() {
        super.onStart();
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
        settingsPresenter.handlePrefChanged();
    }
}
