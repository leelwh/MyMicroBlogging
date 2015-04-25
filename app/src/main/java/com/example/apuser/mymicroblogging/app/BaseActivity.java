package com.example.apuser.mymicroblogging.app;

import android.app.Activity;
import android.os.Bundle;

import com.example.apuser.mymicroblogging.app.di.ActivityModule;

import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

/**
 * Created by apuser on 4/23/15.
 */
public abstract class BaseActivity extends Activity{
    private ObjectGraph activityScopeGraph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        injectViews();
    }

    public void inject(Object object) {
        activityScopeGraph.inject(object);
    }

    private void injectDependencies() {
        MyMicroBloggingApplication myMicroBloggingApplication = (MyMicroBloggingApplication) getApplication();
        List<Object> activityScopeModules = getModules();
        activityScopeModules.add(new ActivityModule(this));
        activityScopeGraph = myMicroBloggingApplication.plus(activityScopeModules);
        inject(this);
    }

    protected abstract List<Object> getModules();

    private void injectViews() {
        ButterKnife.inject(this);
    }
}
