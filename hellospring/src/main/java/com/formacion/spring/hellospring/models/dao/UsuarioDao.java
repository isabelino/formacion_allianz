package com.formacion.spring.hellospring.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacion.spring.hellospring.models.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario,Long>{

	public Usuario findByUsername(String username);
}
