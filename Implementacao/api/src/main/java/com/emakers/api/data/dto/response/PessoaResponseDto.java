package com.emakers.api.data.dto.response;

import com.emakers.api.data.entity.Pessoa;
import com.emakers.api.data.entity.PessoaTipo;

public record PessoaResponseDto(
        long id,

        String email,

        String senha,

        String nome,

        String cep
) {
    public PessoaResponseDto(Pessoa pessoa) {
        this(pessoa.getIdPessoa(),pessoa.getEmail(), pessoa.getSenha(), pessoa.getNome(), pessoa.getCep());
    }
}
