package com.talentojava.pedido;

import javax.validation.Valid;

import com.talentojava.cliente.Cliente;

public class PedidoForm {
	private String titulo;
	private String quantidade;
	private String preco;
	@Valid
	private Cliente cliente;
	
	public PedidoForm() {
		cliente = new Cliente();
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
