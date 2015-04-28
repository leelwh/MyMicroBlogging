package com.example.apuser.mymicroblogging.domain.interactor;

import com.example.apuser.mymicroblogging.domain.repository.api.retrofit.RetrofitStatusRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by apuser on 4/27/15.
 */
@Singleton
public class PostStatusUseCase extends UseCase {
    private RetrofitStatusRepository retrofitStatusRepository;
    private String status;
    private String latitude;
    private String longitude;

    public PostStatusUseCase setStatus(String status) {
        this.status = status;
        return this;
    }

    public PostStatusUseCase setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public PostStatusUseCase setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    @Inject
    public PostStatusUseCase(RetrofitStatusRepository retrofitStatusRepository) {
        this.retrofitStatusRepository = retrofitStatusRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return retrofitStatusRepository.postStatus(status, latitude, longitude);
    }
}
