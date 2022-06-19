package com.oygingenieria.optimus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oygingenieria.optimus.models.Herramienta;

public interface HerramientaDao extends JpaRepository<Herramienta, Long>
{

	List<Herramienta> findByEstadoHerramienta(String estado);

}
