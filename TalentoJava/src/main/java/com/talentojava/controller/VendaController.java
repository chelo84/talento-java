package com.talentojava.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentojava.cliente.Cliente;
import com.talentojava.dia.Dia;
import com.talentojava.livro.Livro;
import com.talentojava.pedido.Pedido;
import com.talentojava.pedido.PedidoForm;
import com.talentojava.service.ClienteService;
import com.talentojava.service.DiaService;
import com.talentojava.service.LivroService;
import com.talentojava.service.PedidoService;
import com.talentojava.validator.PedidoFormValidator;

@Controller
@RequestMapping()
public class VendaController {
	@Autowired
	private DiaService dias;
	
	@Autowired
	private LivroService livros;
	@Autowired
	private ClienteService clientes;
	@Autowired
	private PedidoService pedidos;
	@Autowired
	private PedidoFormValidator validator;
	
	@InitBinder("pedidoForm")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}
	
	@GetMapping({"/", "/hoje"}) 
	public String mainPage() {
		return  "pedidos-de-hoje.html";
	}
	
	@GetMapping("hoje/novo-pedido")
	public String novoPedidoPage(Model model) {
		model.addAttribute("pedidoForm", new PedidoForm()); 
		return "novo-pedido.html";
	}
	
	@RequestMapping(value = "hoje/novo-pedido", method = RequestMethod.POST)
	public String novoPedidoPost(@ModelAttribute @Validated PedidoForm pedidoForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "novo-pedido.html";
		}
		
		Livro l = new Livro();
		l.setTitulo(pedidoForm.getTitulo());
		l = livros.novoLivro(l);
		
		Cliente c = pedidoForm.getCliente();
		c = clientes.novoCliente(c);
		
		Pedido p = new Pedido(l, c);
		p.setPrecoUnitario(new BigDecimal(pedidoForm.getPreco()));
		p.setQuantidade(Integer.parseInt((pedidoForm.getQuantidade())));
		p = pedidos.novoPedido(p);
		
		return "redirect:/";
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
