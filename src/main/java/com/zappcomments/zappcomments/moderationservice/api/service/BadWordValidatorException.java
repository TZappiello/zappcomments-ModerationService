package com.zappcomments.zappcomments.moderationservice.api.service;

public class BadWordValidatorException extends RuntimeException {

    public BadWordValidatorException(String message) {
        super(message);
    }

    public BadWordValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
