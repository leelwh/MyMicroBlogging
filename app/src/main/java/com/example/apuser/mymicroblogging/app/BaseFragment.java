package com.example.apuser.mymicroblogging.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by apuser on 4/23/15.
 */
public class BaseFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
    }

    private void injectDependencies() {
        MyMicroBloggingApplication mvpCleanArchitectureApplication = (MyMicroBloggingApplication) getActivity().getApplication();
        mvpCleanArchitectureApplication.inject(this);
    }

    private void injectViews(View view) {
        ButterKnife.inject(this, view);
    }
}
