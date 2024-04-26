package com.emakers.api.data.dto.request;

import com.emakers.api.data.entity.Livro;
import com.emakers.api.data.entity.Pessoa;

public record EmprestimoResquestDto(
      Livro livro,
      Pessoa pessoa
) {
}
