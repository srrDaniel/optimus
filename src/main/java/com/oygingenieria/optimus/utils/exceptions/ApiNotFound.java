package com.oygingenieria.optimus.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Excepción personalizada de status 404
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFound extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiNotFound(String message)
    {
        super(message);
    }
}
