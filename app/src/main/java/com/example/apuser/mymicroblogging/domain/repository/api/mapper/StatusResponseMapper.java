package com.example.apuser.mymicroblogging.domain.repository.api.mapper;

import com.example.apuser.mymicroblogging.domain.model.Status;
import com.example.apuser.mymicroblogging.domain.repository.api.model.ResponseStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rx.functions.Func1;

/**
 * Created by apuser on 4/23/15.
 */
public class StatusResponseMapper implements Func1<ResponseStatus, Status> {
    public static final String DATE_FORMAT_PATTERN = "EEE MMM dd HH:mm:ss Z yyyy";

    @Override
    public Status call(ResponseStatus responseStatus) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        Status status = null;
        try {
            Date date = dateFormat.parse(responseStatus.getCreatedAt());
            status = new Status(responseStatus.getId(),
                    date,
                    responseStatus.getMessage(),
                    responseStatus.getUser().getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return status;
    }
}
