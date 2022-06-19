package com.oygingenieria.optimus.dao;

import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.models.Empleado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CmpEmpleadoDao extends JpaRepository<CmpEmpleado, Long>
{

	List<CmpEmpleado> findByEmpleado(Empleado empleado);

}
