package com.formacion.spring.hellospring.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacion.spring.hellospring.models.entity.Cliente;

public interface ClienteDao extends CrudRepository<Cliente,Long>{

}
