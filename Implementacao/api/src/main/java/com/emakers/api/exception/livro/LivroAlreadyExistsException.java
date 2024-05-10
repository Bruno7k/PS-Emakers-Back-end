package com.emakers.api.exception.livro;

public class LivroAlreadyExistsException extends RuntimeException {
    public LivroAlreadyExistsException() {super("O livro ja existe no banco de dados");}
}
