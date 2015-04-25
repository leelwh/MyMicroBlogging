package com.example.apuser.mymicroblogging.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.apuser.mymicroblogging.app.BaseActivity;
import com.example.apuser.mymicroblogging.domain.repository.StatusContract;
import com.example.apuser.mymicroblogging.ui.MyMicroBloggingUIModule;
import com.example.apuser.mymicroblogging.ui.fragment.DetailsFragment;
import com.example.apuser.mymicroblogging.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by apuser on 4/23/15.
 */
public class DetailsActivity extends BaseActivity {

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<Object>();
        modules.add(new MyMicroBloggingUIModule());
        return modules;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            DetailsFragment fragment = new DetailsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName()).commit();
        }
    }

    public static Intent getLaunchIntent(Context context, final long statusId) {
        Intent intent = new Intent(context, DetailsActivity.class);
        return intent.putExtra(StatusContract.Column.ID, statusId);
    }
}
