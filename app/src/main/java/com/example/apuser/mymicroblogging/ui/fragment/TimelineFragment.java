package com.example.apuser.mymicroblogging.ui.fragment;

import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BaseListFragment;
import com.example.apuser.mymicroblogging.ui.activity.Navigator;
import com.example.apuser.mymicroblogging.ui.presenter.TimelinePresenterImpl;
import com.example.apuser.mymicroblogging.ui.view.TimelineView;

import javax.inject.Inject;


/**
 * Created by apuser on 4/23/15.
 */
public class TimelineFragment extends BaseListFragment implements TimelineView{
    private static final String TAG = TimelineFragment.class.getSimpleName();
    @Inject Navigator navigator;
    @Inject TimelinePresenterImpl timelinePresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        timelinePresenter.setView(this);
        timelinePresenter.initialize();
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
    public void renderTimelineView(SimpleCursorAdapter adapter,
                                   final int loaderId,
                                   LoaderManager.LoaderCallbacks<Cursor> callbacks) {
        setListAdapter(adapter);
        getLoaderManager().initLoader(loaderId, null, callbacks);
    }

    @Override
    public void showLoadError() {
        Toast.makeText(getActivity(), "No data", Toast.LENGTH_LONG).show();
    }
}
