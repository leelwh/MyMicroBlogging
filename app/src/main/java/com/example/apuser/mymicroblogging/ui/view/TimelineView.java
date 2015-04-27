package com.example.apuser.mymicroblogging.ui.view;

import android.app.LoaderManager;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

/**
 * Created by apuser on 4/23/15.
 */
public interface TimelineView extends View{
    void renderTimelineView(SimpleCursorAdapter adapter,
                            final int loaderId,
                            LoaderManager.LoaderCallbacks<Cursor> callbacks);
    void showLoadError();
}
