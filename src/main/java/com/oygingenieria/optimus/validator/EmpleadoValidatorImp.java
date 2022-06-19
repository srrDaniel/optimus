package com.oygingenieria.optimus.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.dao.EmpleadoDao;
import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class EmpleadoValidatorImp implements IEmpleadoValidator {
	@Autowired
	EmpleadoDao empleadoDAO;

	@Override
	public void validator(Empleado empleado) throws ApiUnprocessableEntity {

		boolean str = false;

		//Validaciones para el nombre del empleado
		if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isEmpty()) {
			message("El nombre es un campo obligatorio.");
		}

		if (empleado.getNombreEmpleado().length() < 3) {
			message("El nombre es demasiado corto, mínimo debe contener 3 letras.");
		}

		var cont = 0;
		var contEspacio=0;
		for (int i=0; i < empleado.getNombreEmpleado().length(); i++) {
			char a = empleado.getNombreEmpleado().charAt(i);
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
			message("El empleado debe tener letras en el nombre.");
		}
		if(contEspacio>1)
			message("El nombre del empleado solo puede tener un espacio.");
		if(cont>0)
			message("El nombre del empleado solo puede tener letras.");
		
		//Validaciones para el apellido
		if (empleado.getApellidoEmpleado() == null || empleado.getApellidoEmpleado().isEmpty()) {

			message("El apellido es un campo obligatorio.");
		}
		if (empleado.getApellidoEmpleado().length() < 3) {
			message("El apellido es demasiado corto, debe tener mínimo 3 letras.");
		}
		
		cont = 0;
		contEspacio=0;
		str = false;
		for (int i=0; i < empleado.getApellidoEmpleado().length(); i++) {
			char a = empleado.getApellidoEmpleado().charAt(i);
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
			message("El empleado debe tener letras en el apellido.");
		}
		if(contEspacio>2)
			message("El apellido del empleado solo puede tener un espacio.");
		if(cont>0)
			message("El apellido del empleado solo puede tener letras.");
		
		//Validaciones para número de documento
		for (Empleado em : empleadoDAO.findAll()) {
			if (em.getDocumentoEmpleado().equals(empleado.getDocumentoEmpleado())) {
				message("Este número de documento ya está registrado en el sistema.");
			}
		}
		if (empleado.getDocumentoEmpleado() == null) {
			message("El campo del número de documento de identidad del empleado es obligatorio.");
		}
		
		if (empleado.getDocumentoEmpleado().toString().charAt(0) == '0')
			message("Es obligatorio que el número de documento no inicie con un cero.");


		//Validaciones para la edad
		if (empleado.getEdadEmpleado() < 18) {
			message("La edad debe ser superior a los 18 años de edad.");
		}
		if (empleado.getEdadEmpleado() == null) {
			message("El campo edad está vacío.");
		}

		//Validaciones para el correo
		if (empleado.getCorreoEmpleado() == "") {
			message("El campo del correo electrónico no debe ir vacío.");
		}
		if (empleado.getCorreoEmpleado().length() > 100) {
			message("El campo correo electrónico del empleado no debe contener más de 100 caracteres.");
		}

		//Validaciones para la fecha de ingreso
		if (empleado.getFechaIngresoEmpleado() == null) {
			message("El campo de la fecha de ingreso del empleado es requerido.");
		}
		
		//Validaciones para la Dirección del empleado
		if (empleado.getDireccionEmpleado() == "") {
			message("La dirección del empleado es requerida.");
		}
		
		var contLetra=0;
		for (int i=0; i < empleado.getDireccionEmpleado().length(); i++) {
			char a = empleado.getDireccionEmpleado().charAt(i);
			if(a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z') {
				contLetra++;
			}
		}
		if(contLetra<2)
			message("La dirección del empleado debe contener mínimo dos letras");
				
		//Validaciones para el salario
		if (empleado.getSalarioEmpleado() == null) {
			message("El campo Salario del empleado es requerido.");
		}
		
		if (empleado.getSalarioEmpleado()<1) {
			message("El salario del empleado debe ser un número mayor a cero(0).");
		}
		
		//Validaciones para la ARL
		if (empleado.getArlEmpleado() == "") {
			message("El campo ARL es requerido.");
		}
		//Validaciones para la EPS
		if (empleado.getEpsEmpleado() == "") {
			message("El campo EPS es requerido.");
		}
		//Validaciones para el tipo de documento
		if (empleado.getTipoDocumentoEmpleado() == "") {
			message(("El campo tipo de documento del empleado es requerido."));
		}
		

	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}

	@Override
	public void validatorActualizar(Empleado empleado) throws ApiUnprocessableEntity {
		boolean str = false;

		//Validaciones para el nombre del empleado
		if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isEmpty()) {
			message("El nombre es un campo obligatorio.");
		}

		if (empleado.getNombreEmpleado().length() < 3) {
			message("El nombre es demasiado corto, mínimo debe contener 3 letras.");
		}

		var cont = 0;
		var contEspacio=0;
		for (int i=0; i < empleado.getNombreEmpleado().length(); i++) {
			char a = empleado.getNombreEmpleado().charAt(i);
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
			message("El empleado debe tener letras en el nombre.");
		}
		if(contEspacio>1)
			message("El nombre del empleado solo puede tener un espacio.");
		if(cont>0)
			message("El nombre del empleado solo puede tener letras.");
		
		
		//Validaciones para el apellido
		if (empleado.getApellidoEmpleado() == null || empleado.getApellidoEmpleado().isEmpty()) {

			message("El apellido es un campo obligatorio.");
		}
		if (empleado.getApellidoEmpleado().length() < 3) {
			message("El apellido es demasiado corto, debe tener mínimo 3 letras.");
		}
		
		cont = 0;
		contEspacio=0;
		str = false;
		for (int i=0; i < empleado.getApellidoEmpleado().length(); i++) {
			char a = empleado.getApellidoEmpleado().charAt(i);
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
			message("El empleado debe tener letras en el apellido.");
		}
		if(contEspacio>1)
			message("El apellido del empleado solo puede tener un espacio.");
		if(cont>0)
			message("El apellido del empleado solo puede tener letras.");
		
		//Validaciones para número de documento
		for (Empleado em : empleadoDAO.findAll()) {
			if(!(em.getIdEmpleado().equals(empleado.getIdEmpleado()))){
				if (em.getDocumentoEmpleado().equals(empleado.getDocumentoEmpleado())) {
					message("Este número de documento ya está registrado en el sistema.");
				}
			}
		}
		if (empleado.getDocumentoEmpleado() == null) {
			message("El campo del número de documento de identidad del empleado es obligatorio.");
		}
		
		if (empleado.getDocumentoEmpleado().toString().charAt(0) == '0')
			message("Es obligatorio que el número de documento no inicie con un cero.");


		//Validaciones para la edad
		if (empleado.getEdadEmpleado() < 18) {
			message("La edad debe ser superior a los 18 años de edad.");
		}
		if (empleado.getEdadEmpleado() == null) {
			message("El campo edad está vacío.");
		}

		//Validaciones para el correo
		if (empleado.getCorreoEmpleado() == "") {
			message("El campo del correo electrónico no debe ir vacío.");
		}
		if (empleado.getCorreoEmpleado().length() > 100) {
			message("El campo correo electrónico del empleado no debe contener más de 100 caracteres.");
		}

		//Validaciones para la fecha de ingreso
		if (empleado.getFechaIngresoEmpleado() == null) {
			message("El campo de la fecha de ingreso del empleado es requerido.");
		}
		
		//Validaciones para el salario
		if (empleado.getSalarioEmpleado() == null) {
			message("El campo Salario del empleado es requerido.");
		}
		
		if (empleado.getSalarioEmpleado()<1) {
			message("El salario del empleado debe ser un número mayor a cero(0).");
		}
		
		//Validaciones para la Dirección del empleado
		if (empleado.getDireccionEmpleado() == "") {
			message("La dirección del empleado es requerida.");
		}
		
		var contLetra=0;
		for (int i=0; i < empleado.getDireccionEmpleado().length(); i++) {
			char a = empleado.getDireccionEmpleado().charAt(i);
			if(a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z') {
				contLetra++;
			}
		}
		if(contLetra<2)
			message("La dirección del empleado debe contener mínimo dos letras");
				
		
		//Validaciones para la ARL
		if (empleado.getArlEmpleado() == "") 
			message("El campo ARL es requerido.");
		
		//Validaciones para la EPS
		if (empleado.getEpsEmpleado() == "") {
			message("El campo EPS es requerido.");
		}
		//Validaciones para el tipo de documento
		if (empleado.getTipoDocumentoEmpleado() == "") {
			message(("El campo tipo de documento del empleado es requerido."));
		}
	}
}
