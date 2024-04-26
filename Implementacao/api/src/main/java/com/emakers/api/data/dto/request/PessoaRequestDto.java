package com.emakers.api.data.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDto(
        @NotBlank(message = "O nome da pessoa nao pode estar vazio")
        String nome,
        @NotBlank(message = "O cep nao pode estar vazio")
        String cep
) {
}
