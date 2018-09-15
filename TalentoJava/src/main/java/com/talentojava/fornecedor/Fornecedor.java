package com.talentojava.fornecedor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.talentojava.pedido.Pedido;

@Entity
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany
	private List<Pedido> pedidos;

	public Fornecedor() {
		this.pedidos = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public boolean addPedido(Pedido pedido) {
		return this.pedidos.add(pedido);
	}
	
	public boolean addPedidos(List<Pedido> pedidos) {
		return this.pedidos.addAll(pedidos);
	}
}
