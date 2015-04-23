package com.example.apuser.mymicroblogging.app;

import android.content.Context;

/**
 * Created by apuser on 4/23/15.
 */
public class BasePresenter {
    public BasePresenter(Context context) {
        ((MyMicroBloggingApplication) context.getApplicationContext()).inject(this);

    }
}
