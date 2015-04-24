package com.example.apuser.mymicroblogging.domain.repository.exception;

/**
 * Created by apuser on 4/24/15.
 */
public class MyMicroBloggingClientTimeoutException extends Exception {
    private static final long serialVersionUID = -1886516486263376005L;

    public MyMicroBloggingClientTimeoutException(String detailMessage) {
        super(detailMessage);
    }

    public MyMicroBloggingClientTimeoutException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
