package com.talentojava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentojava.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByNomeAndSobrenome(String nome, String sobrenome);
}
