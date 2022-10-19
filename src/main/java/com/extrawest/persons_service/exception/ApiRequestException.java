package com.extrawest.persons_service.exception;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(ExceptionMessage message) {
        super(message.getExMessage());
    }

    public ApiRequestException(ExceptionMessage message, Long id) {
        super(message.getExMessage());
    }

    public ApiRequestException(ExceptionMessage message, String email) {
        super(message.getExMessage());
    }
}
