package com.desafio.concrete.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.concrete.dto.UsuarioNewDto;
import com.desafio.concrete.entities.Phone;
import com.desafio.concrete.entities.Usuario;
import com.desafio.concrete.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<Usuario> findAll(){
		return userRepository.findAll();
	}

	public Usuario find(Long id) {
		/*UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}*/
		
		Usuario obj = userRepository.findById(id).orElse(null);
		
		return obj;
	}
	
	public boolean findByEmail(String email) {
		Usuario user = userRepository.findByEmail(email);
		
		if(user != null) {
			return true;
		}
		return false;
	}
	
	public Usuario findByEmailAuth(String email) {
		Usuario user = userRepository.findByEmail(email);
		
		if(user == null) {
			return null;
		}
		return user;
	}

	public Usuario fromDTO(UsuarioNewDto objDto) {
		
		Usuario user = new Usuario(null, objDto.getName(), objDto.getEmail(), passwordEncoder.encode(objDto.getPassword()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), objDto.getToken());
		
		
		if(objDto.getPhones() != null && objDto.getPhones().size() > 0 ) {
			user.getPhones().addAll(objDto.getPhones());			
		}
		
		return user;
	} 

	public Usuario insert(Usuario obj) {
		List<Phone> phones = obj.getPhones();
		 
		obj.setId(null);
		obj = userRepository.save(obj);
		
		for(Phone phone : phones) {			
			phoneService.inserir(phone, obj);
			
		}		
		return obj;
	}
	
	public String updateToken(String token, Usuario user) {
		user.setToken(token);
		user.setLast_login(new Date(System.currentTimeMillis()));
		user.setModified(new Date(System.currentTimeMillis()));
		userRepository.save(user);
		
		return token;
	}
	
}
