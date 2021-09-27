package com.desafio.concrete.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.desafio.concrete.entities.Phone;
import com.desafio.concrete.entities.Usuario;

public class UsuarioNewDto implements Serializable{
	
	private static final Long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 5, max = 80, message = "O campo deve possuir entre 5 e 80 caracteres!")
    private String name;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
    private String email;    
    
	@NotEmpty
	private String password;
	private List<Phone> phones;	
    private Date created; 
    private Date modified; 
    private Date last_login;
    
   
    private String token;
    
    public UsuarioNewDto() {
    	
    }
    
    
    
    public UsuarioNewDto(Usuario obj) {    	
    	id = obj.getId();
    	name = obj.getName();
    	email = obj.getEmail();    
    	password = obj.getPassword();
    	phones = obj.getPhones().stream().map(x -> new Phone(x)).collect(Collectors.toList());
    	created = new Date(System.currentTimeMillis());
    	modified = new Date(System.currentTimeMillis());
    	last_login = new Date(System.currentTimeMillis());    	
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


	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}


	public List<Phone> getPhones() {
		return phones;
	}



	public void setPhones(List<Phone> phones) {
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
