package com.example.apuser.mymicroblogging.ui.fragment;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BaseListFragment;
import com.example.apuser.mymicroblogging.domain.repository.StatusContract;
import com.example.apuser.mymicroblogging.ui.activity.DetailsActivity;
import com.example.apuser.mymicroblogging.ui.activity.Navigator;
import com.example.apuser.mymicroblogging.ui.custom.FreshnessView;

import javax.inject.Inject;


/**
 * Created by apuser on 4/23/15.
 */
public class TimelineFragment extends BaseListFragment implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = TimelineFragment.class.getSimpleName();
    private static final String[] FROM = { StatusContract.Column.USER,
            StatusContract.Column.MESSAGE, StatusContract.Column.CREATED_AT,
            StatusContract.Column.CREATED_AT };
    private static final int[] TO = { R.id.list_item_text_user,
            R.id.list_item_text_message, R.id.list_item_text_created_at,
            R.id.list_item_freshness };
    private static final int LOADER_ID = 42;
    private SimpleCursorAdapter mAdapter;
    @Inject
    Navigator navigator;
    private static final ViewBinder VIEW_BINDER = new ViewBinder() {

        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            long timestamp;

            switch (view.getId()) {
                case R.id.list_item_text_created_at:
                    timestamp = cursor.getLong(columnIndex);
                    CharSequence relTime = DateUtils
                            .getRelativeTimeSpanString(timestamp);
                    ((TextView) view).setText(relTime);
                    return true;
                case R.id.list_item_freshness:
                    timestamp = cursor.getLong(columnIndex);
                    ((FreshnessView) view).setTimestamp(timestamp);
                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item,
                null, FROM, TO, 0);
        mAdapter.setViewBinder(VIEW_BINDER);

        setListAdapter(mAdapter);

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        DetailsFragment fragment = (DetailsFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_details);

        if (fragment != null && fragment.isVisible()) {
            fragment.updateView(id);
        } else {
            navigator.openDetailsActivity(id);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id != LOADER_ID)
            return null;
        Log.d(TAG, "onCreateLoader");

        return new CursorLoader(getActivity(), StatusContract.CONTENT_URI,
                null, null, null, StatusContract.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        DetailsFragment fragment = (DetailsFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_details);

        if (fragment != null && fragment.isVisible() && cursor.getCount() == 0) {
            fragment.updateView(-1);
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_LONG).show();
        }

        Log.d(TAG, "onLoadFinished with cursor: " + cursor.getCount());
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
