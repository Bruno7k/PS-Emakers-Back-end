package com.emakers.api.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record LivroRequestDto(

        @NotBlank
        @Size(min = 2, max = 45)
        String nome,
        @NotBlank
        @Size(min = 2, max = 45)
        String autor,
        @NotNull
        Date data_lancamento,
        @NotNull
        int quantidade
) {
}
