package com.example.apuser.mymicroblogging.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.apuser.mymicroblogging.app.BaseActivity;
import com.example.apuser.mymicroblogging.ui.MyMicroBloggingUIModule;
import com.example.apuser.mymicroblogging.ui.fragment.StatusFragment;

import java.util.LinkedList;
import java.util.List;

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

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<Object>();
        modules.add(new MyMicroBloggingUIModule());
        return modules;
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, StatusActivity.class);
    }
}
