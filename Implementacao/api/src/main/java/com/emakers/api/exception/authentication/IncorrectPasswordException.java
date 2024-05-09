package com.emakers.api.exception.authentication;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {super("A senha esta incorreta");}
}
