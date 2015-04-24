package com.example.apuser.mymicroblogging.domain.repository;

import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.domain.repository.api.model.PostStatus;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by apuser on 4/23/15.
 */
public interface StatusRepository {
    Subscription getStatusCollection(Observer<List<Status>> observer);

    void postStatus(String status, String latitude, String longitude);
}
