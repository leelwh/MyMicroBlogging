package com.example.apuser.mymicroblogging.domain.repository.api.retrofit;

import com.example.apuser.mymicroblogging.domain.repository.api.model.ResponseStatuses;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by apuser on 4/23/15.
 */
public interface RetrofitStatusService {
    @GET("/statuses/friends_timeline.xml")
    ResponseStatuses getStatus();
}
