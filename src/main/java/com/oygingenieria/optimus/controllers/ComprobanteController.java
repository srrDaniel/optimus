package com.oygingenieria.optimus.controllers;

import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.services.IComprobante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/optimus/comprobantes")
public class ComprobanteController {

    @Autowired
    private IComprobante iComprobante;

	@GetMapping("/")
	public String comprobantes(Model model) 
	{
		List<Comprobante> listaComprobantes = iComprobante.listarComprobantes();
		model.addAttribute("comprobantes", listaComprobantes);
		model.addAttribute("todos", listaComprobantes.size());
		model.addAttribute("activos", iComprobante.ComprobantesActivos().size());
		model.addAttribute("inactivos", iComprobante.ComprobantesInactivos().size());
		return "/optimus/comprobantes/comprobantes";
	}
	
	@GetMapping("/agregar")
	public String agregarComprobante(Comprobante comprobante) 
	{
		return "/optimus/comprobantes/registroComprobante";
	}
	
	@GetMapping("/editar")
	public String editarComprobante(Model model, Comprobante comprobante) 
	{
		comprobante = iComprobante.buscarComprobante(comprobante);
		model.addAttribute("comprobante", comprobante);
		return "/optimus/comprobantes/registroComprobante";
	}	
	
	@GetMapping("/estado")
	public String cambiarEstadoComprobante(Model model, Comprobante comprobante) 
	{
		iComprobante.cambiarEstadoComprobante(comprobante);
		return "redirect:/optimus/comprobantes/";
	}	

	@GetMapping("/eliminar")
	public String eliminarComprobante(Model model, Comprobante comprobante) 
	{
		iComprobante.eliminarComprobante(comprobante);	
		return "redirect:/optimus/comprobantes/";
	}

	@PostMapping("/guardar")
	public String guardarComprobante(@Valid Comprobante comprobante, Errors errores, RedirectAttributes redirectAttrs) 
	{
		try
		{
			iComprobante.guardarComprobante(comprobante);	
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha guardado correctamente el comprobante")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/comprobantes/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/comprobantes/agregar";
		}
		
	}
	
	@PostMapping("/actualizar")
	public String actualizar(@Valid Comprobante comprobante, Errors errores, RedirectAttributes redirectAttrs) {
		try {
			iComprobante.actualizarComprobante(comprobante);
			redirectAttrs.addFlashAttribute("mensaje", "Se ha actualizado correctamente el comprobante")
					.addFlashAttribute("clase", "success");
			return "redirect:/optimus/comprobantes/";
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", e.getMessage()).addFlashAttribute("clase", "danger");
			return "redirect:/optimus/comprobantes/agregar";
		}

	}
	
	
	@GetMapping("/detalle")
	public String detalleComprobante(Model model, Comprobante comprobante) 
	{
		comprobante = iComprobante.buscarComprobante(comprobante);
		model.addAttribute("comprobante", comprobante);
		return "/optimus/comprobantes/detalleComprobante";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
