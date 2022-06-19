package com.oygingenieria.optimus.controllers;

import com.oygingenieria.optimus.models.Usuario;
import com.oygingenieria.optimus.services.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/optimus/usuarios")
public class UsuarioController 
{

	@Autowired
    private IUsuario iUsuario;

	@GetMapping("/")
	public String usuarios(Model model) 
	{
		List<Usuario> listaUsuarios = iUsuario.listarUsuarios();
		List<Usuario> listaActivos = iUsuario.listarUsuariosActivos();
		List<Usuario> listaInactivos = iUsuario.listarUsuariosInactivos();
		model.addAttribute("usuarios", listaUsuarios);
		model.addAttribute("todos", listaUsuarios.size());
		model.addAttribute("activos", listaActivos.size());
		model.addAttribute("inactivos", listaInactivos.size());
		return "/optimus/usuarios/usuarios";
	}
	
	@GetMapping("/agregar")
	public String agregarUsuario(Usuario usuario) 
	{
		return "/optimus/usuarios/registroUsuario";
	}
	
	@GetMapping("/editar")
	public String editarUsuario(Model model, Usuario usuario) 
	{
		usuario = iUsuario.buscarUsuario(usuario);
		model.addAttribute("usuario", usuario);
		return "/optimus/usuarios/registroUsuario";
	}	
	
	@GetMapping("/estado")
	public String cambiarEstadoUsuario(Model model, Usuario usuario) 
	{
		iUsuario.cambiarEstadoUsuario(usuario);
		return "redirect:/optimus/usuarios/";
	}	

	@GetMapping("/eliminar")
	public String eliminarUsuario(Model model, Usuario usuario) 
	{
		iUsuario.eliminarUsuario(usuario);	
		return "redirect:/optimus/usuarios/";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid Usuario usuario, Errors errores, RedirectAttributes redirectAttrs) {
		String str = "";
		try {
			iUsuario.guardarUsuario(usuario);
			redirectAttrs.addFlashAttribute("mensaje", "Se ha guardado correctamente el usuario")
					.addFlashAttribute("clase", "success");
			str = "redirect:/optimus/usuarios/";
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", e.getMessage()).addFlashAttribute("clase", "danger");
			str = "redirect:/optimus/usuarios/agregar";
		}
		return str;
	}
	
	@PostMapping("/actualizar")
	public String actualizar(@Valid Usuario usuario, Errors errores, RedirectAttributes redirectAttrs) {
		String str = "";
		try {
			iUsuario.actualizarUsuario(usuario);
			redirectAttrs.addFlashAttribute("mensaje", "Se ha actualizado correctamente el usuario")
						.addFlashAttribute("clase", "success");
			str = "redirect:/optimus/usuarios/";
		}catch(Exception e){
			redirectAttrs.addFlashAttribute("mensaje", e.getMessage())
						.addFlashAttribute("clase", "danger");
			str = "redirect:/optimus/usuarios/agregar";
		}
		return str;
	}
	
	
	@GetMapping("/detalle")
	public String detalleUsuario(Model model, Usuario usuario) 
	{
		usuario = iUsuario.buscarUsuario(usuario);
		model.addAttribute("usuario", usuario);
		return "/optimus/usuarios/detalleUsuario";
	}

}
