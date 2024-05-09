package com.emakers.api.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record LivroRequestDto(

        @NotBlank(message = "O nome nao pode estar vazio")
        @Size(min = 2, max = 45, message = "O nome deve ter entre 2 e 45 caracteres")
        String nome,
        @NotBlank(message = "O autor nao pode estar vazio")
        @Size(min = 2, max = 45, message = "O nome deve ter entre 2 e 45 caracteres")
        String autor,
        @NotNull(message = "A data nao pode esstar nula")
        Date data_lancamento,
        @NotNull(message = "A quantidade nao pode ser nula")
        int quantidade
) {
}
