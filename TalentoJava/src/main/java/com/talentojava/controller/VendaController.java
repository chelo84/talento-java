package com.talentojava.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talentojava.cliente.Cliente;
import com.talentojava.dia.Dia;
import com.talentojava.livro.Livro;
import com.talentojava.pedido.Pedido;
import com.talentojava.service.ClienteService;
import com.talentojava.service.DiaService;
import com.talentojava.service.LivroService;
import com.talentojava.service.PedidoService;

@Controller
@RequestMapping()
public class VendaController {
	@Autowired
	private DiaService dias;
	
	@Autowired
	LivroService livros;
	@Autowired
	ClienteService clientes;
	@Autowired
	PedidoService pedidos;
	
	@GetMapping({"/", "/hoje"}) 
	public String mainPage() {
		return  "pedidos-de-hoje.html";
	}
	
	@ModelAttribute("hoje")
	public Dia getHoje() {
	    return dias.findHoje();
	}
	@ModelAttribute("pedidosDeHoje")
	public List<Pedido> getPedidos() {
		return getHoje().getPedidos();
	}
}
