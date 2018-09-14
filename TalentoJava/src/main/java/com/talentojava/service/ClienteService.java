package com.talentojava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentojava.cliente.Cliente;
import com.talentojava.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clientes;
	
	public Cliente novoCliente(Cliente cliente) {
		return clientes.saveAndFlush(cliente);
	}
	
	public boolean existe(Cliente cliente) {
		return clientes.findByNomeAndSobrenome(cliente.getNome(), cliente.getSobrenome()).isPresent();
	}

	public Cliente find(Cliente cliente) {
		return clientes.findByNomeAndSobrenome(cliente.getNome(), cliente.getSobrenome()).get();
	}
}
