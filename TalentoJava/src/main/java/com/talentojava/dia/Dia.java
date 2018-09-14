package com.talentojava.dia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.talentojava.pedido.Pedido;

@Entity
public class Dia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany
	private List<Pedido> pedidos = new ArrayList<>();
	private boolean hoje;
	
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

	public boolean isHoje() {
		return hoje;
	}

	public void setHoje(boolean hoje) {
		this.hoje = hoje;
	}
	
	public void addPedido(Pedido pedido) {
		pedidos.add(pedido);
	}
}
