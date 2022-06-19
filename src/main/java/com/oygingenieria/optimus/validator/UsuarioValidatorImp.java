package com.oygingenieria.optimus.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.dao.UsuarioDao;
import com.oygingenieria.optimus.models.Usuario;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class UsuarioValidatorImp implements IUsuarioValidator {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public void validator(Usuario usuario) throws ApiUnprocessableEntity {
		// Con la variable user validamos que el usuario contenga letras y con password
		// que la contraseña tenga letras
		Boolean user = false;
		Boolean password = false;
		if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()) {
			message("El usuario no puede estar vacío.");
		}
		if (usuario.getPasswordUsuario() == null || usuario.getPasswordUsuario().isEmpty()) {
			message("La contraseña no puede estar vacía.");
		}
		if (usuario.getNombreUsuario().length() < 3) {
			message("El usuario debe tener por lo menos 4 caracteres.");
		}
		if (usuario.getPasswordUsuario().length() < 5) {
			message("La contraseña es demasiado corta. Ingrese una más larga(mínimo 5 caracteres).");
		}

		// Validamos que el Usuario contenga por lo menos una letra
		for (int i = 0; i < usuario.getNombreUsuario().length(); i++) {
			char letra = usuario.getNombreUsuario().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z') {
				user = true;
			}
		}
		if (!user) {
			message("El usuario debe contener por lo menos una letra.");
		}

		// Validamos que la contraseña contenga por lo menos una letra
		for (int i = 0; i < usuario.getPasswordUsuario().length(); i++) {
			char letra = usuario.getPasswordUsuario().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z') {
				password = true;
			}
		}
		if (!password) {
			message("La contraseña debe contener por lo menos una letra para más seguridad.");
		}

		for (Usuario usu : usuarioDao.findAll()) {
			if (usu.getNombreUsuario().equals(usuario.getNombreUsuario()))
				message("Este nombre de Usuario ya está en uso, intente con uno diferente.");
		}

	}
	
	@Override
	public void validatorActualizar(Usuario usuario) throws ApiUnprocessableEntity {
		// Con la variable user validamos que el usuario contenga letras y con password
		// que la contraseña tenga letras
		Boolean user = false;
		Boolean password = false;
		if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()) {
			message("El usuario no puede estar vacío.");
		}
		if (usuario.getPasswordUsuario() == null || usuario.getPasswordUsuario().isEmpty()) {
			message("La contraseña no puede estar vacía.");
		}
		if (usuario.getNombreUsuario().length() < 3) {
			message("El usuario debe tener por lo menos 4 caracteres.");
		}
		if (usuario.getPasswordUsuario().length() < 5) {
			message("La contraseña es demasiado corta. Ingrese una más larga(mínimo 5 caracteres).");
		}

		// Validamos que el Usuario contenga por lo menos una letra
		for (int i = 0; i < usuario.getNombreUsuario().length(); i++) {
			char letra = usuario.getNombreUsuario().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z') {
				user = true;
			}
		}
		if (!user) {
			message("El usuario debe contener por lo menos una letra.");
		}

		// Validamos que la contraseña contenga por lo menos una letra
		for (int i = 0; i < usuario.getPasswordUsuario().length(); i++) {
			char letra = usuario.getPasswordUsuario().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z') {
				password = true;
			}
		}
		if (!password) {
			message("La contraseña debe contener por lo menos una letra para más seguridad.");
		}

		for (Usuario usu : usuarioDao.findAll()) {
			if(!(usu.getIdUsuario().equals(usuario.getIdUsuario()))) {
				if (usu.getNombreUsuario().equals(usuario.getNombreUsuario()))
					message("Este nombre de Usuario ya está en uso, intente con uno diferente.");
			}
		}

	}
	

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}
}
