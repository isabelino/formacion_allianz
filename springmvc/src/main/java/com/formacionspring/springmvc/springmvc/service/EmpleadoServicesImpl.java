package com.formacionspring.springmvc.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.springmvc.springmvc.dao.EmpleadoRepositorio;
import com.formacionspring.springmvc.springmvc.entity.Empleado;

@Service
public class EmpleadoServicesImpl implements EmpleadoServices {

	@Autowired
	private EmpleadoRepositorio repositorio;
	
	@Override
	public List<Empleado> listarTodosLosEmpleados() {
		return repositorio.findAll();
	}

}
