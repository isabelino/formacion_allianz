package com.formacionspring.springmvc.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.springmvc.springmvc.entity.Empleado;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado,Long> {

}
