package com.example.apuser.mymicroblogging.app;

import android.app.Application;
import android.content.Context;

import com.example.apuser.mymicroblogging.app.di.RootModule;

import dagger.ObjectGraph;

/**
 * Created by apuser on 4/23/15.
 */
public class MyMicroBloggingApplication extends Application{
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public static MyMicroBloggingApplication get(Context context) {
        return (MyMicroBloggingApplication) context.getApplicationContext();
    }
}
