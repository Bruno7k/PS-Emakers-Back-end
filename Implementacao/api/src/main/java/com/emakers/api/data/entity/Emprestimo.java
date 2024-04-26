package com.emakers.api.data.entity;

import com.emakers.api.data.dto.request.EmprestimoResquestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name ="Emprestimo")
public class Emprestimo {
    @Id
    @ManyToOne()
    @JoinColumn(name = "idLivro")
    private  Livro livro;
    @ManyToOne
    @JoinColumn (name = "idPessoa")
    private Pessoa pessoa;

    @Builder
    public Emprestimo(EmprestimoResquestDto emprestimoResquestDto) {
        this.livro = emprestimoResquestDto.livro();
        this.pessoa = emprestimoResquestDto.pessoa();
    }
}
