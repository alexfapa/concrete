package com.desafio.concrete.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.concrete.controllers.exceptions.FieldMessage;
import com.desafio.concrete.dto.UsuarioNewDto;
import com.desafio.concrete.entities.Usuario;
import com.desafio.concrete.repositories.UsuarioRepository;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDto> {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();		
		
		
		Usuario aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("mensagem", "Email já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	
}
