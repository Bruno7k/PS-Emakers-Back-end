package com.emakers.api.exception.authentication;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {super("O email ja existe");}
}
