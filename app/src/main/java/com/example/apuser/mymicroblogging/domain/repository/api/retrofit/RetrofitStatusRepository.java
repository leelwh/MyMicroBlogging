package com.example.apuser.mymicroblogging.domain.repository.api.retrofit;

import android.util.Log;

import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.domain.repository.StatusRepository;
import com.example.apuser.mymicroblogging.domain.repository.api.mapper.StatusResponseMapper;
import com.example.apuser.mymicroblogging.domain.repository.api.model.PostStatus;
import com.example.apuser.mymicroblogging.domain.repository.api.model.ResponseStatus;
import com.example.apuser.mymicroblogging.domain.repository.api.model.ResponseStatuses;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by apuser on 4/23/15.
 */
public class RetrofitStatusRepository implements StatusRepository{
    private RetrofitStatusService retrofitStatusService;
    ResponseStatuses responseStatuses;
    public RetrofitStatusRepository(RetrofitStatusService retrofitStatusService) {
        this.retrofitStatusService = retrofitStatusService;
    }

    @Override
    public Subscription getStatusCollection(Observer<List<Status>> observer) {
        Subscription subscription = retrofitStatusService.getStatus()
                .map(statuses -> statuses.getStatuses())
                .flatMap(statusList -> Observable.from(statusList))
                .map(new StatusResponseMapper())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return subscription;
    }

    @Override
    public void postStatus(String status, String latitude, String longitude) {
        retrofitStatusService.postStatus(status, latitude, longitude);
    }
}
