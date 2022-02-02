package com.formacionspring.springmvc.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.formacionspring.springmvc.springmvc.service.EmpleadoServices;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoServices servicio;
	
	@GetMapping({"/empleados","/"})
	public String listarEmpleados(Model modelo) {
		modelo.addAttribute("empleados",servicio.listarTodosLosEmpleados());
		return "empleados";
	}
}
