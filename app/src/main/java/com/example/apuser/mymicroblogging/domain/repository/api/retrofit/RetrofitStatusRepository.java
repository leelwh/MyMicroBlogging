package com.example.apuser.mymicroblogging.domain.repository.api.retrofit;

import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.domain.repository.StatusRepository;
import com.example.apuser.mymicroblogging.domain.repository.api.mapper.StatusResponseMapper;
import com.example.apuser.mymicroblogging.domain.repository.api.model.ResponseStatus;
import com.example.apuser.mymicroblogging.domain.repository.api.model.ResponseStatuses;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by apuser on 4/23/15.
 */
public class RetrofitStatusRepository implements StatusRepository{
    private RetrofitStatusService retrofitStatusService;

    public RetrofitStatusRepository(RetrofitStatusService retrofitStatusService) {
        this.retrofitStatusService = retrofitStatusService;
    }

    @Override
    public List<Status> getStatusCollection() {
        retrofitStatusService.getStatus()
                .map((Func1<ResponseStatuses, List<ResponseStatus>>) statuses -> {
                    return statuses.getStatuses();})
    }
}
