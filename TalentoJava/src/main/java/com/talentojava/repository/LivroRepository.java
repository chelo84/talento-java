package com.talentojava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentojava.livro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	Optional<Livro> findByTitulo(String titulo);
}
