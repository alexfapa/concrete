package com.desafio.concrete.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.concrete.entities.Phone;
import com.desafio.concrete.entities.Usuario;
import com.desafio.concrete.repositories.PhoneRepository;



@Service
public class PhoneService {

	@Autowired
	private PhoneRepository phoneRepository;
	
	public Phone inserir(Phone phone, Usuario user) {
		phone.setUsuario(user);
		return phoneRepository.save(phone);		
	}
}
