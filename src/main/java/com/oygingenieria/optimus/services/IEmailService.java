package com.oygingenieria.optimus.services;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;

//import org.springframework.validation.BindingResult;
import com.oygingenieria.optimus.models.dto.CambiarClaveDTO;
import com.oygingenieria.optimus.models.dto.EmailValuesDTO;
import com.oygingenieria.optimus.utils.exceptions.ApiNotFound;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

public interface IEmailService {
	void sendEmail(EmailValuesDTO dto2) throws ApiNotFound;

	void cambiarClave(@Valid CambiarClaveDTO cambiarClaveDTO, BindingResult bindingResult) throws ApiUnprocessableEntity;
}
