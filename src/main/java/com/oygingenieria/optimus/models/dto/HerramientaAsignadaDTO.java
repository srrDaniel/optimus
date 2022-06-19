package com.oygingenieria.optimus.models.dto;
import lombok.*;

@Data
@Builder
public class HerramientaAsignadaDTO {
	   private Long idHerramienta;
	    private String nombreHerramienta;
	    private int cantidadAsignada;
	   
	    
	    public HerramientaAsignadaDTO(Long idHerramienta, String nombreHerramienta, int cantidadAsignada){
	    	this.idHerramienta= idHerramienta;
	    	this.cantidadAsignada=cantidadAsignada;
	    	this.nombreHerramienta=nombreHerramienta;
	    }
}

