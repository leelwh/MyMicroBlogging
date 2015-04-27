package com.example.apuser.mymicroblogging.domain.interactor;

import com.example.apuser.mymicroblogging.domain.repository.api.retrofit.RetrofitStatusRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by apuser on 4/27/15.
 */
@Singleton
public class GetStatusesUseCase extends UseCase {
    private RetrofitStatusRepository retrofitStatusRepository;

    @Inject
    public GetStatusesUseCase(RetrofitStatusRepository retrofitStatusRepository) {
        this.retrofitStatusRepository = retrofitStatusRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return retrofitStatusRepository.getStatusCollection();
    }
}
