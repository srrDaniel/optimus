package com.oygingenieria.optimus.controllers;

import com.oygingenieria.optimus.models.dto.EmpleadoDTO;
import com.oygingenieria.optimus.models.Material;
import com.oygingenieria.optimus.services.IEmpleado;
import com.oygingenieria.optimus.services.IMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/optimus")
public class DashboardController 
{
	@Autowired
    private IEmpleado iEmpleado;
	
	@Autowired
    private IMaterial iMaterial;
	
	@GetMapping("/")
	public String dashboard(Model model) throws ParseException 
	{
		List<EmpleadoDTO> empleadosdto = iEmpleado.alertas();
		List<Material> materiales = iMaterial.alertaMateriales();
		model.addAttribute("empleadosdto", empleadosdto);
		model.addAttribute("materiales", materiales);
		model.addAttribute("numEmpleados", empleadosdto.size());
		model.addAttribute("numMateriales", materiales.size());
		return "/optimus/dashboard";
	}
	
}