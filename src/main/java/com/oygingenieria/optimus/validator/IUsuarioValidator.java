package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.Usuario;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

//Interface de la validación de datos recibidos para la creación de Usuarios
@Service
public interface IUsuarioValidator {
	void validator(Usuario usuario) throws ApiUnprocessableEntity;
	void validatorActualizar(Usuario usuario) throws ApiUnprocessableEntity;
}
