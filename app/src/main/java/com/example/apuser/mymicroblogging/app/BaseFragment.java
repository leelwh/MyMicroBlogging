package com.example.apuser.mymicroblogging.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by apuser on 4/23/15.
 */
public abstract class BaseFragment extends Fragment{

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectDependencies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    protected abstract int getFragmentLayout();

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
