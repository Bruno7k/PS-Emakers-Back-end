package com.emakers.api.data.entity;
import com.emakers.api.data.dto.request.LivroRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLivro;

    @Column(name="nome", nullable = false, length = 45)
    private String nome;

    @Column(name="autor", nullable = false, length = 45)
    private String autor;

    @Column(name = "data_lancamento", nullable = false)
    private Date data_lancamento;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Emprestimo",
            joinColumns = @JoinColumn(name = "idLivro"),
            inverseJoinColumns = @JoinColumn(name = "idPessoa")
    )
    private Collection<Pessoa> pessoas;

    @Builder
    public Livro(LivroRequestDto livroRequestDto) {
        this.nome = livroRequestDto.nome();
        this.autor = livroRequestDto.autor();
        this.data_lancamento = livroRequestDto.data_lancamento();
    }
}
