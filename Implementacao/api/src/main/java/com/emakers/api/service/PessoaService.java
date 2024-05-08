package com.emakers.api.service;

import com.emakers.api.data.dto.request.PessoaRequestDto;
import com.emakers.api.data.dto.response.PessoaResponseDto;
import com.emakers.api.data.entity.Pessoa;
import com.emakers.api.exception.general.EntityNotFoundException;
import com.emakers.api.infra.security.TokenService;
import com.emakers.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public List<PessoaResponseDto> getAllPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(PessoaResponseDto::new).collect(Collectors.toList());
    }

    public PessoaResponseDto getPessoaId(Long idPessoa){
        Pessoa pessoa = getPessoaByID(idPessoa);
        return new PessoaResponseDto(pessoa);
    }

    public PessoaResponseDto register(PessoaRequestDto pessoaRequestDto){
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(pessoaRequestDto.email());
        pessoa.setSenha(passwordEncoder.encode(pessoaRequestDto.senha()));
        pessoa.setNome(pessoaRequestDto.nome());
        pessoa.setCep(pessoaRequestDto.cep());
        this.pessoaRepository.save(pessoa);
        String token = tokenService.generateToken(pessoa);
        return new PessoaResponseDto(pessoa);
    }

    public PessoaResponseDto updatePessoa(Long idPessoa, PessoaRequestDto pessoaRequestDto){
        Pessoa pessoa = getPessoaByID(idPessoa);
        pessoa.setEmail(pessoaRequestDto.email());
        pessoa.setSenha(pessoaRequestDto.senha());
        pessoa.setNome(pessoaRequestDto.nome());
        pessoa.setCep(pessoaRequestDto.cep());
        pessoaRepository.save(pessoa);
        return new PessoaResponseDto(pessoa);
    }

    public String deletePessoa(Long idPessoa){
        Pessoa pessoa = getPessoaByID(idPessoa);;
        pessoaRepository.delete(pessoa);
        return "A pessoa com id: " + idPessoa + " foi removida com sucesso";
    }

    private Pessoa getPessoaByID(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new EntityNotFoundException(idPessoa));

    }

}
