package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Service
public interface ICmpProveedorValidator {
	void validator(CmpProveedor cmpProveedor) throws ApiUnprocessableEntity;
}
