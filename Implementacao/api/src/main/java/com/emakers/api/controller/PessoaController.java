package com.emakers.api.controller;

import com.emakers.api.data.dto.request.LoginRequestDto;
import com.emakers.api.data.dto.request.PessoaRequestDto;
import com.emakers.api.data.dto.response.LivroResponseDto;
import com.emakers.api.data.dto.response.LoginResponseDto;
import com.emakers.api.data.dto.response.PessoaResponseDto;
import com.emakers.api.infra.security.TokenService;
import com.emakers.api.repository.PessoaRepository;
import com.emakers.api.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Pessoa", description = "Endpoints relacionados à área de pessoa")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;


    @Operation(summary = "Faz o login da pessoa no sistema",
            description = "Faz o login com o email e senha da pessoa",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LoginResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @PostMapping(value = "/login",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.login(loginRequestDto));
    }

    @Operation(summary = "Faz o registro da pessoa no sistema",
            description = "Faz o registro com os dados da pessoa, email, senha, nome e cep ",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LoginResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
   @PostMapping(value = "/register",
           produces = "application/json",
           consumes = "application/json")
    public ResponseEntity<LoginResponseDto> register(@Valid @RequestBody PessoaRequestDto pessoaRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.register(pessoaRequestDto));
    }

    @Operation(summary = "Lista todas as pessoas",
            description = "Lista todas as pessoas que foram cadastradas",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @GetMapping(value = "/all",
            produces = "application/json")
    public ResponseEntity<List<PessoaResponseDto>> getAllPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }

    @Operation(summary = "Lista uma pessoa",
            description = "Lista uma pessoa de acordo com seu respectivo id",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @GetMapping(value = "/{idPessoa}",
        produces = "application/json")
    public ResponseEntity<PessoaResponseDto> getPessoaId(@PathVariable long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaId(idPessoa));
    }

    @Operation(summary = "Atualiza uma pessoa",
            description = "Atualiza uma pessoa de acordo com suas respectivas informacoes",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @PutMapping(value = "/update/{idPessoa}",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<LoginResponseDto> updatePessoa(@PathVariable long idPessoa,@Valid @RequestBody PessoaRequestDto pessoaRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(idPessoa, pessoaRequestDto));
    }

    @Operation(summary = "Deleta uma pessoa",
            description = "Deleta uma pessoa de acordo com seu respectivo id",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }

}
