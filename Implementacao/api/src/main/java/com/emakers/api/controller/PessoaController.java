package com.emakers.api.controller;

import com.emakers.api.data.dto.request.LoginRequestDto;
import com.emakers.api.data.dto.request.PessoaRequestDto;
import com.emakers.api.data.dto.response.LoginResponseDto;
import com.emakers.api.data.dto.response.PessoaResponseDto;
import com.emakers.api.infra.security.TokenService;
import com.emakers.api.repository.PessoaRepository;
import com.emakers.api.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.login(loginRequestDto));
    }

   @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(@Valid @RequestBody PessoaRequestDto pessoaRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.register(pessoaRequestDto));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PessoaResponseDto>> getAllPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }

    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<PessoaResponseDto> getPessoaId(@PathVariable long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaId(idPessoa));
    }

    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<PessoaResponseDto> updatePessoa(@PathVariable long idPessoa,@Valid @RequestBody PessoaRequestDto pessoaRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(idPessoa, pessoaRequestDto));
    }

    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }

}
