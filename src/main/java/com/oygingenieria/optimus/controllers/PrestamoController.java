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
import com.oygingenieria.optimus.models.Herramienta;
import com.oygingenieria.optimus.models.Prestamo;
import com.oygingenieria.optimus.models.dto.NombreEmpleadoDTO;
import com.oygingenieria.optimus.services.IPrestamo;
import com.oygingenieria.optimus.services.IHerramienta;
import com.oygingenieria.optimus.services.IEmpleado;

@Controller
@RequestMapping("/optimus/prestamos")
public class PrestamoController 
{
	
	@Autowired
	private IPrestamo iPrestamo;
	
	@Autowired
	private IHerramienta iHerramienta;
	
	@Autowired
	private IEmpleado iEmpleado;

	@GetMapping("/")
	public String Prestamo(Model model) 
	{
		List<Prestamo> listaPrestamos = iPrestamo.listarPrestamo();
		model.addAttribute("prestamos", listaPrestamos);
		model.addAttribute("todos", listaPrestamos.size());
		return "/optimus/prestamos/prestamos";
	}
	
	@GetMapping("/agregar")
	public String agregarPrestamo(Model model, Prestamo prestamo) 
	{
		List<Herramienta> listaHerramientas = iHerramienta.listarHerramientasActivas();
		List<NombreEmpleadoDTO> listaEmpleados = iEmpleado.ListarEmpleadosActivos();
		model.addAttribute("prestamo", new Prestamo()); 
		model.addAttribute("herramientas", listaHerramientas);
		model.addAttribute("empleados", listaEmpleados);
		return "/optimus/prestamos/registroPrestamo";
	}
	
	@GetMapping("/editar")
	public String editarPrestamo(Model model, Prestamo prestamo) 
	{
		List<Herramienta> listaHerramientas = iHerramienta.listarHerramientasActivas();
		List<NombreEmpleadoDTO> listaEmpleados = iEmpleado.ListarEmpleadosActivos();
		prestamo = iPrestamo.buscarPrestamo(prestamo);
		model.addAttribute("prestamo", prestamo);
		model.addAttribute("herramientas", listaHerramientas);
		model.addAttribute("empleados", listaEmpleados);
		return "/optimus/prestamos/registroPrestamo";
	}	
	
	@GetMapping("/estado")
	public String cambiarEstadoPrestamo(Model model, Prestamo prestamo) throws Exception 
	{
		iPrestamo.cambiarEstadoPrestamo(prestamo);
		return "redirect:/optimus/prestamos/";
	}	

	@GetMapping("/eliminar")
	public String eliminarPrestamo(Model model, Prestamo prestamo) 
	{
		iPrestamo.eliminarPrestamo(prestamo);	
		return "redirect:/optimus/prestamos/";
	}

	@PostMapping("/guardar")
	public String guardarPrestamo(@Valid Prestamo prestamo, Errors errores, RedirectAttributes redirectAttrs) 
	{
		try
		{
			iPrestamo.guardarPrestamo(prestamo);	
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha asignado correctamente el préstamo.")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/prestamos/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/prestamos/agregar";
		}
		
	}
	
	@GetMapping("/detalle")
	public String detallePrestamo(Model model, Prestamo prestamo) 
	{
		prestamo = iPrestamo.buscarPrestamo(prestamo);
		model.addAttribute("prestamo", prestamo);
		return "/optimus/prestamos/detallePrestamo";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
