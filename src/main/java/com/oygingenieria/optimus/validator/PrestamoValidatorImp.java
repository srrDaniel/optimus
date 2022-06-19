package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.models.Prestamo;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class PrestamoValidatorImp implements IPrestamoValidator{
	
	@Override
	public void validator(Prestamo prestamo) throws ApiUnprocessableEntity{
		if(prestamo.getEmpleado()==null) message("No se ha ingresado un empleado.");
		
		if(prestamo.getHerramienta()==null) message("No se ha ingresado una herramienta.");
		
		if(prestamo.getCantidad_herramientas()==null) message("No se ha ingresado la cantidad de herramientas a prestar.");
	}

	private void message(String message) throws ApiUnprocessableEntity{
		throw new ApiUnprocessableEntity(message);
	}
}
