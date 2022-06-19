package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.Herramienta;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

//Interface de la validación de datos recibidos para la creación de Herramientas
@Service
public interface IHerramientaValidator {
	void validator(Herramienta herramienta) throws ApiUnprocessableEntity;
	void validatorActualizar(Herramienta herramienta) throws ApiUnprocessableEntity;
}
