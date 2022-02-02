package com.formacionspring.springmvc.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacionspring.springmvc.springmvc.entity.Empleado;
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
	
	
	@GetMapping("empleados/nuevo")
	public String crearEmpleadoFormulario(Model modelo) {
		Empleado empleado= new Empleado();
		modelo.addAttribute("empleado", empleado);
		return "crear_empleado";
	}
	
	@PostMapping("/empleados")
	public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
		servicio.guardarEmpleado(empleado);
		return "redirect:/empleados";
	}
	
	@GetMapping("/empleados/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id,Model modelo) {
		modelo.addAttribute("empleado",servicio.obtenerEmpleadoPorId(id));
		return "editar_empleado";
	}
	
	@PostMapping("/empleados/{id}")
	public String actualizarEmpleado(@PathVariable Long id,@ModelAttribute("empleado") Empleado empleado) {
		Empleado empleadoExistente= servicio.obtenerEmpleadoPorId(id);
		
		empleadoExistente.setId(id);
		empleadoExistente.setNombre(empleado.getNombre());
		empleadoExistente.setApellido(empleado.getApellido());
		empleadoExistente.setEmail(empleado.getEmail());
		empleadoExistente.setTelefono(empleado.getTelefono());
		
		servicio.guardarEmpleado(empleadoExistente);
		return "redirect:/empleados";
	}
	
	@GetMapping("empleado/{id}")
	public String eliminarTrabajador(@PathVariable Long id) {
		servicio.eliminarEmpleado(id);
		return "redirect:/empleados";
	}
	
	
	
}
