package com.desafio.concrete.repositories;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.concrete.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Transactional(readOnly = true)
	public Usuario findByEmail(String email);

}
