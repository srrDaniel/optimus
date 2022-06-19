package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

/**
 * interface validacion para los datos de creacion de un empleado
 */
@Service
public interface IEmpleadoValidator {
	void validator(Empleado empleado) throws ApiUnprocessableEntity;

	void validatorActualizar(Empleado empleado) throws ApiUnprocessableEntity;
}
