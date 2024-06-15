package com.emakers.api.exception.general;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("A entidade com id: " +id +" nao foi encontrada");
    }
    public EntityNotFoundException(Long id, String entidade) {
        super("A entidade "+ entidade + " com id: " +id +" nao foi encontrada");
    }
    public EntityNotFoundException(String email) {
        super("Pessoa com email: " + email +" nao foi encontrada");
    }

}

