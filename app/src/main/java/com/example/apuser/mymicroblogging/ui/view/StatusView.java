package com.example.apuser.mymicroblogging.ui.view;

/**
 * Created by apuser on 4/23/15.
 */
public interface StatusView extends View {
    void showPostResult(String message);
    void showProcessing();
    void hideProcessing();
}
