package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class CmpEmpleadoValidatorImp implements ICmpEmpleadoValidator{

	@Override
	public void validator(CmpEmpleado cmpEmpleado) throws ApiUnprocessableEntity{
		if(cmpEmpleado.getEmpleado()==null) message("No se ha ingresado un empleado.");
		
		if(cmpEmpleado.getComprobante()==null) message("No se ha ingresado un comprobante.");		
	}
	
	private void message(String message) throws ApiUnprocessableEntity{
		throw new ApiUnprocessableEntity(message);
	}
}
