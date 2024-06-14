package com.emakers.api.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @Email(message="O email deve ser valido")
        @NotBlank(message = "O email nao pode estar vazio")
        String email,
        @NotBlank(message = "A senha nao pode estar vazia")
        String senha) {
}
