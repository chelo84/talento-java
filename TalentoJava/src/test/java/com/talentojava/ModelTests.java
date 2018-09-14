package com.talentojava;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.talentojava.cliente.Cliente;
import com.talentojava.dia.Dia;
import com.talentojava.livro.Livro;
import com.talentojava.pedido.Pedido;
import com.talentojava.repository.DiaRepository;
import com.talentojava.service.ClienteService;
import com.talentojava.service.LivroService;
import com.talentojava.service.PedidoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelTests {
	@Autowired
	LivroService livros;
	@Autowired
	ClienteService clientes;
	@Autowired
	PedidoService pedidos;
	@Autowired
	DiaRepository dias;
	
	@Test
	public void testAddLivro() {
		Livro l = new Livro();
		l.setTitulo("As Crônicas de Gelo e Fogo");
		livros.novoLivro(l);
		
		assertTrue(livros.existe(l));
	}
	
	@Test
	public void testAddCliente() {
		Cliente c = new Cliente();
		c.setNome("Marcelo");
		c.setSobrenome("Lacroix");
		c.setTelefone("4812345678");
		c.setEmail("marcelo_lacroix@hotmail.com");
		clientes.novoCliente(c);
		
		assertTrue(clientes.existe(c));
	}
	
	@Test
	public void testAddPedido() {
		Livro l = new Livro();
		l.setTitulo("As Crônicas de Gelo e Fogo");
		l = livros.novoLivro(l);
		
		Cliente c = new Cliente();
		c.setNome("Marcelo");
		c.setSobrenome("Lacroix");
		c.setTelefone("4812345678");
		c.setEmail("marcelo_lacroix@hotmail.com");
		c = clientes.novoCliente(c);
		
		Pedido pedido = new Pedido(l, c);
		pedido.setPrecoUnitario(new BigDecimal("69.90"));
		
		Pedido p = pedidos.novoPedido(pedido);
		
		assertTrue(pedidos.existe(p));
	}
	
	@Test
	public void testDia() {
		for(int i = 0; i < 5; i++) {
			Livro l = new Livro();
			l.setTitulo("As Crônicas de Gelo e Fogo");
			l = livros.novoLivro(l);
			
			Cliente c = new Cliente();
			c.setNome("Marcelo");
			c.setSobrenome("Lacroix");
			c.setTelefone("4812345678");
			c.setEmail("marcelo_lacroix@hotmail.com");
			c = clientes.novoCliente(c);
			
			Pedido pedido = new Pedido(l, c);
			pedido.setPrecoUnitario(new BigDecimal("69.90"));
			pedido.setQuantidade(1);
			
			pedidos.novoPedido(pedido);
		}
		List<Dia> hj = dias.findHoje();
		Dia d = hj.get(hj.size()-1);
		
		assertThat(d.getPedidos().size(), is(5));
	}
}
