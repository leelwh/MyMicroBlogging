package com.example.apuser.mymicroblogging.ui.fragment;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BaseFragment;
import com.example.apuser.mymicroblogging.ui.presenter.StatusPresenterImpl;
import com.example.apuser.mymicroblogging.ui.view.StatusView;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by apuser on 4/23/15.
 */
public class StatusFragment extends BaseFragment implements StatusView {
    private static final String TAG = StatusFragment.class.getSimpleName();
    private static final String PROVIDER = LocationManager.GPS_PROVIDER;
    private int mDefaultColor;
    private static Location location;

    @InjectView(R.id.status_button_tweet) Button mButtonTweet;
    @InjectView(R.id.status_text) EditText mTextStatus;
    @InjectView(R.id.status_text_count) TextView mTextCount;
    @InjectView(R.id.rl_progress) RelativeLayout rl_progress;

    @Inject LocationManager locationManager;
    @Inject StatusPresenterImpl statusPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        location = locationManager.getLastKnownLocation(PROVIDER);
        statusPresenter.setView(this);
        statusPresenter.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(PROVIDER, 60000, 1000,
                LOCATION_LISTENER);
        statusPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(LOCATION_LISTENER);
        statusPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        statusPresenter.destroy();
    }

    private static final LocationListener LOCATION_LISTENER = new LocationListener() {
        @Override
        public void onLocationChanged(Location l) {
            location = l;
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_status, null, false);
        mTextCount.setText(Integer.toString(140));
        mDefaultColor = mTextCount.getTextColors().getDefaultColor();

        mButtonTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = mTextStatus.getText().toString();

                if (location != null) {
                    statusPresenter.postStatus(status, String.valueOf(location.getLatitude()),
                            String.valueOf(location.getLongitude()));
                } else {
                    statusPresenter.postStatus(status,
                            String.valueOf(Double.NaN), String.valueOf(Double.NaN));
                }
            }
        });

        mTextStatus.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                int count = 140 - s.length();
                mTextCount.setText(Integer.toString(count));

                if (count < 50) {
                    mTextCount.setTextColor(Color.RED);
                } else {
                    mTextCount.setTextColor(mDefaultColor);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

        });

        Log.d(TAG, "onCreated");

        return v;
    }

    @Override
    public void showPostResult(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProcessing() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideProcessing() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }
}
