package com.example.apuser.mymicroblogging.ui.activity;

import android.os.Bundle;

import com.example.apuser.mymicroblogging.app.BaseActivity;
import com.example.apuser.mymicroblogging.ui.fragment.SettingsFragment;

/**
 * Created by apuser on 4/23/15.
 */
public class SettingsActivity extends BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if this activity was created before
        if (savedInstanceState == null) {
            // Create a fragment
            SettingsFragment fragment = new SettingsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName()).commit();
        }
    };
}
