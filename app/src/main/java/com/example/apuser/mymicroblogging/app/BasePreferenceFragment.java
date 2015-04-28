package com.example.apuser.mymicroblogging.app;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by apuser on 4/24/15.
 */
public class BasePreferenceFragment extends PreferenceFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectDependencies();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
    }

    private void injectDependencies() {
        ((BaseActivity) getActivity()).inject(this);
    }

    private void injectViews(View view) {
        ButterKnife.inject(this, view);
    }
}
