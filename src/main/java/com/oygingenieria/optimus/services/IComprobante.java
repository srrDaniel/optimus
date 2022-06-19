package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

import java.util.List;

public interface IComprobante 
{
	List<Comprobante> listarComprobantes();
	void guardarComprobante(Comprobante comprobante) throws ApiUnprocessableEntity;; 
	void eliminarComprobante(Comprobante comprobante);
	void cambiarEstadoComprobante(Comprobante comprobante);
	public Comprobante buscarComprobante(Comprobante comprobante);	
	void actualizarComprobante(Comprobante comprobante) throws ApiUnprocessableEntity;
	List<Comprobante> listarComprobantesActivos();
	List<Comprobante> ComprobantesActivos();
	List<Comprobante> ComprobantesInactivos();
}
