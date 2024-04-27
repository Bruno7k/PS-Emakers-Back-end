package com.emakers.api.data.entity;
import com.emakers.api.data.dto.request.PessoaRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name ="Pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPessoa;

    @Column(name="nome", nullable = false, length = 80)
    private String nome;

    @Column(name="cep", nullable = false, length = 9)
    private String cep;

    @ManyToMany(mappedBy = "pessoas")
    private Collection<Livro> livros;

    @Builder
    public Pessoa(PessoaRequestDto pessoaRequestDto) {
        this.nome = pessoaRequestDto.nome();
        this.cep = pessoaRequestDto.cep();
    }
}
