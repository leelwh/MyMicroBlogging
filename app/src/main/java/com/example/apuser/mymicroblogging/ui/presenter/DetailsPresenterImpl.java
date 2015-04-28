package com.example.apuser.mymicroblogging.ui.presenter;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.apuser.mymicroblogging.app.di.qualifier.ActivityContext;
import com.example.apuser.mymicroblogging.ui.model.StatusModel;
import com.example.apuser.mymicroblogging.ui.provider.StatusContract;
import com.example.apuser.mymicroblogging.ui.view.DetailsView;
import com.example.apuser.mymicroblogging.ui.view.View;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by apuser on 4/23/15.
 */
@Singleton
public class DetailsPresenterImpl implements DetailsPresenter {
    private Context context;
    private DetailsView detailsView;

    @Inject
    public DetailsPresenterImpl(@ActivityContext Context context) {
        this.context = context;
    }

    public void initialize(long id) {
        detailsView.renderStatusDetailView(getStatusDetail(id));
    }

    private StatusModel getStatusDetail(long id) {
        if (id == -1) {
            return null;
        }
        Uri uri = ContentUris.withAppendedId(StatusContract.CONTENT_URI, id);

        Cursor cursor = context.getContentResolver().query(uri, null,
                null, null, null);
        if (!cursor.moveToFirst())
            return null;

        String user = cursor.getString(cursor
                .getColumnIndex(StatusContract.Column.USER));
        String message = cursor.getString(cursor
                .getColumnIndex(StatusContract.Column.MESSAGE));
        long createdAt = cursor.getLong(cursor
                .getColumnIndex(StatusContract.Column.CREATED_AT));

        return new StatusModel(user, message, createdAt);
    }
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void setView(View view) {
        detailsView = (DetailsView) view;
    }

    @Override
    public void initialize() {

    }
}
