package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.Proveedor;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import java.util.List;

public interface IProveedor 
{
	List<Proveedor> listarProveedores();
	List<Proveedor> ProveedoresActivos();
	List<Proveedor> ProveedoresInactivos();
	List<Proveedor> listarProveedoresActivos();
	void guardarProveedor(Proveedor proveedor) throws ApiUnprocessableEntity;
	void eliminarProveedor(Proveedor proveedor);
	void cambiarEstadoProveedor(Proveedor proveedor);
	public Proveedor buscarProveedor(Proveedor proveedor);
	void actualizarProveedor(Proveedor proveedor) throws ApiUnprocessableEntity;	
}