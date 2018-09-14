package com.talentojava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentojava.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByNomeAndSobrenome(String nome, String sobrenome);
}
