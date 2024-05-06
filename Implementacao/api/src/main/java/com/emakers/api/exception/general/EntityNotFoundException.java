package com.emakers.api.exception.general;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("A entidade com id: " +id +" nao foi encontrrada");
    }
}
