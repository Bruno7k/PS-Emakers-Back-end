package com.emakers.api.data.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoResponseDto(

    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    String ddd
) {
}
