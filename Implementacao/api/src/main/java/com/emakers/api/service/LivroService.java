package com.emakers.api.service;

import com.emakers.api.data.dto.request.LivroRequestDto;
import com.emakers.api.data.dto.response.LivroResponseDto;
import com.emakers.api.data.entity.Livro;
import com.emakers.api.data.entity.Pessoa;
import com.emakers.api.repository.LivroRepository;
import com.emakers.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<LivroResponseDto> getAllLivros(){
        List<Livro> livros = livroRepository.findAll();
        return livros.stream().map(LivroResponseDto::new).collect(Collectors.toList());
    }

    public LivroResponseDto getLivroId(Long idLivro){
        Livro livro = getLivroById(idLivro);
        return new LivroResponseDto(livro);
    }

    public LivroResponseDto createLivro(LivroRequestDto livroRequestDto){
        Livro livro = new Livro(livroRequestDto);
        livroRepository.save(livro);
        return new LivroResponseDto(livro);
    }

    public LivroResponseDto updateLivro(Long idLivro, LivroRequestDto livroRequestDto){
        Livro livro = getLivroById(idLivro);
        livro.setNome(livroRequestDto.nome());
        livro.setAutor(livroRequestDto.autor());
        livro.setData_lancamento(livroRequestDto.data_lancamento());
        livro.setQuantidade_disponivel(livroRequestDto.quantidade_disponivel());
        livroRepository.save(livro);
        return new LivroResponseDto(livro);
    }

    public String deleteLivro(Long idLivro){
        Livro livro = getLivroById(idLivro);
        livroRepository.delete(livro);
        return "Livro com id: " + idLivro + " foi removido com sucesso";
    }

    private Livro getLivroById(Long idLivro){
        return livroRepository.findById(idLivro).orElseThrow(()-> new RuntimeException("O livro requisitado nao existe ou nao foi cadastrado ainda"));

    }
    public String emprestimoLivro(Long idLivro, Long idPessoa){
        if(livroRepository.existsById(idLivro) && pessoaRepository.existsById(idPessoa)){
            Livro livro = getLivroById(idLivro);
            if(livro.getQuantidade_disponivel()>0) {
                Pessoa pessoa = pessoaRepository.findById(idPessoa).get();
                livro.getPessoas().add(pessoa);
                int quantidade = livro.getQuantidade_disponivel();
                livro.setQuantidade_disponivel(quantidade-1);
                livroRepository.save(livro);
                return "Emprestimo realizado com sucesso";
            }else{
                return "Esse livro nao esta disponivel para emprestimo no momento";
            }
        }else {
            return "O id do livro ou pessoa e inexistente";
        }

    }
    public String devolverLivro(Long idLivro, Long idPessoa){
        if(livroRepository.existsById(idLivro) && pessoaRepository.existsById(idPessoa)) {
            Livro livro = getLivroById(idLivro);
            Pessoa pessoa = pessoaRepository.findById(idPessoa).get();
            livro.getPessoas().remove(pessoa);
            int quantidade = livro.getQuantidade_disponivel();
            livro.setQuantidade_disponivel(quantidade+1);
            livroRepository.save(livro);
            return "o livro foi devolvido com sucesso";
        }else {
            return  "O id do livro ou pessoa esta incorreto";
        }
    }
}
