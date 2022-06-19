package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.Proveedor;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Service
public interface IProveedorValidator {
	void validator(Proveedor proveedor) throws ApiUnprocessableEntity;

	void validatorActualizar(Proveedor proveedor) throws ApiUnprocessableEntity;
}
