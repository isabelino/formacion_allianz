package com.formacionspring.apiproductos.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionspring.apiproductos.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto,Long>{

}
