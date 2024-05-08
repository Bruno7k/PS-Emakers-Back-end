package com.emakers.api.data.entity;
import com.emakers.api.data.dto.request.PessoaRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name ="Pessoa")
public class Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPessoa;

    @Column(name="email", nullable = false, length = 80, unique = true)
    private String email;

    @Column(name="senha", nullable = false, length = 80)
    private String senha;

    @Column(name="nome", nullable = false, length = 80)
    private String nome;

    @Column(name="cep", nullable = false, length = 9)
    private String cep;

    @ManyToMany(mappedBy = "pessoas")
    private Set<Livro> livros;

    @Builder
    public Pessoa(PessoaRequestDto pessoaRequestDto) {
        this.email = pessoaRequestDto.email();
        this.senha = pessoaRequestDto.senha();
        this.nome = pessoaRequestDto.nome();
        this.cep = pessoaRequestDto.cep();
    }

}
