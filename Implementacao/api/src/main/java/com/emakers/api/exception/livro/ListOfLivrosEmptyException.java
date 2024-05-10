package com.emakers.api.exception.livro;

public class ListOfLivrosEmptyException extends RuntimeException {
    public ListOfLivrosEmptyException() {super("Nao a livros cadastrados");}
}
