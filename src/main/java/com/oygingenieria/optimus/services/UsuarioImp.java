package com.oygingenieria.optimus.services;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.oygingenieria.optimus.dao.UsuarioDao;
import com.oygingenieria.optimus.models.Usuario;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.IUsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioImp implements IUsuario, UserDetailsService 
{

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private IUsuarioValidator usuarioValidator;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuarios() 
	{
		return usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuariosActivos() 
	{
		return usuarioDao.findByEstadoUsuario("Activo");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuariosInactivos() 
	{
		return usuarioDao.findByEstadoUsuario("Inactivo");
	}
	
	@Override
	@Transactional
	public void eliminarUsuario(Usuario usuario) 
	{
		usuarioDao.delete(usuario);
	}

	@Override
	@Transactional
	public void guardarUsuario(Usuario usuario) throws ApiUnprocessableEntity 
	{
		usuarioValidator.validator(usuario);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPasswordUsuario(encoder.encode(usuario.getPasswordUsuario()));
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void actualizarUsuario(Usuario usuario) throws ApiUnprocessableEntity 
	{
		usuarioValidator.validatorActualizar(usuario);
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuario(Usuario usuario) 
	{
		return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
	}
	
	@Override
	@Transactional
	public void cambiarEstadoUsuario(Usuario usuario) 
	{
		Usuario user = usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
		if (user != null) {
			var estado = (user.getEstadoUsuario().equals("Activo")) ? "Inactivo" : "Activo";
			user.setEstadoUsuario(estado);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Usuario usuario = usuarioDao.findByNombreUsuarioAndEstadoUsuario(username, "Activo");

		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("USER"));

		UserDetails userDetails = new User(usuario.getNombreUsuario(), usuario.getPasswordUsuario(), roles);
		return userDetails;
	}

}
