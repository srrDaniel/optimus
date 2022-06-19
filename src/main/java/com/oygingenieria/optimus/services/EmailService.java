package com.oygingenieria.optimus.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.BindingResult;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.oygingenieria.optimus.dao.UsuarioDao;
import com.oygingenieria.optimus.models.Usuario;
import com.oygingenieria.optimus.models.dto.CambiarClaveDTO;
import com.oygingenieria.optimus.models.dto.EmailValuesDTO;
import com.oygingenieria.optimus.utils.exceptions.ApiNotFound;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Service
public class EmailService implements IEmailService{
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Value("${mail.urlFront}")
	private String urlFront;
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
	private static final String SUBJECT="Cambio de contraseña";
		
	@Autowired
	private UsuarioDao usuarioDao;
	

	@Override
	@Transactional
	public void cambiarClave(@Valid CambiarClaveDTO cambiarClaveDTO, BindingResult bindingResult)
			throws ApiUnprocessableEntity {
		if(bindingResult.hasErrors()) {
			throw new ApiUnprocessableEntity("Los campos están mal diligenciados.");
		}
		if(!cambiarClaveDTO.getPassword().equals(cambiarClaveDTO.getConfirmarPassword())) {
			throw new ApiUnprocessableEntity("La clave debe ser la misma en ambos campos.");
		}
		try {
			Usuario usuario = encontrarPorToken(cambiarClaveDTO.getTokenPassword());
						
			//usuario.setPasswordUsuario(cambiarClaveDTO.getPassword());
			usuario.setTokenPassword(null);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        usuario.setPasswordUsuario(encoder.encode(cambiarClaveDTO.getPassword()));
			usuarioDao.save(usuario);
			
		}catch(Exception e) {
			throw new ApiUnprocessableEntity("El token de recuperación de la clave es incorrecto.");
		}
	}
	
	private Usuario encontrarPorToken(String token) {
		Usuario usuario = null;
		for(Usuario u: usuarioDao.findAll()) {
			if(u.getTokenPassword().equals(token)) {
				usuario = u;
			}
		}
		return usuario;
	}
	
	
	@Override
	@Transactional
	public void sendEmail(EmailValuesDTO dto2) throws ApiNotFound {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			EmailValuesDTO dto = asignarValoresDTO(dto2);
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			Context context = new Context();
			Map<String, Object> model = new HashMap<>();
			model.put("userName", dto.getUserName());
			model.put("url",urlFront+dto.getTokenPassword());
			context.setVariables(model);
			String htmlText = templateEngine.process("email-template", context);
			helper.setFrom(dto.getMailFrom());
			helper.setTo(dto.getMailTo());
			helper.setSubject(dto.getSubject());
			helper.setText(htmlText,true);
			
			javaMailSender.send(message);
		}catch(MessagingException e) {
			throw new ApiNotFound("No existe ningún usuario con este correo.");
		}
	}
	
	private EmailValuesDTO asignarValoresDTO(EmailValuesDTO dto) throws ApiNotFound{
		try {
			Usuario usuario = encontrarUsuario(dto.getMailTo());
			dto.setMailFrom(mailFrom);
			dto.setMailTo(usuario.getCorreoUsuario());
			dto.setSubject(SUBJECT);
			dto.setUserName(usuario.getNombreUsuario());
			UUID uuid = UUID.randomUUID();
			String tokenPassword = uuid.toString();
			dto.setTokenPassword(tokenPassword);
			usuario.setTokenPassword(tokenPassword);
			usuarioDao.save(usuario);
			return dto;
		}catch(Exception e) {
			throw new ApiNotFound("Este usuario o email no están registrados.");
		}
		
	}
	
	private Usuario encontrarUsuario(String email) {
		Usuario usuario = null;
		for(Usuario u: usuarioDao.findAll()) {
			if(u.getCorreoUsuario().equals(email)) {
				usuario = u;
			}
		}
		return usuario;
	}

}
