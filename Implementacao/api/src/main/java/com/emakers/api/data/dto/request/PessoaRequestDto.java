package com.emakers.api.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaRequestDto(
        @NotBlank(message = "O nome da pessoa nao pode estar vazio")
        @Size(min=2, max=80)
        String nome,
        @NotBlank(message = "O cep nao pode estar vazio")
        @Size(min=9, max = 9, message = "O cep e invalido")
        String cep
) {
}
