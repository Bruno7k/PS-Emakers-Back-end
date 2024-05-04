package com.emakers.api.data.dto.response;

import com.emakers.api.data.entity.Livro;

import java.util.Date;

public record LivroResponseDto(

        long id,

        String name,

        String autor,

        Date data,

        int quantidade_disponivel
) {
    public LivroResponseDto(Livro livro) {
        this(livro.getIdLivro(), livro.getNome(), livro.getAutor(), livro.getData_lancamento(), livro.getQuantidade_disponivel());
    }

}
