package com.emakers.api.data.dto.response;

import com.emakers.api.data.entity.Pessoa;

public record PessoaResponseDto(
        long id,

        String email,

        String nome,

        String cep
) {
    public PessoaResponseDto(Pessoa pessoa) {
        this(pessoa.getIdPessoa(),pessoa.getEmail(), pessoa.getNome(), pessoa.getCep());
    }
}
