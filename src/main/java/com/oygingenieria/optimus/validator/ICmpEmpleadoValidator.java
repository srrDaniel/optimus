package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Service
public interface ICmpEmpleadoValidator {
	void validator(CmpEmpleado cmpEmpleado) throws ApiUnprocessableEntity;
}
