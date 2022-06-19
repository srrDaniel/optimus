package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.models.Empleado;

import java.util.List;

public interface ICmpEmpleado 
{
	List<CmpEmpleado> listarCmpEmpleado();
	List<CmpEmpleado> listarCmpEmpleado(Empleado empleado);
	void guardarCmpEmpleado(CmpEmpleado cmp_empleado) throws ApiUnprocessableEntity;
	void eliminarCmpEmpleado(CmpEmpleado cmp_empleado);
	void cambiarEstadoCmpEmpleado(CmpEmpleado cmp_empleado) throws Exception;
	public CmpEmpleado buscarCmpEmpleado(CmpEmpleado cmp_empleado);
}
