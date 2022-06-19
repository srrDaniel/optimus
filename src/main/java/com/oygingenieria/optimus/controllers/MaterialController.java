package com.oygingenieria.optimus.controllers;

import com.oygingenieria.optimus.models.Material;
import com.oygingenieria.optimus.services.IMaterial;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/optimus/materiales")
public class MaterialController 
{
    @Autowired
    private IMaterial iMaterial;

    @GetMapping("/")
    public String listarMateriales(Model model) 
    {
        List<Material> listaMateriales = iMaterial.listarMateriales();
		model.addAttribute("materiales", listaMateriales);
		model.addAttribute("todos", listaMateriales.size());
		model.addAttribute("activos", iMaterial.listarMaterialesActivos().size());
		model.addAttribute("inactivos", iMaterial.listarMaterialesInactivos().size());
		return "/optimus/materiales/materiales";
    }
    
    @GetMapping("/agregar")
	public String agregarMaterial(Material material) 
	{
    	return "/optimus/materiales/registroMaterial";
	}
    
    @GetMapping("/editar")
	public String editarMaterial(Model model, Material material) 
	{
		material = iMaterial.buscarMaterial(material);
		model.addAttribute("material", material);
		return "/optimus/materiales/registroMaterial";
	}	
	    
	@GetMapping("/estado")
	public String cambiarEstadoMaterial(Model model, Material material) 
	{
		iMaterial.cambiarEstadoMaterial(material);
		return "redirect:/optimus/materiales/";
	}	

	@GetMapping("/eliminar")
	public String eliminarMaterial(Model model, Material material) 
	{
		iMaterial.eliminarMaterial(material);
		return "redirect:/optimus/materiales/";
	}

	@PostMapping("/guardar")
	public String guardarMaterial(@Valid Material material, Errors errores, RedirectAttributes redirectAttrs) throws ApiUnprocessableEntity
	{
		try
		{
			iMaterial.guardarMaterial(material);
			redirectAttrs
				.addFlashAttribute("mensaje", "Se ha guardado correctamente el material")
				.addFlashAttribute("clase", "success");
			return "redirect:/optimus/materiales/";
		}
		catch(Exception e)
		{
			redirectAttrs
				.addFlashAttribute("mensaje", e.getMessage())
				.addFlashAttribute("clase", "danger");
			return "redirect:/optimus/materiales/agregar";
		}
	}
	
	@PostMapping("/actualizar")
	public String actualizar(@Valid Material material, Errors errores, RedirectAttributes redirectAttrs)
			throws ApiUnprocessableEntity {
		try {
			iMaterial.actualizarMaterial(material);
			redirectAttrs.addFlashAttribute("mensaje", "Se ha actualizado correctamente el material")
					.addFlashAttribute("clase", "success");
			return "redirect:/optimus/materiales/";
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", e.getMessage()).addFlashAttribute("clase", "danger");
			return "redirect:/optimus/materiales/agregar";
		}
	}
	
	
	@GetMapping("/detalle")
	public String detalleMaterial(Model model, Material material) 
	{
		material = iMaterial.buscarMaterial(material);
		model.addAttribute("material", material);
		return "/optimus/materiales/detalleMaterial";
	} 
}
