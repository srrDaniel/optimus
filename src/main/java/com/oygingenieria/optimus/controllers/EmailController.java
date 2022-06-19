package com.oygingenieria.optimus.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.oygingenieria.optimus.models.dto.CambiarClaveDTO;
import com.oygingenieria.optimus.models.dto.EmailValuesDTO;
import com.oygingenieria.optimus.services.IEmailService;
import com.oygingenieria.optimus.utils.exceptions.ApiNotFound;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Controller
@CrossOrigin
public class EmailController 
{
	
	@Autowired
	IEmailService iEmailService;
			
	@RequestMapping(value = "/email/send-html", method = RequestMethod.POST)
	public String sendEmailTemplate(EmailValuesDTO emailValuesDTO, 
			RedirectAttributes redirectAttrs) throws ApiNotFound
	{
		try 
		{
			iEmailService.sendEmail(emailValuesDTO);
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha enviado el enlace correctamente.")
				.addFlashAttribute("clase", "success");
			return "redirect:/auth/login/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/auth/forgot-password/";
		}
		
	}
	
	@RequestMapping(value = "/email/change-password")
	public String cambiarClave(@Valid CambiarClaveDTO cambiarClaveDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttrs) throws ApiUnprocessableEntity 
	{
		try 
		{
			iEmailService.cambiarClave(cambiarClaveDTO , bindingResult);
			redirectAttrs
			.addFlashAttribute("mensaje", "Se ha cambiado correctamente la clave.")
			.addFlashAttribute("clase", "success");
			return "redirect:/auth/login/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/auth/forgot-password/";
		}
	}
}
