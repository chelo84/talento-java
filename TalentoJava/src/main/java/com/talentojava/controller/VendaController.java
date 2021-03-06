package com.talentojava.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@PropertySource("classpath:application.properties")
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
	@Autowired
	private Environment environment;
	
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
	
	@GetMapping("todos-pedidos")
	public String todosPedidosPage(Model model) {
		model.addAttribute("diaCounter", 0);
		return "todos-pedidos.html";
	}
	
	@RequestMapping(value = "hoje/novo-pedido", method = RequestMethod.POST)
	public String novoPedidoPost(@ModelAttribute @Validated PedidoForm pedidoForm, BindingResult result, Model model, RedirectAttributes ra) {
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
		
		ra.addFlashAttribute("novoPedido", p);
		return "redirect:/";
	}
	
	//Este método simula o envio dos pedidos ao fornecedor através de uma Chamada Post
	@RequestMapping(value = "hoje/enviar-pedidos", method = RequestMethod.POST)
	public String enviarPedidos(RedirectAttributes ra) {
		Dia hoje = this.getHoje();
		RestTemplate rt = new RestTemplate();
		HttpEntity<Dia> request = new HttpEntity<>(hoje);
		ResponseEntity<String> response = null;
		try {
			int port = Integer.parseInt(environment.getProperty("local.server.port"));
			response = rt.exchange("http://localhost:"+ port +"/fornecedor/enviar-pedidos", HttpMethod.POST, request, String.class);
			hoje.setHoje(false);
			dias.save(hoje);
			
			List<Long> novosPedidosIds = new ArrayList<>();
			hoje.getPedidos().forEach(pedido -> novosPedidosIds.add(pedido.getId()));
			ra.addFlashAttribute("novosPedidosIds", novosPedidosIds);
			return "redirect:/fornecedor";
		} catch (HttpStatusCodeException err) {
			ra.addFlashAttribute("erroEnviar", true);
		}
		
		return "redirect:/";
	}
	
	@ModelAttribute("hoje")
	public Dia getHoje() {
	    return dias.findHoje();
	}
	@ModelAttribute("pedidosDeHoje")
	public List<Pedido> getPedidosDeHoje() {
		return getHoje().getPedidos();
	}
	@ModelAttribute("dias")
	public List<Dia> getTodosPedidos() {
		List<Dia> tdsDias = dias.findTodosDias();
		Collections.reverse(tdsDias);
		return tdsDias;
	}
}
