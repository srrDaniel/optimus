package com.oygingenieria.optimus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.models.Prestamo;

public interface PrestamoDao extends JpaRepository<Prestamo, Long>
{

	List<Prestamo> findByEmpleado(Empleado empleado);
	
}
