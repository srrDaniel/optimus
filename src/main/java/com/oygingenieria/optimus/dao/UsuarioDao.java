package com.oygingenieria.optimus.dao;

import com.oygingenieria.optimus.models.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario,Long> 
{
	Usuario findByNombreUsuarioAndEstadoUsuario(String usuario, String estado);
	List<Usuario> findByEstadoUsuario(String estado);
}
