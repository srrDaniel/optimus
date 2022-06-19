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
import com.oygingenieria.optimus.models.Proveedor;
import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.services.IComprobante;
import com.oygingenieria.optimus.services.ICmpProveedor;
import com.oygingenieria.optimus.services.IProveedor;

@Controller
@RequestMapping("/optimus/comprobantesProveedor")
public class CmpProveedorController 
{
	
	@Autowired
	private ICmpProveedor iComprobanteProveedor;
	
	@Autowired
	private IComprobante iComprobante;
	
	@Autowired
	private IProveedor iProveedor;

	@GetMapping("/")
	public String CmpProveedor(Model model) 
	{
		List<CmpProveedor> listaComprobantesProveedor = iComprobanteProveedor.listarCmpProveedor();
		model.addAttribute("comprobantesProveedor", listaComprobantesProveedor);
		model.addAttribute("todos", listaComprobantesProveedor.size());
		return "/optimus/comprobantesProveedor/cmpProveedor";
	}
	
	@GetMapping("/agregar")
	public String agregarCmpProveedor(Model model, CmpProveedor comprobante_empleado) 
	{
		List<Comprobante> listaComprobantes = iComprobante.listarComprobantesActivos();
		List<Proveedor> listaProveedor = iProveedor.listarProveedoresActivos();
		model.addAttribute("comprobanteProveedor", new CmpProveedor()); 
		model.addAttribute("comprobantes", listaComprobantes);
		model.addAttribute("proveedores", listaProveedor);
		return "/optimus/comprobantesProveedor/registroCmpProveedor";
	}
	
	@GetMapping("/editar")
	public String editarCmpProveedor(Model model, CmpProveedor comprobante_proveedor) 
	{
		comprobante_proveedor = iComprobanteProveedor.buscarCmpProveedor(comprobante_proveedor);
		List<Comprobante> listaComprobantes = iComprobante.listarComprobantesActivos();
		List<Proveedor> listaProveedor = iProveedor.listarProveedoresActivos();
		model.addAttribute("comprobanteProveedor", comprobante_proveedor);
		model.addAttribute("comprobantes", listaComprobantes);
		model.addAttribute("proveedores", listaProveedor);
		return "/optimus/comprobantesProveedor/registroCmpProveedor";
	}	
	
	@GetMapping("/estado")
	public String cambiarEstadoCmpProveedor(Model model, CmpProveedor comprobante_proveedor, RedirectAttributes redirectAttrs) throws Exception 
	{
		try {
			iComprobanteProveedor.cambiarEstadoCmpProveedor(comprobante_proveedor);
			redirectAttrs
				.addFlashAttribute("mensaje", "Se cambiado correctamente el estado de la asignación del comprobante.")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/comprobantesProveedor/";
		}catch(Exception e) {
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/comprobantesProveedor/";
		}
	}	

	@GetMapping("/eliminar")
	public String eliminarCmpProveedor(Model model, CmpProveedor comprobante_proveedor) 
	{
		iComprobanteProveedor.eliminarCmpProveedor(comprobante_proveedor);	
		return "redirect:/optimus/comprobantesProveedor/";
	}

	@PostMapping("/guardar")
	public String guardarCmpProveedor(@Valid CmpProveedor comprobante_proveedor, Errors errores, RedirectAttributes redirectAttrs) 
	{
		try
		{
			iComprobanteProveedor.guardarCmpProveedor(comprobante_proveedor);	
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha asignado correctamente el comprobante")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/comprobantesProveedor/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/comprobantesProveedor/agregar";
		}
		
	}
	
	@GetMapping("/detalle")
	public String detalleCmpProveedor(Model model, CmpProveedor comprobante_proveedor) 
	{
		comprobante_proveedor = iComprobanteProveedor.buscarCmpProveedor(comprobante_proveedor);
		model.addAttribute("comprobanteProveedor", comprobante_proveedor);
		return "/optimus/comprobantesProveedor/detalleCmpProveedor";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
