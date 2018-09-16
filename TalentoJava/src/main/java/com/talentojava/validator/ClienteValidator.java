package com.talentojava.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.talentojava.cliente.Cliente;

@Component
public class ClienteValidator implements Validator{
	@Autowired
	@Qualifier("emailValidator")
	private EmailValidator emailValidator;
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Cliente.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Cliente cli = (Cliente)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.nome", "NotEmpty.cliente.nome");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.sobrenome", "NotEmpty.cliente.sobrenome");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.telefone", "NotEmpty.cliente.telefone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.email", "NotEmpty.cliente.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.endereco", "NotEmpty.cliente.endereco");
        
		if(!emailValidator.valid(cli.getEmail())){
			errors.rejectValue("cliente.email", "Invalid.cliente.email");
		}
		if(!cli.getTelefone().matches("\\d{1,}")) {
			errors.rejectValue("cliente.telefone", "OnlyDigits.cliente.telefone");
		}
    }
}
