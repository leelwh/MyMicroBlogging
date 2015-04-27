package com.example.apuser.mymicroblogging.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BaseActivity;
import com.example.apuser.mymicroblogging.ui.MyMicroBloggingUIModule;
import com.example.apuser.mymicroblogging.ui.presenter.MenuActionPresenterImpl;
import com.example.apuser.mymicroblogging.ui.view.MenuActionView;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MenuActionView {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject MenuActionPresenterImpl menuActionPresenter;
    @Inject Navigator navigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuActionPresenter.setView(this);
        menuActionPresenter.initialize();
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<Object>();
        modules.add(new MyMicroBloggingUIModule());
        return modules;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                navigator.openSettingsActivity();
                return true;
            case R.id.action_tweet:
                navigator.openStatusActivity();
                return true;
            case R.id.action_refresh:
                menuActionPresenter.refreshTimeline();
                return true;
            case R.id.action_purge:
                menuActionPresenter.purge();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void showDeleteMessage(int rows) {
        Toast.makeText(this, "Deleted " + rows + " rows", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        menuActionPresenter.destroy();
    }
}
