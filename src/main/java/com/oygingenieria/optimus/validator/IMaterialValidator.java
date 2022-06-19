package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.Material;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

//Interface de la validación de datos recibidos para la creación de Materiales
@Service
public interface IMaterialValidator {
	void validator(Material material) throws ApiUnprocessableEntity;

	void validatorActualizar(Material material) throws ApiUnprocessableEntity;
}
