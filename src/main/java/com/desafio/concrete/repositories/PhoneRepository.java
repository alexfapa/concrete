package com.desafio.concrete.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.concrete.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer>{

}
