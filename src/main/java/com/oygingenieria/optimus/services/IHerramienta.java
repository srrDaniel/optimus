package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.Herramienta;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import java.util.List;

public interface IHerramienta 
{
	List<Herramienta> listarHerramientas();
	List<Herramienta> listarHerramientasActivas();
	void guardarHerramienta(Herramienta herramienta) throws ApiUnprocessableEntity;
	void eliminarHerramienta(Herramienta herramienta);
	void cambiarEstadoHerramienta(Herramienta herramienta);
	public Herramienta buscarHerramienta(Herramienta herramienta);
	void actualizarHerramienta(Herramienta herramienta) throws ApiUnprocessableEntity;
	List<Herramienta> HerramientasInactivas();
	List<Herramienta> HerramientasActivas();
}
