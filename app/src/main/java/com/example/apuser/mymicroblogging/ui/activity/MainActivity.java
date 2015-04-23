package com.example.apuser.mymicroblogging.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BaseActivity;
import com.example.apuser.mymicroblogging.domain.service.RefreshService;


public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_tweet:
                startActivity(new Intent("com.marakana.android.yamba.action.tweet"));
                return true;
            case R.id.action_refresh:
                startService(new Intent(this, RefreshService.class));
                return true;
            case R.id.action_purge:
//                int rows = getContentResolver().delete(StatusContract.CONTENT_URI, null, null);
//                Toast.makeText(this, "Deleted " + rows + " rows", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }
}
