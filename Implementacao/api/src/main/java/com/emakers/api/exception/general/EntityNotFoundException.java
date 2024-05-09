package com.emakers.api.exception.general;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("A entidade com id: " +id +" nao foi encontrrada");
    }
    public EntityNotFoundException(Long id, String entidade) {
        super("O/A "+ entidade + " com id: " +id +" nao foi encontrrada");
    }


}
