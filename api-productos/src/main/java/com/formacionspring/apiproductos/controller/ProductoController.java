package com.formacionspring.apiproductos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.apiproductos.entity.Producto;
import com.formacionspring.apiproductos.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductoController {

	@Autowired
	private ProductoService servicio;
	
	@GetMapping("/productos")
	public List<Producto> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/productos/{id}")
	public Producto show(@PathVariable Long id) {
		return servicio.findById(id);
	}
	
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@RequestBody Producto producto) {
		return servicio.save(producto);
	}
	
	@PutMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto update(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoUpdate = servicio.findById(id);
		
		productoUpdate.setNombre(producto.getNombre());
		productoUpdate.setDetalle(producto.getDetalle());
		productoUpdate.setPrecio(producto.getPrecio());
		
		return servicio.save(productoUpdate);
		
	}
	
	@DeleteMapping("productos/{id}")
	public void delete(@PathVariable Long id) {
		servicio.delete(id);
	}
	
	
	
	
	
}
