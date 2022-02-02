package com.formacionspring.springmvc.springmvc.service;

import java.util.List;

import com.formacionspring.springmvc.springmvc.entity.Empleado;

public interface EmpleadoServices {

	public List<Empleado> listarTodosLosEmpleados();
	
	public Empleado guardarEmpleado(Empleado empleado);
	
	public Empleado obtenerEmpleadoPorId(Long id);
	
	public void eliminarEmpleado(Long id);
}
