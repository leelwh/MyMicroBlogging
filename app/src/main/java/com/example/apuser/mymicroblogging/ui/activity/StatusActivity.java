package com.example.apuser.mymicroblogging.ui.activity;

import android.os.Bundle;

import com.example.apuser.mymicroblogging.app.BaseActivity;
import com.example.apuser.mymicroblogging.ui.fragment.StatusFragment;

/**
 * Created by apuser on 4/23/15.
 */
public class StatusActivity extends BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            StatusFragment fragment = new StatusFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName()).commit();
        }
    }
}
