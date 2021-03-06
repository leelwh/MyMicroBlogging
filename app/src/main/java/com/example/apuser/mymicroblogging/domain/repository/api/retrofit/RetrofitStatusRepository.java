package com.example.apuser.mymicroblogging.domain.repository.api.retrofit;

import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.domain.repository.StatusRepository;
import com.example.apuser.mymicroblogging.domain.repository.api.mapper.StatusResponseMapper;
import com.example.apuser.mymicroblogging.domain.repository.api.model.ResponseStatuses;
import com.example.apuser.mymicroblogging.domain.repository.exception.MyMicroBloggingClientIOException;

import java.util.List;

import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apuser on 4/23/15.
 */
public class RetrofitStatusRepository implements StatusRepository{
    private RetrofitStatusService retrofitStatusService;
    public RetrofitStatusRepository(RetrofitStatusService retrofitStatusService) {
        this.retrofitStatusService = retrofitStatusService;
    }

    @Override
    public Observable<List<Status>> getStatusCollection() {
        return retrofitStatusService.getStatus()
                .map(statuses -> statuses.getStatuses())
                .flatMap(statusList -> Observable.from(statusList))
                .map(new StatusResponseMapper())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Response> postStatus(String status, String latitude, String longitude) {

        return Observable.create(new Observable.OnSubscribe<Response>() {
            @Override public void call(Subscriber<? super Response> subscriber) {
                try {
                    Response response = retrofitStatusService.postStatus(status, latitude, longitude);
                    if (response != null) {
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new MyMicroBloggingClientIOException("response = null"));
                    }
                } catch (Exception e) {
                    subscriber.onError(new MyMicroBloggingClientIOException("exception", e));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
