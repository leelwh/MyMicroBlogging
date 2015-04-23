package com.example.apuser.mymicroblogging.app;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by apuser on 4/23/15.
 */
public class BaseActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        injectViews();
    }

    private void injectDependencies() {
        MyMicroBloggingApplication mvpCleanArchitectureApplication = (MyMicroBloggingApplication) getApplication();
        mvpCleanArchitectureApplication.inject(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }
}
