package com.desafio.concrete.dto;
import java.io.Serializable;

import com.desafio.concrete.entities.Phone;

public class PhoneDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String ddd;
	private String number;
	
	public PhoneDto() {
		
	}
	
	public PhoneDto(Phone obj) {
		ddd = obj.getDdd();
		number = obj.getNumber();
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}