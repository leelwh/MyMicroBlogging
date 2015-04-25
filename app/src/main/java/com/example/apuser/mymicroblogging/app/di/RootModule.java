package com.example.apuser.mymicroblogging.app.di;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.example.apuser.mymicroblogging.app.MyMicroBloggingApplication;
import com.example.apuser.mymicroblogging.ui.service.RefreshService;
import com.example.apuser.mymicroblogging.ui.activity.DetailsActivity;
import com.example.apuser.mymicroblogging.ui.activity.MainActivity;
import com.example.apuser.mymicroblogging.ui.activity.SettingsActivity;
import com.example.apuser.mymicroblogging.ui.activity.StatusActivity;
import com.example.apuser.mymicroblogging.ui.fragment.DetailsFragment;
import com.example.apuser.mymicroblogging.ui.fragment.SettingsFragment;
import com.example.apuser.mymicroblogging.ui.fragment.StatusFragment;
import com.example.apuser.mymicroblogging.ui.fragment.TimelineFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by apuser on 4/23/15.
 */
@Module(
        includes = {
                DomainModule.class,
        },
        injects = {
                MyMicroBloggingApplication.class,
        },
        library = true
)
public class RootModule {
    private final Context context;

    public RootModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return MyMicroBloggingApplication.get(context);
    }

    @Provides
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }
}
