package com.oygingenieria.optimus.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class IndexController 
{
	@GetMapping("/")
	public String inicio() 
	{
		return "/index";
	}
	
}