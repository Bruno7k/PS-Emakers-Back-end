package com.emakers.api.exception.emprestimo;

public class LoanNotAllowedException extends RuntimeException {
    public LoanNotAllowedException(long idLivro) {
        super("A pessoa ja pegou o livro com id: " + idLivro + " emprestado");
    }
    public LoanNotAllowedException(long idLivro, int qtd) {
        super("O livro com id: " + idLivro + " nao esta disponivel para emprestimo");
    }
    public LoanNotAllowedException(long idLivro, long idPessoa){super("A pessoa com id: " + idPessoa + " nao pegou o livro com id: " + idLivro + " emprestado" );}
}
