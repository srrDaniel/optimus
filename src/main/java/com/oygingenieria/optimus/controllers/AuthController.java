package com.oygingenieria.optimus.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oygingenieria.optimus.models.dto.CambiarClaveDTO;
import com.oygingenieria.optimus.models.dto.EmailValuesDTO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/auth")
public class AuthController 
{
	@GetMapping("/login")
	public String login() 
	{
		return "/auth/auth-login";
	}
	
	@GetMapping("/forgot-password")
	public String forgot(Model model) 
	{
		model.addAttribute("emailValuesDTO", new EmailValuesDTO());
		return "/auth/auth-forgot-password";
	}
		
	@GetMapping("/reset-password/{tokenPassword}")
	public String reset(Model model, @PathVariable String tokenPassword) 
	{
		CambiarClaveDTO cambiarClaveDTO = new CambiarClaveDTO();
		cambiarClaveDTO.setTokenPassword(tokenPassword);
		model.addAttribute("cambiarClaveDTO", cambiarClaveDTO);
		return "/auth/auth-reset-password";
	}
}


