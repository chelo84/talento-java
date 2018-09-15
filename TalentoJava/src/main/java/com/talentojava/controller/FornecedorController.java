package com.talentojava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.talentojava.dia.Dia;
import com.talentojava.pedido.Pedido;
import com.talentojava.service.FornecedorService;

@Controller
public class FornecedorController {
	@Autowired
	FornecedorService f;
	
	@GetMapping("fornecedor")
	public String getPage() {
		return "fornecedor.html";
	}
	
	@PostMapping("fornecedor/enviar-pedidos")
	public ResponseEntity<String> enviarPedido(@RequestBody Dia dia) {
		if(dia == null) System.err.println("NULL");
		List<Pedido> pedidos = dia.getPedidos();
		
		if(f.addPedidos(pedidos)) return new ResponseEntity<>("Enviado", HttpStatus.OK);
		
		return new ResponseEntity<>("Não enviado", HttpStatus.BAD_REQUEST);
	}
}
