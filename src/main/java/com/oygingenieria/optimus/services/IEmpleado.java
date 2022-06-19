package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.models.dto.EmpleadoDTO;
import com.oygingenieria.optimus.models.dto.NombreEmpleadoDTO;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import java.util.List;
import java.text.ParseException;

public interface IEmpleado 
{
    List<Empleado> listarEmpleados();
    List<NombreEmpleadoDTO> ListarEmpleadosActivos();
    void guardarEmpleado(Empleado empleado) throws ApiUnprocessableEntity;
	void eliminarEmpleado(Empleado empleado);
	void cambiarEstadoEmpleado(Empleado empleado) throws ApiUnprocessableEntity;
	public Empleado buscarEmpleado(Empleado empleado);	
	List<EmpleadoDTO> alertas() throws ParseException;
	void actualizarEmpleado(Empleado empleado) throws ApiUnprocessableEntity;
	List<Empleado> EmpleadosActivos();
	List<Empleado> EmpleadosInactivos();
}
