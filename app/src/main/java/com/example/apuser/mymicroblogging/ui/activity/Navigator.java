package com.example.apuser.mymicroblogging.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.example.apuser.mymicroblogging.app.di.qualifier.ActivityContext;
import com.example.apuser.mymicroblogging.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mikelee on 4/25/15.
 */
@Singleton
public class Navigator {
    private final Context context;

    @Inject
    public Navigator(@ActivityContext Context context) {
        this.context = context;
    }

    public void openSettingsActivity() {
        Intent intent = SettingsActivity.getLaunchIntent(context);
        startActivity(intent);
    }

    public void openDetailsActivity(final long statusId) {
        Intent intent = DetailsActivity.getLaunchIntent(context, statusId);
        startActivity(intent);
    }

    public void openStatusActivity() {
        Intent intent = StatusActivity.getLaunchIntent(context);
        startActivity(intent);
    }

    private void startActivity(Intent intent) {
        context.startActivity(intent);
    }
}
