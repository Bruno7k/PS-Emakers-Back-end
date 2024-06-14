package com.emakers.api.repository;

import com.emakers.api.data.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByNome(String nome);
    Optional<Livro> findByNomeAndAutor(String nome, String autor);
}
