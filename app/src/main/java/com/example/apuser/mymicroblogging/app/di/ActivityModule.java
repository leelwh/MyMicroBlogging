package com.example.apuser.mymicroblogging.app.di;

import android.app.Activity;
import android.content.Context;

import com.example.apuser.mymicroblogging.app.di.qualifier.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mikelee on 4/25/15.
 */
@Module(library = true)
public final class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @Provides
    Context provideActivityContext() {
        return activity;
    }
}
