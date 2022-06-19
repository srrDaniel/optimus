package com.oygingenieria.optimus.services;

import java.util.List;

import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.models.Prestamo;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

public interface IPrestamo 
{
	List<Prestamo> listarPrestamo();
	List<Prestamo> listarPrestamo(Empleado empleado);
	void guardarPrestamo(Prestamo prestamo) throws ApiUnprocessableEntity, Exception;
	void eliminarPrestamo(Prestamo prestamo);
	void cambiarEstadoPrestamo(Prestamo prestamo) throws Exception;
	public Prestamo buscarPrestamo(Prestamo prestamo);	
}
