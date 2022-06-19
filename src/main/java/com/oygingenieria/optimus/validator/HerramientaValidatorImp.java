package com.oygingenieria.optimus.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.dao.HerramientaDao;
import com.oygingenieria.optimus.models.Herramienta;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class HerramientaValidatorImp implements IHerramientaValidator {
	@Autowired
	private HerramientaDao herramientaDao;

	@Override
	public void validator(Herramienta herramienta) throws ApiUnprocessableEntity {
		
		if (herramienta.getNombreHerramienta() == null || herramienta.getNombreHerramienta().isEmpty()) {
			message("El nombre de la herramienta es obligatorio.");
		}
		if (herramienta.getCantidadHerramienta() == null) {
			message("La cantidad de la herramienta es obligatorio.");
		}
		if (herramienta.getCantidadHerramienta() < 1) {
			message("Debe ingresar una cantidad de herramienta mayor a cero(0).");
		}
		if (herramienta.getNombreHerramienta().length() < 3) {
			message("El nombre de la herramienta es muy corto.");
		}
		
		var cont=0;
		for (int i = 0; i < herramienta.getNombreHerramienta().length(); i++) {
			char letra = herramienta.getNombreHerramienta().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z');
			else if(letra==' ');
			else if(letra=='1');
			else if(letra=='2');
			else if(letra=='3');
			else if(letra=='4');
			else if(letra=='5');
			else if(letra=='6');
			else if(letra=='7');
			else if(letra=='8');
			else if(letra=='9');
			else if(letra=='0');
			else {
				cont++;
			}
		}
		if (cont>0) {
			message("La herramienta debe tener solo letras y números en el nombre.");
		}
		

		for (Herramienta h : herramientaDao.findAll()) {
			if (h.getNombreHerramienta().equals(herramienta.getNombreHerramienta())) {
				message("Ya existe una herramienta con este nombre.");
			}
		}
	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}

	@Override
	public void validatorActualizar(Herramienta herramienta) throws ApiUnprocessableEntity {
		
		if (herramienta.getNombreHerramienta() == null || herramienta.getNombreHerramienta().isEmpty()) {
			message("El nombre de la herramienta es obligatorio.");
		}
		if (herramienta.getCantidadHerramienta() == null) {
			message("La cantidad de la herramienta es obligatorio.");
		}
		if (herramienta.getCantidadHerramienta() < 1) {
			message("Debe ingresar una cantidad de herramienta mayor a cero(0).");
		}
		if (herramienta.getNombreHerramienta().length() < 3) {
			message("El nombre de la herramienta es muy corto.");
		}
		
		
		var cont=0;
		for (int i = 0; i < herramienta.getNombreHerramienta().length(); i++) {
			char letra = herramienta.getNombreHerramienta().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z');
			else if(letra==' ');
			else if(letra=='1');
			else if(letra=='2');
			else if(letra=='3');
			else if(letra=='4');
			else if(letra=='5');
			else if(letra=='6');
			else if(letra=='7');
			else if(letra=='8');
			else if(letra=='9');
			else if(letra=='0');
			else {
				cont++;
			}
		}
		if (cont>0) {
			message("La herramienta debe tener solo letras y números en el nombre.");
		}


		for (Herramienta h : herramientaDao.findAll()) {
			if(!(h.getIdHerramienta().equals(herramienta.getIdHerramienta()))) {
				if (h.getNombreHerramienta().equals(herramienta.getNombreHerramienta())) {
					message("Ya existe una herramienta con este nombre.");
				}
			}
		}
		
	}
}
