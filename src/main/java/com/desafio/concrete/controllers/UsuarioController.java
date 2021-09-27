package com.desafio.concrete.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.concrete.dto.UsuarioDto;
import com.desafio.concrete.dto.UsuarioNewDto;
import com.desafio.concrete.entities.Usuario;
import com.desafio.concrete.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDto>> findAll(){
		List<Usuario> listaDeUsuarios = userService.findAll();
		List<UsuarioDto> listaUsuariosAExibir = listaDeUsuarios.stream().map(x -> new UsuarioDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok(listaUsuariosAExibir);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDto> find(@PathVariable Long id) throws Exception {
		Usuario user = userService.find(id);
		
		UsuarioDto userDto = new UsuarioDto(user);
		
		return ResponseEntity.ok().body(userDto);
		
	}

	@RequestMapping(value="/cadastro", method = RequestMethod.POST)
	public ResponseEntity<?> insert(@Valid @RequestBody UsuarioNewDto objDto){
			
		Usuario obj = userService.fromDTO(objDto);
				
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		String url = uri.toString().replace("/cadastro", "");
		
		return ResponseEntity.created(URI.create(url)).build();
		
	}
	
	
}
