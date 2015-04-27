package com.example.apuser.mymicroblogging.domain.repository;

import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.domain.repository.api.model.PostStatus;

import java.util.List;

import retrofit.client.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by apuser on 4/23/15.
 */
public interface StatusRepository {
    Observable<List<Status>> getStatusCollection();

    Observable<Response> postStatus(String status, String latitude, String longitude);
}
