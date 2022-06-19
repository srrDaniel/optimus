package com.oygingenieria.optimus.controllers;

import org.springframework.stereotype.Controller;
import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.models.Prestamo;
import com.oygingenieria.optimus.services.ICmpEmpleado;
import com.oygingenieria.optimus.services.IEmpleado;
import com.oygingenieria.optimus.services.IPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
@RequestMapping("/optimus/empleados")
public class EmpleadoController 
{
    @Autowired
    private IEmpleado iEmpleado;
    
	@Autowired
	private IPrestamo iPrestamo;
	
	@Autowired
	private ICmpEmpleado iCmpEmpleado;

    @GetMapping("/")
	public String Empleados(Model model) 
	{
		List<Empleado> listaEmpleados = iEmpleado.listarEmpleados();
		model.addAttribute("empleados", listaEmpleados);
		model.addAttribute("todos", listaEmpleados.size());
		model.addAttribute("activos", iEmpleado.EmpleadosActivos().size());
		model.addAttribute("inactivos", iEmpleado.EmpleadosInactivos().size());
		return "/optimus/empleados/empleados";
	}
	
	@GetMapping("/agregar")
	public String agregarEmpleado(Empleado empleado) 
	{
		return "/optimus/empleados/registroEmpleado";
	}
	
	@GetMapping("/editar")
	public String editarEmpleado(Model model, Empleado empleado) 
	{
		empleado = iEmpleado.buscarEmpleado(empleado);
		model.addAttribute("empleado", empleado);
		return "/optimus/empleados/registroEmpleado";
	}	
	
	@GetMapping("/estado")
	public String cambiarEstadoEmpleado(Model model, Empleado empleado, RedirectAttributes redirectAttrs)
	{
		try 
		{
			iEmpleado.cambiarEstadoEmpleado(empleado);
			redirectAttrs
				.addFlashAttribute("mensaje", "Se cambiado el estado del empleado correctamente.")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/empleados/";
		}
		catch(Exception e) 
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/empleados/";
		}
	}	

	@GetMapping("/eliminar")
	public String eliminarEmpleado(Empleado empleado, Model model) 
	{
		iEmpleado.eliminarEmpleado(empleado);	
		return "redirect:/optimus/empleados/";
	}

	@PostMapping("/guardar")
	public String guardarEmpleado(@Valid Empleado empleado, Errors errores, RedirectAttributes redirectAttrs) 
	{
		try
		{
			iEmpleado.guardarEmpleado(empleado);	
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha guardado correctamente el empleado")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/empleados/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/empleados/agregar";
		}
	}
	
	@PostMapping("/actualizar")
	public String actualizar(@Valid Empleado empleado, Errors errores, RedirectAttributes redirectAttrs) {
		try 
		{
			iEmpleado.actualizarEmpleado(empleado);
			redirectAttrs.addFlashAttribute("mensaje", "Se ha actualizado correctamente el empleado")
					.addFlashAttribute("clase", "success");
			return "redirect:/optimus/empleados/";
		} 
		catch (Exception e) 
		{
			redirectAttrs.addFlashAttribute("mensaje", e.getMessage()).addFlashAttribute("clase", "danger");
			return "redirect:/optimus/empleados/agregar";
		}
	}
	
	@GetMapping("/verPrestamos")
	public String verPrestamosEmpleado(Model model, Empleado empleado) 
	{
		empleado = iEmpleado.buscarEmpleado(empleado);
		List<Prestamo> listaPrestamos = iPrestamo.listarPrestamo(empleado);
		model.addAttribute("prestamos", listaPrestamos);
		model.addAttribute("empleado", empleado);
		return "/optimus/empleados/verPrestamos";
	}
	
	@GetMapping("/verComprobantes")
	public String verCmpEmpleado(Model model, Empleado empleado) 
	{
		empleado = iEmpleado.buscarEmpleado(empleado);
		List<CmpEmpleado> listaComprobantes = iCmpEmpleado.listarCmpEmpleado(empleado);
		model.addAttribute("comprobantesEmpleado", listaComprobantes);
		model.addAttribute("empleado", empleado);
		return "/optimus/empleados/verComprobantes";
	}
	
	@GetMapping("/detalle")
	public String detalleEmpleado(Model model, Empleado empleado) 
	{
		empleado = iEmpleado.buscarEmpleado(empleado);
		model.addAttribute("empleado", empleado);
		return "/optimus/empleados/detalleEmpleado";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}