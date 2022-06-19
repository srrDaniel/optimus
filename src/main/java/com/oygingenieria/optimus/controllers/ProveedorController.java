package com.oygingenieria.optimus.controllers;

import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.models.Proveedor;
import com.oygingenieria.optimus.services.ICmpProveedor;
import com.oygingenieria.optimus.services.IProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/optimus/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedor iProveedor;
    
	@Autowired
	private ICmpProveedor iCmpProveedor;

	@GetMapping("/")
	public String proveedores(Model model) 
	{
		List<Proveedor> listaProveedores = iProveedor.listarProveedores();
		model.addAttribute("proveedores", listaProveedores);
		model.addAttribute("todos", listaProveedores.size());
		model.addAttribute("activos", iProveedor.ProveedoresActivos().size());
		model.addAttribute("inactivos", iProveedor.ProveedoresInactivos().size());
		return "/optimus/proveedores/proveedores";
	}
	
	@GetMapping("/verComprobantes")
	public String verCmpProveedor(Model model, Proveedor proveedor) 
	{
		proveedor = iProveedor.buscarProveedor(proveedor);
		List<CmpProveedor> listaComprobantes = iCmpProveedor.listarCmpProveedor(proveedor);
		model.addAttribute("comprobantesProveedor", listaComprobantes);
		model.addAttribute("proveedor", proveedor);
		return "/optimus/proveedores/verComprobantes";
	}
	
	@GetMapping("/agregar")
	public String agregarProveedor(Proveedor proveedor) 
	{
		return "/optimus/proveedores/registroProveedor";
	}
	
	@GetMapping("/editar")
	public String editarProveedor(Model model, Proveedor proveedor) 
	{
		proveedor = iProveedor.buscarProveedor(proveedor);
		model.addAttribute("proveedor", proveedor);
		return "/optimus/proveedores/registroProveedor";
	}	
	
	@GetMapping("/estado")
	public String cambiarEstadoProveedor(Model model, Proveedor proveedor) 
	{
		iProveedor.cambiarEstadoProveedor(proveedor);
		return "redirect:/optimus/proveedores/";
	}	

	@GetMapping("/eliminar")
	public String eliminarProveedor(Proveedor proveedor, Model model) 
	{
		iProveedor.eliminarProveedor(proveedor);	
		return "redirect:/optimus/proveedores/";
	}

	@PostMapping("/guardar")
	public String guardarProveedor(@Valid Proveedor proveedor, Errors errores, RedirectAttributes redirectAttrs) 
	{
		try
		{
			iProveedor.guardarProveedor(proveedor);	
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha guardado correctamente el proveedor")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/proveedores/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
		}
		return "redirect:/optimus/proveedores/agregar";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(@Valid Proveedor proveedor, Errors errores, RedirectAttributes redirectAttrs) {
		String str = "";
		try {
			iProveedor.actualizarProveedor(proveedor);
			redirectAttrs.addFlashAttribute("mensaje", "Se ha actualizado correctamente el proveedor")
					.addFlashAttribute("clase", "success");
			str = "redirect:/optimus/proveedores/";
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", e.getMessage()).addFlashAttribute("clase", "danger");
			str = "redirect:/optimus/proveedores/agregar";
		}
		return str;
	}
	
	
	@GetMapping("/detalle")
	public String detalleProveedor(Model model, Proveedor proveedor) 
	{
		proveedor = iProveedor.buscarProveedor(proveedor);
		model.addAttribute("proveedor", proveedor);
		return "/optimus/proveedores/detalleProveedor";
	}
}