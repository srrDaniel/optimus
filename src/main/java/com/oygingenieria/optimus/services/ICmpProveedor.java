package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.models.Proveedor;

import java.util.List;

public interface ICmpProveedor 
{
	List<CmpProveedor> listarCmpProveedor();
	void guardarCmpProveedor(CmpProveedor cmp_proveedor) throws ApiUnprocessableEntity;
	void eliminarCmpProveedor(CmpProveedor cmp_proveedor);
	void cambiarEstadoCmpProveedor(CmpProveedor cmp_proveedor)throws Exception;
	public CmpProveedor buscarCmpProveedor(CmpProveedor cmp_proveedor);
	List<CmpProveedor> listarCmpProveedor(Proveedor proveedor);
}
