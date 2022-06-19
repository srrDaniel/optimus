package com.oygingenieria.optimus.dao;

import com.oygingenieria.optimus.models.Proveedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorDao extends JpaRepository<Proveedor, Long> 
{

	List<Proveedor> findByEstadoProveedor(String estado);
	
}