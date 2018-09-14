package com.talentojava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talentojava.dia.Dia;
import com.talentojava.pedido.Pedido;
import com.talentojava.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidos;
	@Autowired
	private DiaService dias;
	
	@Transactional
	public Pedido novoPedido(Pedido pedido) {
		Dia hoje = dias.findHoje();
		hoje.addPedido(pedido);
		
		Pedido p = pedidos.save(pedido);
		dias.save(hoje);
		
		return p;
	}

	public boolean existe(Pedido pedido) {
		return pedidos.findById(pedido.getId()).isPresent();
	}
}
