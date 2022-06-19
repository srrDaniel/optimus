package com.oygingenieria.optimus.dao;

import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.models.Proveedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CmpProveedorDao extends JpaRepository<CmpProveedor, Long> 
{

	List<CmpProveedor> findByProveedor(Proveedor proveedor);

}
