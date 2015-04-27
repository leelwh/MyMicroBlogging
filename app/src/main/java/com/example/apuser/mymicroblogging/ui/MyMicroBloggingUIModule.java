package com.example.apuser.mymicroblogging.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.di.qualifier.ActivityContext;
import com.example.apuser.mymicroblogging.ui.provider.StatusContract;
import com.example.apuser.mymicroblogging.ui.activity.DetailsActivity;
import com.example.apuser.mymicroblogging.ui.activity.MainActivity;
import com.example.apuser.mymicroblogging.ui.activity.SettingsActivity;
import com.example.apuser.mymicroblogging.ui.activity.StatusActivity;
import com.example.apuser.mymicroblogging.ui.custom.FreshnessView;
import com.example.apuser.mymicroblogging.ui.fragment.DetailsFragment;
import com.example.apuser.mymicroblogging.ui.fragment.SettingsFragment;
import com.example.apuser.mymicroblogging.ui.fragment.StatusFragment;
import com.example.apuser.mymicroblogging.ui.fragment.TimelineFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mikelee on 4/25/15.
 */
@Module(complete = false,
        injects = {
                MainActivity.class,
                DetailsActivity.class,
                SettingsActivity.class,
                StatusActivity.class,
                TimelineFragment.class,
                StatusFragment.class,
                SettingsFragment.class,
                DetailsFragment.class,
        })
public class MyMicroBloggingUIModule {
    private static final String[] FROM = { StatusContract.Column.USER,
            StatusContract.Column.MESSAGE, StatusContract.Column.CREATED_AT,
            StatusContract.Column.CREATED_AT };
    private static final int[] TO = { R.id.list_item_text_user,
            R.id.list_item_text_message, R.id.list_item_text_created_at,
            R.id.list_item_freshness };

    @Provides ViewBinder provideViewBinder() {
        return new ViewBinder() {
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
    }

    @Provides
    SimpleCursorAdapter provideSimpleCursorAdapter(@ActivityContext Context context,
                                                   ViewBinder viewBinder) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, R.layout.list_item,
                null, FROM, TO, 0);
        adapter.setViewBinder(viewBinder);
        return adapter;
    }

    @Provides SharedPreferences provideSharedPreferences(@ActivityContext Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
