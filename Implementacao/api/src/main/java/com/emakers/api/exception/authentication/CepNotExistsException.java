package com.emakers.api.exception.authentication;

public class CepNotExistsException extends RuntimeException {
    public CepNotExistsException(String cep) {super("O CEP: " + cep + " nao e valido");}
}
