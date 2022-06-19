package com.oygingenieria.optimus.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.dao.ProveedorDao;
import com.oygingenieria.optimus.models.Proveedor;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class ProveedorValidatorImp implements IProveedorValidator {

	@Autowired
	private ProveedorDao proveedorDao;

	@Override
	public void validator(Proveedor proveedor) throws ApiUnprocessableEntity {

		// Validaciones de NIT
		for (Proveedor prov : proveedorDao.findAll()) {
			if (prov.getNitProveedor().equals(proveedor.getNitProveedor())) {
				message("El NIT ingresado ya se encuentra registrado en el sistema");
			}
		}
		
		if (proveedor.getNitProveedor() == null || proveedor.getNitProveedor().toString().isEmpty()) {
			message("El campo NIT no puede ir vacío");
		}
		
		int guion = 0;
		int cont = 0;
		for(int i=0;i<proveedor.getNitProveedor().length();i++) {
			char letra = proveedor.getNitProveedor().charAt(i);
			if(letra=='0');
			else if(letra=='1');
			else if(letra=='2');
			else if(letra=='3');
			else if(letra=='4');
			else if(letra=='5');
			else if(letra=='6');
			else if(letra=='7');
			else if(letra=='8');
			else if(letra=='9');
			else if(letra=='-') guion++;
			else
				cont++;
		}
		if (guion > 1 || cont > 0) {
			message("El campo NIT no puede tener letras, ni caracteres especiales. Solo números, un guión y sin espacios.");
		}
		if(proveedor.getNitProveedor().charAt(0)=='-' || proveedor.getNitProveedor().charAt(proveedor.getNitProveedor().length()-1)=='-')
			message("El campo NIT no puede empezar o terminar con el guión.");

		// Validaciones de Nombre
		if (proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().isEmpty()) {
			message("El campo nombre no puede ir vacío.");
		}
	
		if (proveedor.getNombreProveedor().length() < 3) {
			message("El nombre debe tener al menos 3 caracteres.");
		}
	
		if (proveedor.getNombreProveedor().length() > 50) {
			message("El nombre es demasiado largo, debe contener maxímo 50 caracteres.");
		}
		
		boolean nombre = false;
		cont = 0;
		for (int i=0; i < proveedor.getNombreProveedor().length(); i++) {
			char a = proveedor.getNombreProveedor().charAt(i);
			if(a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z') {
				nombre = true;
				cont++;
			} 
		}
		if (!nombre && cont<4) {
			message("El proveedor debe tener mínimo 3 letras en el nombre.");
		}
	
		// Validaciones de Contacto
		if (proveedor.getContactoProveedor() == null || proveedor.getContactoProveedor().isEmpty()) {
			message("El campo contacto del proveedor no puede ir vacío.");
		}
	
		if (proveedor.getContactoProveedor().length() < 3) {
			message("El contacto debe tener al menos 3 carácteres.");
		}
		
		cont = 0;
		int contEspacio=0;
		boolean str = false;
		for (int i=0; i < proveedor.getContactoProveedor().length(); i++) {
			char a = proveedor.getContactoProveedor().charAt(i);
			if(a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z') {
				str = true;
			}else {
				if(a!=' ')
					cont++;
				else
					contEspacio++;
			}
		}
		if (!str) {
			message("El campo contacto del proveedor solo puede contener letras.");
		}
		if(contEspacio>2)
			message("El campo contacto de proveedor solo puede contener tres espacios.");
		if(cont>0)
			message("El contacto del empleado solo puede tener letras.");
		
		if (proveedor.getContactoProveedor().length() > 100) {
			message("El contacto del proveedor es demasiado largo, debe contener maxímo 100 carácteres.");
		}
	
		// Validaciones de Telefóno
		if (proveedor.getTelefonoProveedor() == null || proveedor.getTelefonoProveedor().toString().isEmpty()) {
			message("El campo telefono no puede ir vacío.");
		}
	
		if (proveedor.getTelefonoProveedor().toString().length() < 7) {
			message("El número telefónico debe contener al menos 7 dígitos.");
		}

		// Validaciones Correo Electronico
		if (proveedor.getCorreoProveedor() == null || proveedor.getCorreoProveedor().isEmpty()) {
			message("El campo correo no puede ir vacío.");
		}

		if (proveedor.getCorreoProveedor().length() > 100) {
			message("El correo es demasiado largo, debe contener maxímo 100 caracteres.");
		}

		// Validaciones Dirección
		if (proveedor.getDireccionProveedor() == null || proveedor.getDireccionProveedor().isEmpty()) {
			message("El campo dirección no puede ir vacío.");
		}

		if (proveedor.getDireccionProveedor().length() < 4) {
			message("La dirección es demasiado corta, debe ingresar una dirección valida.");
		}

		if (proveedor.getDireccionProveedor().length() > 100) {
			message("La dirección no puede exceder los 100 caracteres.");
		}
	}

	@Override
	public void validatorActualizar(Proveedor proveedor) throws ApiUnprocessableEntity {
		

		// Validaciones de NIT
		for (Proveedor prov : proveedorDao.findAll()) {
			if(!(prov.getIdProveedor().equals(proveedor.getIdProveedor()))) {
				if (prov.getNitProveedor().equals(proveedor.getNitProveedor())) {
					message("El NIT ingresado ya se encuentra registrado en el sistema");
				}
			}
		}
		
		if (proveedor.getNitProveedor() == null || proveedor.getNitProveedor().toString().isEmpty()) {
			message("El campo NIT no puede ir vacío");
		}
		
		int guion = 0;
		int cont = 0;
		for(int i=0;i<proveedor.getNitProveedor().length();i++) {
			char letra = proveedor.getNitProveedor().charAt(i);
			if(letra=='0');
			else if(letra=='1');
			else if(letra=='2');
			else if(letra=='3');
			else if(letra=='4');
			else if(letra=='5');
			else if(letra=='6');
			else if(letra=='7');
			else if(letra=='8');
			else if(letra=='9');
			else if(letra=='-') guion++;
			else
				cont++;
		}
		if (guion > 1 || cont > 0) {
			message("El campo NIT no puede tener letras, ni caracteres especiales. Solo números, un guión y sin espacios.");
		}
		if(proveedor.getNitProveedor().charAt(0)=='-' || proveedor.getNitProveedor().charAt(proveedor.getNitProveedor().length()-1)=='-')
			message("El campo NIT no puede empezar o terminar con el guión.");

		// Validaciones de Nombre
		if (proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().isEmpty()) {
			message("El campo nombre no puede ir vacío.");
		}

		if (proveedor.getNombreProveedor().length() < 3) {
			message("El nombre debe tener al menos 3 caracteres.");
		}

		if (proveedor.getNombreProveedor().length() > 50) {
			message("El nombre es demasiado largo, debe contener maxímo 50 caracteres.");
		}
		
		boolean nombre = false;
		cont = 0;
		for (int i=0; i < proveedor.getNombreProveedor().length(); i++) {
			char a = proveedor.getNombreProveedor().charAt(i);
			if(a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z') {
				nombre = true;
				cont++;
			} 
		}
		if (!nombre && cont<4) {
			message("El proveedor debe tener mínimo 3 letras en el nombre.");
		}

		// Validaciones de Contacto
				if (proveedor.getContactoProveedor() == null || proveedor.getContactoProveedor().isEmpty()) {
					message("El campo contacto del proveedor no puede ir vacío.");
				}
			
				if (proveedor.getContactoProveedor().length() < 3) {
					message("El contacto debe tener al menos 3 carácteres.");
				}
				
				cont = 0;
				int contEspacio=0;
				boolean str = false;
				for (int i=0; i < proveedor.getContactoProveedor().length(); i++) {
					char a = proveedor.getContactoProveedor().charAt(i);
					if(a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z') {
						str = true;
					}else {
						if(a!=' ')
							cont++;
						else
							contEspacio++;
					}
				}
				if (!str) {
					message("El campo contacto del proveedor solo puede contener letras.");
				}
				if(contEspacio>2)
					message("El campo contacto de proveedor solo puede contener tres espacios.");
				if(cont>0)
					message("El contacto del proveedor solo puede tener letras.");
				
				if (proveedor.getContactoProveedor().length() > 100) {
					message("El contacto del proveedor es demasiado largo, debe contener maxímo 100 carácteres.");
				}
			
		// Validaciones de Telefóno
		if (proveedor.getTelefonoProveedor() == null || proveedor.getTelefonoProveedor().toString().isEmpty()) {
			message("El campo telefono no puede ir vacío.");
		}

		if (proveedor.getTelefonoProveedor().toString().length() < 7) {
			message("El número telefónico debe contener al menos 7 dígitos.");
		}

		// Validaciones Correo Electronico
		if (proveedor.getCorreoProveedor() == null || proveedor.getCorreoProveedor().isEmpty()) {
			message("El campo correo no puede ir vacío.");
		}

		if (proveedor.getCorreoProveedor().length() > 100) {
			message("El correo es demasiado largo, debe contener maxímo 100 caracteres.");
		}

		// Validaciones Dirección
		if (proveedor.getDireccionProveedor() == null || proveedor.getDireccionProveedor().isEmpty()) {
			message("El campo dirección no puede ir vacío.");
		}

		if (proveedor.getDireccionProveedor().length() < 4) {
			message("La dirección es demasiado corta, debe ingresar una dirección valida.");
		}

		if (proveedor.getDireccionProveedor().length() > 100) {
			message("La dirección no puede exceder los 100 caracteres.");
		}

	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}
}
