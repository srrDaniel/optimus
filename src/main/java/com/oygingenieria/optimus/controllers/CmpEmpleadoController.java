package com.oygingenieria.optimus.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.models.dto.NombreEmpleadoDTO;
import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.services.IComprobante;
import com.oygingenieria.optimus.services.ICmpEmpleado;
import com.oygingenieria.optimus.services.IEmpleado;

@Controller
@RequestMapping("/optimus/comprobantesEmpleado")
public class CmpEmpleadoController 
{
	
	@Autowired
	private ICmpEmpleado iComprobanteEmpleado;
	
	@Autowired
	private IComprobante iComprobante;
	
	@Autowired
	private IEmpleado iEmpleado;

	@GetMapping("/")
	public String CmpEmpleado(Model model) 
	{
		List<CmpEmpleado> listaComprobantesEmpleado = iComprobanteEmpleado.listarCmpEmpleado();
		model.addAttribute("comprobantesEmpleado", listaComprobantesEmpleado);
		model.addAttribute("todos", listaComprobantesEmpleado.size());
		return "/optimus/comprobantesEmpleado/cmpEmpleado";
	}
	
	@GetMapping("/agregar")
	public String agregarCmpEmpleado(Model model, CmpEmpleado comprobante_empleado) 
	{
		List<Comprobante> listaComprobantes = iComprobante.listarComprobantesActivos();
		List<NombreEmpleadoDTO> listaEmpleados = iEmpleado.ListarEmpleadosActivos();
		model.addAttribute("comprobanteEmpleado", new CmpEmpleado()); 
		model.addAttribute("comprobantes", listaComprobantes);
		model.addAttribute("empleados", listaEmpleados);
		return "/optimus/comprobantesEmpleado/registroCmpEmpleado";
	}
	
	@GetMapping("/editar")
	public String editarCmpEmpleado(Model model, CmpEmpleado comprobante_empleado) 
	{
		List<Comprobante> listaComprobantes = iComprobante.listarComprobantesActivos();
		List<NombreEmpleadoDTO> listaEmpleados = iEmpleado.ListarEmpleadosActivos();
		comprobante_empleado = iComprobanteEmpleado.buscarCmpEmpleado(comprobante_empleado);
		model.addAttribute("comprobanteEmpleado", comprobante_empleado);
		model.addAttribute("comprobantes", listaComprobantes);
		model.addAttribute("empleados", listaEmpleados);
		return "/optimus/comprobantesEmpleado/registroCmpEmpleado";
	}	
	
	@GetMapping("/estado")
	public String cambiarEstadoCmpEmpleado(Model model, CmpEmpleado comprobante_empleado, RedirectAttributes redirectAttrs) throws Exception 
	{
		try 
		{
			iComprobanteEmpleado.cambiarEstadoCmpEmpleado(comprobante_empleado);
			redirectAttrs
				.addFlashAttribute("mensaje", "Se cambiado correctamente el estado de la asignaci�n del comprobante.")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/comprobantesEmpleado/";
		}
		catch(Exception e) 
		{
			redirectAttrs
			.addFlashAttribute("mensaje", e.getMessage())
			.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/comprobantesEmpleado/";
		}
			
	}	

	@GetMapping("/eliminar")
	public String eliminarCmpEmpleado(Model model, CmpEmpleado comprobante_empleado) 
	{
		iComprobanteEmpleado.eliminarCmpEmpleado(comprobante_empleado);	
		return "redirect:/optimus/comprobantesEmpleado/";
	}

	@PostMapping("/guardar")
	public String guardarCmpEmpleado(@Valid CmpEmpleado comprobante_empleado, Errors errores, RedirectAttributes redirectAttrs) 
	{
		try
		{
			iComprobanteEmpleado.guardarCmpEmpleado(comprobante_empleado);	
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha asignado correctamente el comprobante")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/comprobantesEmpleado/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/comprobantesEmpleado/agregar";
		}
		
	}
	
	@GetMapping("/detalle")
	public String detalleCmpEmpleado(Model model, CmpEmpleado comprobante_empleado) 
	{
		comprobante_empleado = iComprobanteEmpleado.buscarCmpEmpleado(comprobante_empleado);
		model.addAttribute("comprobanteEmpleado", comprobante_empleado);
		return "/optimus/comprobantesEmpleado/detalleCmpEmpleado";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
