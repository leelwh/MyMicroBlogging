package com.example.apuser.mymicroblogging.ui.presenter;

import com.example.apuser.mymicroblogging.ui.view.View;

/**
 * Created by apuser on 4/23/15.
 */
public interface Presenter<T extends View> {

    void resume();

    void pause();

    void destroy();

    void setView(T view);

    void initialize();
}
