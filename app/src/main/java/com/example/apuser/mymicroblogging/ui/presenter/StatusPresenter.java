package com.example.apuser.mymicroblogging.ui.presenter;

import com.example.apuser.mymicroblogging.ui.view.View;

/**
 * Created by apuser on 4/23/15.
 */
public interface StatusPresenter extends Presenter<View> {
    void postStatus(String status, String latitude, String longitude);
}
