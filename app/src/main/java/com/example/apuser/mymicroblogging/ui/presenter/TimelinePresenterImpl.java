package com.example.apuser.mymicroblogging.ui.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.di.qualifier.ActivityContext;
import com.example.apuser.mymicroblogging.ui.provider.StatusContract;
import com.example.apuser.mymicroblogging.ui.fragment.DetailsFragment;
import com.example.apuser.mymicroblogging.ui.view.TimelineView;
import com.example.apuser.mymicroblogging.ui.view.View;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by apuser on 4/23/15.
 */
@Singleton
public class TimelinePresenterImpl implements TimelinePresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = TimelinePresenterImpl.class.getSimpleName();
    private TimelineView timelineView;
    private Context context;
    private SimpleCursorAdapter adapter;
    private static final int LOADER_ID = 42;

    @Inject
    public TimelinePresenterImpl(@ActivityContext Context context, SimpleCursorAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public void initialize() {
        timelineView.renderTimelineView(adapter, LOADER_ID, this);
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
        timelineView = (TimelineView) view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id != LOADER_ID)
            return null;
        Log.d(TAG, "onCreateLoader");

        return new CursorLoader(context, StatusContract.CONTENT_URI,
                null, null, null, StatusContract.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        DetailsFragment fragment = (DetailsFragment) ((Activity)context).getFragmentManager()
                .findFragmentById(R.id.fragment_details);

        if (fragment != null && fragment.isVisible() && cursor.getCount() == 0) {
            fragment.updateView(-1);
            timelineView.showLoadError();
        }

        Log.d(TAG, "onLoadFinished with cursor: " + cursor.getCount());
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
