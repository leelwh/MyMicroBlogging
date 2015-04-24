package com.example.apuser.mymicroblogging.domain.repository.api.retrofit;

import com.example.apuser.mymicroblogging.domain.repository.api.model.PostStatus;
import com.example.apuser.mymicroblogging.domain.repository.api.model.ResponseStatuses;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by apuser on 4/23/15.
 */
public interface RetrofitStatusService {
    @GET("/statuses/friends_timeline.xml")
    Observable<ResponseStatuses> getStatus();

    @FormUrlEncoded
    @POST("/statuses/update.xml")
    Response postStatus(@Field("status") String status,
                    @Field("lat") String latitude,
                    @Field("long") String longitude);
}
