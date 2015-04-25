package com.example.apuser.mymicroblogging.app;

import android.app.Application;
import android.content.Context;

import com.example.apuser.mymicroblogging.app.di.RootModule;

import java.util.List;

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
        objectGraph.injectStatics();
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public static MyMicroBloggingApplication get(Context context) {
        return (MyMicroBloggingApplication) context.getApplicationContext();
    }

    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }
}
