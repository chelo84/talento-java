package com.talentojava.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.talentojava.pedido.PedidoForm;

@Component
public class PedidoFormValidator implements Validator {

	@Autowired
	private ClienteValidator clienteValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PedidoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PedidoForm p = (PedidoForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "NotEmpty.pedidoForm.titulo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantidade", "NotEmpty.pedidoForm.quantidade");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "preco", "NotEmpty.pedidoForm.preco");
		ValidationUtils.invokeValidator(this.clienteValidator, p.getCliente(), errors);
		
		if(!p.getPreco().matches("\\d*(\\.|,){0,1}\\d{0,2}$")) {
			errors.rejectValue("preco", "NotEmpty.pedidoForm.preco");
		}
		if(!p.getQuantidade().matches("\\d{1,}")) {
			errors.rejectValue("quantidade", "OnlyDigits.pedidoForm.quantidade");
		}
	}
}