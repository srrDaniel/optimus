package com.oygingenieria.optimus.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.oygingenieria.optimus.models.Herramienta;
import com.oygingenieria.optimus.services.IHerramienta;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/optimus/herramientas")
public class HerramientaController 
{
	@Autowired
	private IHerramienta iHerramienta;
	
	@GetMapping("/")
	public String herramientas(Model model) 
	{
		List<Herramienta> listaHerramientas = iHerramienta.listarHerramientas();
		model.addAttribute("herramientas", listaHerramientas);
		model.addAttribute("todos", listaHerramientas.size());
		model.addAttribute("activos", iHerramienta.HerramientasActivas().size());
		model.addAttribute("inactivos", iHerramienta.HerramientasInactivas().size());
		return "/optimus/herramientas/herramientas";
	}
	
	@GetMapping("/agregar")
	public String agregarHerramientas(Herramienta herramienta) 
	{
		return "/optimus/herramientas/registroHerramienta";
	}
	
	@GetMapping("/editar")
	public String editarHerramientas(Model model, Herramienta herramienta) 
	{
		herramienta = iHerramienta.buscarHerramienta(herramienta);
		model.addAttribute("herramienta", herramienta);
		return "/optimus/herramientas/registroHerramienta";
	}	
	
	@GetMapping("/estado")
	public String cambiarEstadoHerramientas(Model model, Herramienta herramienta) 
	{
		iHerramienta.cambiarEstadoHerramienta(herramienta);
		return "redirect:/optimus/herramientas/";
	}	

	@GetMapping("/eliminar")
	public String eliminarHerramientas(Herramienta herramienta, Model model) 
	{
		iHerramienta.eliminarHerramienta(herramienta);	
		return "redirect:/optimus/herramientas/";
	}

	@PostMapping("/guardar")
	public String guardarHerramientas(@Valid Herramienta herramienta, Errors errores, RedirectAttributes redirectAttrs) 
	{
		try
		{
			iHerramienta.guardarHerramienta(herramienta);	
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha guardado correctamente la herramienta")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/herramientas/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
		}
		return "redirect:/optimus/herramientas/agregar";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(@Valid Herramienta herramienta, Errors errores, RedirectAttributes redirectAttrs) {
		try {
			iHerramienta.actualizarHerramienta(herramienta);
			redirectAttrs.addFlashAttribute("mensaje", "Se ha actualizado correctamente la herramienta")
					.addFlashAttribute("clase", "success");
			return "redirect:/optimus/herramientas/";
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", e.getMessage())
					.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/herramientas/agregar";
		}
			
	}
	
	@GetMapping("/detalle")
	public String detalleHerramientas(Model model, Herramienta herramienta) 
	{
		herramienta = iHerramienta.buscarHerramienta(herramienta);
		model.addAttribute("herramienta", herramienta);
		return "/optimus/herramientas/detalleHerramienta";
	}
}