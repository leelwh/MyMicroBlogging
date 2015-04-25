package com.example.apuser.mymicroblogging.ui.activity;


import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BaseActivity;
import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.domain.repository.StatusContract;
import com.example.apuser.mymicroblogging.domain.repository.api.retrofit.RetrofitStatusRepository;
import com.example.apuser.mymicroblogging.ui.MyMicroBloggingUIModule;
import com.example.apuser.mymicroblogging.ui.service.RefreshService;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;


public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    Subscription subscription;
    List<Status> timeline = null;
    @Inject
    RetrofitStatusRepository retrofitStatusRepository;
    @Inject Navigator navigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                startService(new Intent(this, RefreshService.class));
                return true;
            case R.id.action_purge:
                int rows = getContentResolver().delete(StatusContract.CONTENT_URI, null, null);
                Toast.makeText(this, "Deleted " + rows + " rows", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

    private void refresh() {
        ContentValues values = new ContentValues();

        retrofitStatusRepository.getStatusCollection(new Observer<List<Status>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onError");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onNext(List<Status> statuses) {
                Log.d(TAG, "onNext");
                timeline = statuses;
                int count = 0;
                for (Status status : timeline) {
                    values.clear();
                    values.put(StatusContract.Column.ID, status.getId());
                    values.put(StatusContract.Column.USER, status.getUser());
                    values.put(StatusContract.Column.MESSAGE, status.getText());
                    values.put(StatusContract.Column.CREATED_AT, status.getCreated_at().getTime());

                    Uri uri = getContentResolver().insert(
                            StatusContract.CONTENT_URI, values);
                    if (uri != null) {
                        count++;
                        Log.d(TAG,
                                String.format("%s: %s", status.getUser(),
                                        status.getText()));
                    }
                }
            }
        });
    }
}
