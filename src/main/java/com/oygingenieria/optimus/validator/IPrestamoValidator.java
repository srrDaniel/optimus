package com.oygingenieria.optimus.validator;

import org.springframework.stereotype.Service;

import com.oygingenieria.optimus.models.Prestamo;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Service
public interface IPrestamoValidator {
	void validator(Prestamo prestamo) throws ApiUnprocessableEntity;
}
