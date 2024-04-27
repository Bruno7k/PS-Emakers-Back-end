package com.emakers.api.service;

import com.emakers.api.data.dto.request.LivroRequestDto;
import com.emakers.api.data.dto.response.LivroResponseDto;
import com.emakers.api.data.entity.Livro;
import com.emakers.api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

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
}
