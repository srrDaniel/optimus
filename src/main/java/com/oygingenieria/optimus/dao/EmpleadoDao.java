package com.oygingenieria.optimus.dao;

import com.oygingenieria.optimus.models.Empleado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDao extends JpaRepository <Empleado,Long> 
{

	List<Empleado> findByEstadoEmpleado(String estado);
	
}
