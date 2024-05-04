package com.emakers.api.data.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record LivroRequestDto(
        @NotBlank(message = "O nome do livro nao pode estar vazio")
        String nome,
        @NotBlank(message = "O nome do autor nao pode estar vazio")
        String autor,
        @NotBlank(message= "Digite a data de lancamento do livro")
        Date data_lancamento,
        @NotBlank(message = "A quantidade nao pode estar vazia")
        int quantidade_disponivel
) {
}
