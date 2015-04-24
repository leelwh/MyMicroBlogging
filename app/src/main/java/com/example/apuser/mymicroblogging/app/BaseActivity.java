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
        MyMicroBloggingApplication myMicroBloggingApplication = (MyMicroBloggingApplication) getApplication();
        myMicroBloggingApplication.inject(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }
}
