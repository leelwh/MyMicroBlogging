package com.example.apuser.mymicroblogging.domain.repository.exception;

/**
 * Created by apuser on 4/24/15.
 */
public class MyMicroBloggingClientIOException extends Exception {
    private static final long serialVersionUID = -8136616128447561561L;

    public MyMicroBloggingClientIOException(String detailMessage) {
        super(detailMessage);
    }

    public MyMicroBloggingClientIOException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
