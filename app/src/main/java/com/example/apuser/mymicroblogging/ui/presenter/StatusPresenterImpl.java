package com.example.apuser.mymicroblogging.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.apuser.mymicroblogging.app.di.qualifier.ActivityContext;
import com.example.apuser.mymicroblogging.domain.interactor.DefaultSubscriber;
import com.example.apuser.mymicroblogging.domain.interactor.PostStatusUseCase;
import com.example.apuser.mymicroblogging.ui.activity.SettingsActivity;
import com.example.apuser.mymicroblogging.ui.view.StatusView;
import com.example.apuser.mymicroblogging.ui.view.View;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.client.Response;

/**
 * Created by apuser on 4/23/15.
 */
@Singleton
public class StatusPresenterImpl implements StatusPresenter {
    private static final String TAG = StatusPresenterImpl.class.getSimpleName();
    private PostStatusUseCase postStatusUseCase;
    private StatusView statusView;
    private SharedPreferences sharedPreferences;
    private Context context;

    @Inject
    public StatusPresenterImpl(PostStatusUseCase postStatusUseCase,
                               SharedPreferences sharedPreferences,
                               @ActivityContext Context context) {
        this.postStatusUseCase = postStatusUseCase;
        this.sharedPreferences = sharedPreferences;
        this.context = context;
    }

    @Override
    public void postStatus(String status, String latitude, String longitude) {
        if (userCheck()) {
            postStatusUseCase.setStatus(status)
                    .setLatitude(latitude)
                    .setLongitude(longitude)
                    .execute(new DefaultSubscriber<Response>() {
                @Override
                public void onCompleted() {
                    statusView.showPostResult("Successfully posted");
                }

                @Override
                public void onError(Throwable e) {
                    statusView.showPostResult("Failed to post");
                }

                @Override
                public void onNext(Response response) {
                    Log.d(TAG, "onNext");
                }
            });
        }
    }

    private boolean userCheck() {
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            context.startActivity(
                    new Intent(context, SettingsActivity.class));
            statusView.showPostResult("Please update your username and password");
            return false;
        }
        return true;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        postStatusUseCase.unsubscribe();
    }

    @Override
    public void setView(View view) {
        statusView = (StatusView) view;
    }
}
