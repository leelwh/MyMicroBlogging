package com.example.apuser.mymicroblogging.ui;

import com.example.apuser.mymicroblogging.ui.activity.DetailsActivity;
import com.example.apuser.mymicroblogging.ui.activity.MainActivity;
import com.example.apuser.mymicroblogging.ui.activity.SettingsActivity;
import com.example.apuser.mymicroblogging.ui.activity.StatusActivity;
import com.example.apuser.mymicroblogging.ui.fragment.DetailsFragment;
import com.example.apuser.mymicroblogging.ui.fragment.SettingsFragment;
import com.example.apuser.mymicroblogging.ui.fragment.StatusFragment;
import com.example.apuser.mymicroblogging.ui.fragment.TimelineFragment;
import com.example.apuser.mymicroblogging.ui.service.RefreshService;

import dagger.Module;

/**
 * Created by mikelee on 4/25/15.
 */
@Module(complete = false,
        injects = {
                MainActivity.class,
                DetailsActivity.class,
                SettingsActivity.class,
                StatusActivity.class,
                TimelineFragment.class,
                StatusFragment.class,
                SettingsFragment.class,
                DetailsFragment.class,
        })
public class MyMicroBloggingUIModule {
}
