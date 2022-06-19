package com.oygingenieria.optimus.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class NombreEmpleadoDTO 
{
	private Long id;
	private String nombreCompleto;
	
}
