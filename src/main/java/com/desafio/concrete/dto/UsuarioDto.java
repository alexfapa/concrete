package com.desafio.concrete.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.desafio.concrete.entities.Usuario;

public class UsuarioDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String name;
    private String email;    
    
	private List<PhoneDto> phones;	
    private Date created; 
    private Date modified; 
    private Date last_login; 
    private String token;
    
    public UsuarioDto() {
    	
    }
    
    
    
    public UsuarioDto(Usuario obj) {
    	id = obj.getId();
    	name = obj.getName();
    	email = obj.getEmail();
    	phones = obj.getPhones().stream().map(x -> new PhoneDto(x)).collect(Collectors.toList());
    	created = obj.getCreated();
    	modified = obj.getModified();
    	last_login = obj.getLast_login();
    	token = obj.getToken();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() { 
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public List<PhoneDto> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDto> phones) {
		this.phones = phones;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    	
}
