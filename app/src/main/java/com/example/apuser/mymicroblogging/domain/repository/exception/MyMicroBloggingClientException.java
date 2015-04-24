package com.example.apuser.mymicroblogging.domain.repository.exception;

/**
 * Created by apuser on 4/24/15.
 */
public class MyMicroBloggingClientException extends Exception {

    private static final long serialVersionUID = 1995644171129482795L;

    public MyMicroBloggingClientException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public MyMicroBloggingClientException(String detailMessage) {
        super(detailMessage);
    }
}
