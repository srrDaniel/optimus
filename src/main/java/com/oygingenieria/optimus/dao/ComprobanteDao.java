package com.oygingenieria.optimus.dao;

import com.oygingenieria.optimus.models.Comprobante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteDao extends JpaRepository <Comprobante,Long> 
{

	List<Comprobante> findByEstadoComprobante(String estado);
	
}
