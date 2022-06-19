package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class CmpProveedorValidatorImp implements ICmpProveedorValidator{
	
	@Override
	public void validator(CmpProveedor cmpProveedor) throws ApiUnprocessableEntity{
		if(cmpProveedor.getComprobante()==null) message("No se ha ingresado un comprobante.");
		if(cmpProveedor.getProveedor()==null) message("No se ha ingresado un proveedor.");
	}
	
	private void message(String message) throws ApiUnprocessableEntity{
		throw new ApiUnprocessableEntity(message);
	}
}
