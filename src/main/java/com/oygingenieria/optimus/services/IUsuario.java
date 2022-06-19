package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.Usuario;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import java.util.List;

public interface IUsuario 
{
    List<Usuario> listarUsuarios();
    List<Usuario> listarUsuariosActivos();
    List<Usuario> listarUsuariosInactivos();
    void eliminarUsuario(Usuario usuario);
    void guardarUsuario(Usuario usuario) throws ApiUnprocessableEntity;
    void cambiarEstadoUsuario(Usuario usuario);
    public Usuario buscarUsuario(Usuario usuario);
	void actualizarUsuario(Usuario usuario) throws ApiUnprocessableEntity;
}
