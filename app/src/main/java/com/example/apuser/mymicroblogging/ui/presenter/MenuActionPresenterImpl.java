package com.example.apuser.mymicroblogging.ui.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.apuser.mymicroblogging.app.di.qualifier.ActivityContext;
import com.example.apuser.mymicroblogging.domain.interactor.DefaultSubscriber;
import com.example.apuser.mymicroblogging.domain.interactor.GetStatusesUseCase;
import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.ui.provider.StatusContract;
import com.example.apuser.mymicroblogging.ui.view.MenuActionView;
import com.example.apuser.mymicroblogging.ui.view.View;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by apuser on 4/27/15.
 */
@Singleton
public class MenuActionPresenterImpl implements MenuActionPresenter {
    private static final String TAG = MenuActionPresenterImpl.class.getSimpleName();
    GetStatusesUseCase getStatusesUseCase;
    MenuActionView menuActionView;
    Context context;
    @Inject
    public MenuActionPresenterImpl(@ActivityContext Context context,
                                   GetStatusesUseCase getStatusesUseCase) {
        this.context = context;
        this.getStatusesUseCase = getStatusesUseCase;
    }

    @Override
    public void refreshTimeline() {
        getStatusesUseCase.execute(new DefaultSubscriber<List<Status>>() {
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
                updateDatabaseAndSendNotification(statuses);
            }
        });
    }

    private void updateDatabaseAndSendNotification(List<Status> statuses) {
        ContentValues values = new ContentValues();
        List<Status> timeline  = statuses;
        int count = 0;
        for (Status status : timeline) {
            values.clear();
            values.put(StatusContract.Column.ID, status.getId());
            values.put(StatusContract.Column.USER, status.getUser());
            values.put(StatusContract.Column.MESSAGE, status.getText());
            values.put(StatusContract.Column.CREATED_AT, status.getCreated_at().getTime());

            Uri uri = context.getContentResolver().insert(
                    StatusContract.CONTENT_URI, values);
            if (uri != null) {
                count++;
                Log.d(TAG,
                        String.format("%s: %s", status.getUser(),
                                status.getText()));
            }
        }
        if (count > 0) {
            context.sendBroadcast(new Intent(
                    "com.example.apuser.mymicroblogging.action.NEW_STATUSES").putExtra(
                    "count", count));
        }
    }

    @Override
    public void purge() {
        int rows = context.getContentResolver().delete(StatusContract.CONTENT_URI, null, null);
    }

    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getStatusesUseCase.unsubscribe();
    }

    @Override
    public void setView(View view) {
        menuActionView = (MenuActionView) view;
    }
}
