package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

//Interface de la validación de datos recibidos para la creación de Comprobantes
@Service
public interface IComprobanteValidator {
	void validator(Comprobante comprobante) throws ApiUnprocessableEntity;

	void validatorActualizar(Comprobante comprobante) throws ApiUnprocessableEntity;
}
