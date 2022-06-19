package com.oygingenieria.optimus.dao;

import com.oygingenieria.optimus.models.Material;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialDao extends JpaRepository <Material,Long> 
{

	List<Material> findByEstadoMaterial(String estado);

}
