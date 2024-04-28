package com.emakers.api.controller;

import com.emakers.api.data.dto.request.PessoaRequestDto;
import com.emakers.api.data.dto.response.PessoaResponseDto;
import com.emakers.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<PessoaResponseDto>> getAllPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }

    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<PessoaResponseDto> getPessoaId(@PathVariable long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaId(idPessoa));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<PessoaResponseDto> createPessoa(@RequestBody PessoaRequestDto pessoaRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.createPessoa(pessoaRequestDto));
    }

    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<PessoaResponseDto> updatePessoa(@PathVariable long idPessoa, @RequestBody PessoaRequestDto pessoaRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(idPessoa, pessoaRequestDto));
    }

    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }
}
