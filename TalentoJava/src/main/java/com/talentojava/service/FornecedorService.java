package com.talentojava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentojava.fornecedor.Fornecedor;
import com.talentojava.pedido.Pedido;
import com.talentojava.repository.FornecedorRepository;

@Service
public class FornecedorService {
	@Autowired
	FornecedorRepository f;
	
	public Fornecedor getFornecedor() {
		if(!f.existsById(new Long("1"))) {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setId(new Long("1"));
			return f.save(fornecedor);
		}
		
		return f.findById(new Long("1")).get();
	}
	
	public boolean addPedidos(List<Pedido> pedidos) {
		Fornecedor fornecedor = this.getFornecedor();
		boolean b = fornecedor.addPedidos(pedidos);
		f.save(fornecedor);
		return b;
	}
}
