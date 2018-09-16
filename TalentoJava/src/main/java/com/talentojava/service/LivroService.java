package com.talentojava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentojava.livro.Livro;
import com.talentojava.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	private LivroRepository livros;
	
	public Livro novoLivro(Livro livro) {
		Livro l = (this.existe(livro)) ? this.find(livro) : livro;
		l = livros.save(l);
		return l;
	}

	public boolean existe(Livro livro) {
		return !livros.findByTitulo(livro.getTitulo()).isEmpty();
	}

	public Livro find(Livro livro) {
		return livros.findByTitulo(livro.getTitulo()).get(0);
	}
}
