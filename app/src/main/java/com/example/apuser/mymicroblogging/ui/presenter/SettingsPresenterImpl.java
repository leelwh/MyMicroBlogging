package com.example.apuser.mymicroblogging.ui.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.apuser.mymicroblogging.app.di.qualifier.ActivityContext;
import com.example.apuser.mymicroblogging.ui.view.SettingsView;
import com.example.apuser.mymicroblogging.ui.view.View;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by apuser on 4/23/15.
 */
@Singleton
public class SettingsPresenterImpl implements SettingsPresenter {
    private Context context;
    private SettingsView settingsView;

    @Inject
    public SettingsPresenterImpl(@ActivityContext Context context) {
        this.context = context;
    }

    @Override
    public void handlePrefChanged() {
        context.sendBroadcast(new Intent("com.example.apuser.mymicroblogging.action.UPDATED_INTERVAL"));
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void setView(View view) {
        settingsView = (SettingsView) view;
    }
}
