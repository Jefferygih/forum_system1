package com.forum_system.entity.exception;

public class NotFoundException extends ErrorCodeException {

    public NotFoundException(String message) {
        super(404, message);
    }
}
