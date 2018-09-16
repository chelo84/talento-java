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
		Cliente c = (existe(cliente))? find(cliente): cliente;
		c = clientes.save(c);
		return c;
	}
	
	public boolean existe(Cliente cliente) {
		return clientes.findByNomeAndSobrenomeAndTelefone(cliente.getNome(), cliente.getSobrenome(), cliente.getTelefone()).isPresent();
	}

	public Cliente find(Cliente cliente) {
		return clientes.findByNomeAndSobrenomeAndTelefone(cliente.getNome(), cliente.getSobrenome(), cliente.getTelefone()).get();
	}
}
