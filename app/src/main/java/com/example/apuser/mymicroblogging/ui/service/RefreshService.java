package com.example.apuser.mymicroblogging.ui.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.apuser.mymicroblogging.app.MyMicroBloggingApplication;
import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.domain.repository.StatusContract;
import com.example.apuser.mymicroblogging.domain.repository.api.retrofit.RetrofitStatusRepository;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by apuser on 4/23/15.
 */
public class RefreshService extends IntentService {
    private static final String TAG = RefreshService.class.getSimpleName();
    Subscription subscription;
    List<Status> timeline = null;
    @Inject RetrofitStatusRepository retrofitStatusRepository;

    public RefreshService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyMicroBloggingApplication myMicroBloggingApplication = (MyMicroBloggingApplication) getApplication();
        myMicroBloggingApplication.inject(this);
        Log.d(TAG, "onCreated");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        final String username = prefs.getString("username", "");
        final String password = prefs.getString("password", "");

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please update your username and password",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Log.d(TAG, "onStarted");

        ContentValues values = new ContentValues();

        subscription = retrofitStatusRepository.getStatusCollection(new Observer<List<Status>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
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
                    if (count > 0) {
                        sendBroadcast(new Intent(
                                "com.example.apuser.mymicroblogging.action.NEW_STATUSES").putExtra(
                                "count", count));
                    }
                }
            }
        });




        return;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroyed");
    }

}
