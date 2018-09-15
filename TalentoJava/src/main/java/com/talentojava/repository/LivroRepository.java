package com.talentojava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentojava.livro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	List<Livro> findByTitulo(String titulo);
}
