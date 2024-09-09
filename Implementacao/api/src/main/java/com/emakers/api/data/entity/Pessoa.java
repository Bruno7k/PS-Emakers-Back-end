package com.emakers.api.data.entity;
import com.emakers.api.data.dto.request.PessoaRequestDto;
import com.emakers.api.data.dto.response.EnderecoResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

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

    @Column(name="logradouro", length = 255)
    private String logradouro;

    @Column(name="bairro", length = 255)
    private String bairro;

    @Column(name="cidade", length = 255)
    private String cidade;

    @Column(name="uf", length = 2)
    private String uf;


    @ManyToMany(mappedBy = "pessoas")
    private List<Livro> livros;

    @Builder
    public Pessoa(PessoaRequestDto pessoaRequestDto, EnderecoResponseDto enderecoDto) {
        this.email = pessoaRequestDto.email();
        this.senha = pessoaRequestDto.senha();
        this.nome = pessoaRequestDto.nome();
        this.cep = pessoaRequestDto.cep();
        this.logradouro = enderecoDto.logradouro();
        this.bairro = enderecoDto.bairro();
        this.cidade = enderecoDto.localidade();
        this.uf = enderecoDto.uf();
    }


}
