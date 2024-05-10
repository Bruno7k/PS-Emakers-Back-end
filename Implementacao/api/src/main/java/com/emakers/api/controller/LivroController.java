package com.emakers.api.controller;

import com.emakers.api.data.dto.request.LivroRequestDto;
import com.emakers.api.data.dto.response.LivroResponseDto;
import com.emakers.api.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Livro", description = "Endpoints relacionados à área de livro")
@RestController
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @Operation(summary = "Lista todos os livros",
            description = "Lista todos os livros que foram cadastrados",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @GetMapping(value = "/all",
            produces = "application/json")
    public ResponseEntity<List<LivroResponseDto>> getAllLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getAllLivros());
    }

    @Operation(summary = "Lista um livros",
            description = "Lista um livro de acordo com seu respectivo id",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @GetMapping(value = "/{idLivro}",
            produces = "application/json")
    public ResponseEntity<LivroResponseDto> getLivroId(@PathVariable long idLivro) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getLivroId(idLivro));
    }

    @Operation(summary = "Cria um livro",
            description = "Cria um livro de acordo com suas respectivas informacoes",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @PostMapping(value = "/create",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<LivroResponseDto> createLivro(@Valid @RequestBody LivroRequestDto livroRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.createLivro(livroRequestDto));
    }


    @Operation(summary = "Atualiza um livro",
            description = "Atualiza um livro de acordo com suas respectivas informacoes",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @PutMapping(value = "/update/{idLivro}",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<LivroResponseDto> updateLivro(@PathVariable long idLivro,@Valid @RequestBody LivroRequestDto livroRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.updateLivro(idLivro, livroRequestDto));
    }


    @Operation(summary = "Deleta um livros",
            description = "Deleta um livro de acordo com seu respectivo id",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @DeleteMapping(value = "/delete/{idLivro}")
    public ResponseEntity<String> deleteLivro(@PathVariable long idLivro) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteLivro(idLivro));
    }

    @Operation(summary = "Realiza o emprestimo de um livro",
            description = "Realiza o emprestimo do livro de acordo com id do livro e o id da pessoa",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @PostMapping(value = "/emprestar/{idLivro}/{idPessoa}")
    public ResponseEntity<String> emprestimoLivro(@PathVariable long idLivro, @PathVariable long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.emprestimoLivro(idLivro, idPessoa));
    }

    @Operation(summary = "Realiza a devolucao de um livro",
            description = "Realiza a devolucao do livro de acordo com id do livro e o id da pessoa",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            }
    )
    @DeleteMapping(value = "/devolver/{idLivro}/{idPessoa}")
    public ResponseEntity<String> devolverLivro(@PathVariable long idLivro, @PathVariable long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.devolverLivro(idLivro, idPessoa));
    }
}
