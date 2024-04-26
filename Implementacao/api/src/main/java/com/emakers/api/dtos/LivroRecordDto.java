package com.emakers.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record LivroRecordDto(@NotBlank String nome,@NotBlank String autor,@NotNull Date data_lancamento) {
}
