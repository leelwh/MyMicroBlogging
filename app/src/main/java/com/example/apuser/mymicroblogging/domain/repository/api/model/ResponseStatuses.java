package com.example.apuser.mymicroblogging.domain.repository.api.model;

import java.util.List;

/**
 * Created by apuser on 4/23/15.
 */
public class ResponseStatuses {
    private List<ResponseStatus> statuses;

    public void setStatuses(List<ResponseStatus> statuses) {
        this.statuses = statuses;
    }

    public List<ResponseStatus> getStatuses() {

        return statuses;
    }

    public ResponseStatuses(List<ResponseStatus> statuses) {

        this.statuses = statuses;
    }
}
