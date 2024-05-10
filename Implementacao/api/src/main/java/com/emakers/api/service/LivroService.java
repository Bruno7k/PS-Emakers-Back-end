package com.emakers.api.service;

import com.emakers.api.data.dto.request.LivroRequestDto;
import com.emakers.api.data.dto.response.LivroResponseDto;
import com.emakers.api.data.entity.Livro;
import com.emakers.api.data.entity.Pessoa;
import com.emakers.api.exception.emprestimo.LoanNotAllowedException;
import com.emakers.api.exception.general.EntityNotFoundException;
import com.emakers.api.exception.general.OperationNotAllowed;
import com.emakers.api.exception.livro.ListOfLivrosEmptyException;
import com.emakers.api.exception.livro.LivroAlreadyExistsException;
import com.emakers.api.repository.LivroRepository;
import com.emakers.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<LivroResponseDto> getAllLivros(){
        List<Livro> livros = livroRepository.findAll();
        if(!livros.isEmpty()) {
            return livros.stream().map(LivroResponseDto::new).collect(Collectors.toList());
        }else{
            throw new ListOfLivrosEmptyException();
        }
    }

    public LivroResponseDto getLivroId(Long idLivro){
        Livro livro = getLivroById(idLivro);
        return new LivroResponseDto(livro);
    }

    public LivroResponseDto createLivro(LivroRequestDto livroRequestDto){
        Livro livro = new Livro(livroRequestDto);
        if(livroExiste(livro, livroRequestDto)){
            throw new LivroAlreadyExistsException();
        }
        else{
            livroRepository.save(livro);
            return new LivroResponseDto(livro);
        }
    }

    public boolean livroExiste(Livro livro, LivroRequestDto livroRequestDto){
        Optional<Livro> aux = livroRepository.findByNome(livroRequestDto.nome());
        if(aux.isEmpty() || (!Objects.equals(aux.get().getAutor(), livro.getAutor()))) {
            livroRepository.save(livro);
            return false;
        }
        else{
            return true;
        }
    }

    public LivroResponseDto updateLivro(Long idLivro, LivroRequestDto livroRequestDto){
        Livro livro = getLivroById(idLivro);
        if(livroExiste(livro, livroRequestDto)){
            throw new LivroAlreadyExistsException();
        }else {
            if (livro.getPessoas().isEmpty()){
                livro.setNome(livroRequestDto.nome());
                livro.setAutor(livroRequestDto.autor());
                livro.setData_lancamento(livroRequestDto.data_lancamento());
                livro.setQuantidade(livroRequestDto.quantidade());
                livroRepository.save(livro);
                return new LivroResponseDto(livro);
            }else{
                throw new OperationNotAllowed();
            }
        }
    }

    public String deleteLivro(Long idLivro){
        Livro livro = getLivroById(idLivro);
        if(livro.getPessoas().isEmpty()) {
            livroRepository.delete(livro);
            return "Livro com id: " + idLivro + " foi removido com sucesso";
        }else{
            throw new OperationNotAllowed();
        }
    }

    private Livro getLivroById(Long idLivro){
        return livroRepository.findById(idLivro).orElseThrow(()-> new EntityNotFoundException(idLivro));

    }
    public String devolverLivro(Long idLivro, Long idPessoa){
        Livro livro = livroRepository.findById(idLivro).orElseThrow(()-> new EntityNotFoundException(idLivro, "livro"));
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(()-> new EntityNotFoundException(idPessoa, "pessoa"));
        if(livro.getPessoas().contains(pessoa)) {
            livro.getPessoas().remove(pessoa);
            int quantidade = livro.getQuantidade();
            livro.setQuantidade(quantidade + 1);
            livroRepository.save(livro);
            return "Livro devolvido com sucesso";
        }else{
            throw new LoanNotAllowedException(idLivro, idPessoa);
        }
    }

    public String emprestimoLivro(Long idLivro, Long idPessoa){
        Livro livro = livroRepository.findById(idLivro).orElseThrow(()-> new EntityNotFoundException(idLivro, "livro"));
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(()-> new EntityNotFoundException(idPessoa, "pessoa"));
        if(livro.getQuantidade()>0){
            if(livro.getPessoas().contains(pessoa)){
                throw new LoanNotAllowedException(idLivro);
            }
            livro.getPessoas().add(pessoa);
            int quantidade = livro.getQuantidade();
            livro.setQuantidade(quantidade-1);
            livroRepository.save(livro);
            return "O emprestimo do livro foi realizado com sucesso";
        }else{
            throw new LoanNotAllowedException(idLivro, 0);
        }
    }
    //public List<Pessoa> listarEmprestimos(){}

}
