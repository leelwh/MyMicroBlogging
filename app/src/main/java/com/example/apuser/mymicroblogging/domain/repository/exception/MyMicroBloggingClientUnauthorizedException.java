package com.example.apuser.mymicroblogging.domain.repository.exception;

/**
 * Created by apuser on 4/24/15.
 */
public class MyMicroBloggingClientUnauthorizedException extends Exception {
    private static final long serialVersionUID = 2393819856390171545L;

    public MyMicroBloggingClientUnauthorizedException(String detailMessage) {
        super(detailMessage);
    }

    public MyMicroBloggingClientUnauthorizedException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
