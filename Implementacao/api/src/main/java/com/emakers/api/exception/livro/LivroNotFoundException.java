package com.emakers.api.exception.livro;

public class LivroNotFoundException extends RuntimeException {
    public LivroNotFoundException(Long id) {
        super("O livro com id: " +id +" nao foi encontrrada");
    }
}
