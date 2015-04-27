package com.example.apuser.mymicroblogging.ui.presenter;

import com.example.apuser.mymicroblogging.ui.view.View;

/**
 * Created by apuser on 4/27/15.
 */
public interface MenuActionPresenter extends Presenter<View> {
    void refreshTimeline();
    void purge();
}
