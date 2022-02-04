package com.formacionspring.apiproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspring.apiproductos.dao.ProductoDao;
import com.formacionspring.apiproductos.entity.Producto;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoDao productodao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productodao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productodao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productodao.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productodao.deleteById(id);
	}

}
