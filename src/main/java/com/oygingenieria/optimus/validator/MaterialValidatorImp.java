package com.oygingenieria.optimus.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.dao.MaterialDao;
import com.oygingenieria.optimus.models.Material;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class MaterialValidatorImp implements IMaterialValidator {
	@Autowired
	private MaterialDao materialDao;

	@Override
	public void validator(Material material) throws ApiUnprocessableEntity {
		var cont=0;
		if (material.getNombreMaterial() == null || material.getNombreMaterial().isEmpty()) {
			message("El nombre del material es obligatorio.");
		}
		if (material.getCantidadMaterial() == null) {
			message("La cantidad del material es obligatorio.");
		}
		if (material.getCantidadMaterial() < 1) {
			message("Debe ingresar una cantidad de material mayor a cero(0).");
		}
		if (material.getNombreMaterial().length() < 3) {
			message("El nombre del material es muy corto.");
		}
		for (int i = 0; i < material.getNombreMaterial().length(); i++) {
			char letra = material.getNombreMaterial().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z') {
				
			}
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
			message("El campo material solo debe tener letras y numeros");
		}

		for (Material m : materialDao.findAll()) {
			if (m.getNombreMaterial().equals(material.getNombreMaterial())) {
				message("Ya existe un material con este nombre.");
			}
		}
	}
//actualizar validators
	@Override
	public void validatorActualizar(Material material) throws ApiUnprocessableEntity {
		var cont=0;
		if (material.getNombreMaterial() == null || material.getNombreMaterial().isEmpty()) {
			message("El nombre del material es obligatorio.");
		}
		if (material.getCantidadMaterial() == null) {
			message("La cantidad del material es obligatorio.");
		}
		if (material.getCantidadMaterial() < 1) {
			message("Debe ingresar una cantidad de material mayor a cero(0).");
		}
		if (material.getNombreMaterial().length() < 3) {
			message("El nombre del material es muy corto.");
		}
		
		for (int i = 0; i < material.getNombreMaterial().length(); i++) {
			char letra = material.getNombreMaterial().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z') {
				
			}
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
			message("El campo material solo debe tener letras y numeros");
		}

		for (Material m : materialDao.findAll()) {
			if (!(material.getIdMaterial().equals(m.getIdMaterial()))) {
				if (m.getNombreMaterial().equals(material.getNombreMaterial())) {
					message("Ya existe un material con este nombre.");
				}
			}
		}
	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}
}
