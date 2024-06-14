package com.emakers.api.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaRequestDto(
        @Email(message="O email deve ser valido")
        @NotBlank(message = "O email nao pode estar vazio")
        @Size(min=2, max=80, message = "O email deve ter entre 2 e 80 caracteres")
        String email,

        @NotBlank(message = "A senha nao pode estar vazia")
        @Size(min=6, max=80, message = "A senha deve ter entre 6 e 80 caracteres")
        String senha,

        @NotBlank(message = "O nome da pessoa nao pode estar vazio")
        @Size(min=2, max=80, message = "O nome da pessoa deve ter entre 2 e 80 caracteres")
        String nome,

        @NotBlank(message = "O cep nao pode estar vazio")
        @Size(min=9, max = 9, message = "O cep e invalido")
        String cep
) {
}
