package com.oygingenieria.optimus.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oygingenieria.optimus.dao.ComprobanteDao;
import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;

@Component
public class ComprobanteValidatorImp implements IComprobanteValidator {
	@Autowired
	private ComprobanteDao comprobanteDao;

	@Override
	public void validator(Comprobante comprobante) throws ApiUnprocessableEntity {

		// Validamos que ninguno de los campos obligatorios quede vacío
		if (comprobante.getConceptoComprobante() == null || comprobante.getConceptoComprobante().isEmpty()) {
			message("El concepto del comprobante no puede estar vacío.");
		}
		if (comprobante.getFechaComprobante() == null || comprobante.getFechaComprobante().toString().isEmpty()) {
			message("El campo 'Fecha' no puede quedar vacío, debes ingresar la fecha en la que se generó el comprobante.");
		}
		if (comprobante.getIvaComprobante() == null || comprobante.getIvaComprobante().toString().isEmpty()) {
			message("El campo 'IVA' no puede quedar vacío.");
		}
		if (comprobante.getNFacturaComprobante() == null) {
			message("El número de la factura es obligatorio.");
		}
		
		
		/*
		if (comprobante.getIdentificacionComprobante() == null
				|| comprobante.getIdentificacionComprobante().isEmpty()) {
			message("No puedes dejar el campo de Identificación de la persona asociada al comprobante en blanco.");
		}
		*/
		
		if (comprobante.getNumeroComprobante() == null || comprobante.getNumeroComprobante().toString().isEmpty()) {
			message("No puedes dejar el campo de 'Número de comprobante' vacío.");
		}
		if (comprobante.getPorcentajeIvaComprobante() == null
				|| comprobante.getPorcentajeIvaComprobante().toString().isEmpty()) {
			message("El campo de Porcentaje de IVA no puede ir vacío.");
		}
		if (comprobante.getPorcentajeRetencionComprobante() == null
				|| comprobante.getPorcentajeRetencionComprobante().toString().isEmpty()) {
			message("El campo de Porcentaje de retención no puede ir vacío.");
		}
		if (comprobante.getRetencionComprobante() == null
				|| comprobante.getRetencionComprobante().toString().isEmpty()) {
			message("El campo Retención no puede ir vacío.");
		}
		if (comprobante.getSubTotalComprobante() == null || comprobante.getSubTotalComprobante().toString().isEmpty()) {
			message("No puedes dejar el campo de subtotal vacío.");
		}
		if (comprobante.getTipoPagoComprobante() == null || comprobante.getTipoPagoComprobante().isEmpty()) {
			message("No puedes dejar el Tipo de pago del comprobante vacío.");
		}
		if (comprobante.getValorNetoComprobante() == null
				|| comprobante.getValorNetoComprobante().toString().isEmpty()) {
			message("El Valor Neto del comprobante no puede ir vacío.");
		}
		if (comprobante.getTipoComprobante() == null || comprobante.getTipoComprobante().isEmpty()) {
			message("El campo de tipo de comprobante no puede ir vacío.");
		}
		if (comprobante.getCiudadComprobante() == null || comprobante.getCiudadComprobante().isEmpty()) {
			message("No puedes dejar el campo de ciudad vacío.");
		}
		if (comprobante.getEstadoComprobante() == null || comprobante.getEstadoComprobante().isEmpty()) {
			message("No puedes ingresar un estado de comprobante vacío.");
		}

		// Validaciones para el campo 'Concepto'
		if (comprobante.getConceptoComprobante().length() < 4) {
			message("El campo concepto debe tener por lo menos 4 caracteres.");
		}
		if (comprobante.getConceptoComprobante().length() > 254) {
			message("El campo concepto no puede exceder los 250 caracteres permitidos.");
		}

		// Validaciones para el campo 'IVA'
		if (comprobante.getIvaComprobante() < 0) {
			message("No puedes ingresar un valor negativo para el IVA.");
		}

		if (!(comprobante.getIvaComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo IVA.");
		}

		// Validaciones para el campo Número de Factura.
		if (comprobante.getNFacturaComprobante().toString().length() < 3) {
			message("Es obligatorio que el número de la factura tenga por lo menos 3 caracteres.");
		}
		if (comprobante.getNFacturaComprobante() < 0) {
			message("El número de factura que ingresaste no puede ser una cantidad negativa.");
		}
		if (!(comprobante.getNFacturaComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo Número de Factura.");
		}
		for (Comprobante c : comprobanteDao.findAll()) {
			if (c.getNFacturaComprobante().equals(comprobante.getNFacturaComprobante())) {
				message("Ya existe un comprobante en el sistema con este Número de Factura.");
			}
		}

		/*
		// Validaciones para el campo Identificación
		if (comprobante.getIdentificacionComprobante().length() < 4) {
			message("El campo Identificación debe tener como mínimo 4 caracteres.");
		}
		if (comprobante.getIdentificacionComprobante().length() > 50) {
			message("El campo Identificación no puede exceder los 50 caracteres permitidos.");
		}
		
		int guion = 0;
		int cont = 0;
		for (int i = 0; i < comprobante.getIdentificacionComprobante().length(); i++) {
			char letra = comprobante.getIdentificacionComprobante().charAt(i);
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
		if(comprobante.getIdentificacionComprobante().charAt(0)=='-' || comprobante.getIdentificacionComprobante().charAt(comprobante.getIdentificacionComprobante().length()-1)=='-')
			message("El campo identificación no puede empezar con el guión.");
		if (guion > 1 || cont > 0) {
			message("El campo Identificación no puede tener letras. Solo números, un guión y sin espacios.");
		}
		*/

		// Validaciones para el campo de Número de comprobante
		if (comprobante.getNumeroComprobante() < 0) {
			message("El número de comprobante no puede ser una cantidad negativa.");
		}
		if (!(comprobante.getNumeroComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo Número de Comprobante.");
		}
		for (Comprobante c : comprobanteDao.findAll()) {
			if (c.getNumeroComprobante().equals(comprobante.getNumeroComprobante())) {
				message("Ya existe un comprobante en el sistema con este mismo Número de Comprobante.");
			}
		}

		// Validaciones para el campo Porcentaje de IVA
		try {
			Double.parseDouble(comprobante.getPorcentajeIvaComprobante().toString());
		} catch (Exception e) {
			message("Es obligatorio ingresar un número decimal en el campo Porcentaje de IVA");
		}
		if (!(comprobante.getPorcentajeIvaComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo Porcentaje de IVA.");
		}

		// validaciones para el campo Porcentaje de Retención
		try {
			Double.parseDouble(comprobante.getPorcentajeRetencionComprobante().toString());
		} catch (Exception e) {
			message("Es obligatorio ingresar un número decimal en el campo Porcentaje de Retención");
		}
		if (!(comprobante.getPorcentajeRetencionComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo Porcentaje de Retención.");
		}

		
		/*
		// Validaciones para el campo Recibido de y pagado A
		if (comprobante.getRecibidoDeComprobante() == "" && comprobante.getPagadoAComprobante() == "") {
			message("Es obligatorio indicar de quién viene el comprobate económico o hacia quién va dirigido (Llenar el campo Pagado a o Recibido de).");
		}

		if (comprobante.getRecibidoDeComprobante().length() > 100
				|| comprobante.getPagadoAComprobante().length() > 100) {
			message("El campo 'Recibido de' y el campo 'Pagado A' no pueden exceder los 100 caracteres permitidos.");
		}
		*/

		// Validaciones para el campo Retención
		if (!(comprobante.getRetencionComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo caracteres numéricos en el campo Retención.");
		}
		if (comprobante.getPorcentajeRetencionComprobante() < 0) {
			message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo Retención.");
		}

		// Validaciones para el campo Subtotal
		if (!(comprobante.getSubTotalComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo caracteres numéricos en el campo Subtotal.");
		}
		if (comprobante.getSubTotalComprobante() < 0) {
			message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo subtotal.");
		}

		// Validaciones para el campo tipo de pago
		boolean pago = true;
		for (int i = 0; i < comprobante.getTipoPagoComprobante().length(); i++) {
			char letra = comprobante.getTipoPagoComprobante().charAt(i);
			if (!(letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z')) {
				pago = false;
			}
		}
		if (!pago) {
			message("El campo Tipo de Pago no puede contener números.");
		}
		if (comprobante.getTipoPagoComprobante().length() < 4) {
			message("Es obligatorio que el campo Tipo de Pago contenga por lo menos 4 caracteres.");
		}
		
		/*
		if (comprobante.getRecibidoDeComprobante().length() > 40) {
			message("El campo 'Tipo de pago' no puede exceder los 40 caracteres permitidos.");
		}
		 */

		// Validaciones para el campo Valor Neto
		if (!(comprobante.getValorNetoComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo caracteres numéricos en el campo Valor Neto.");
		}
		if (comprobante.getValorNetoComprobante() < 0) {
			message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo Valor Neto.");
		}

		// Validaciones para el campo Tipo Comprobante
		boolean tipo = true;
		for (int i = 0; i < comprobante.getTipoComprobante().length(); i++) {
			char letra = comprobante.getTipoComprobante().charAt(i);
			if (!(letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z')) {
				tipo = false;
			}
		}
		if (!tipo) {
			message("El campo Tipo de Comprobante no puede contener números.");
		}
		if (comprobante.getTipoComprobante().length() < 4) {
			message("Es obligatorio que el campo Tipo de Comprobante contenga por lo menos 4 caracteres.");
		}

		if (comprobante.getTipoComprobante().length() > 40) {
			message("Es obligatorio que el campo Tipo de Comprobante no exceda los 40 caracteres permitidos.");
		}

		// Validaciones para el campo Ciudad
		boolean ciudad = false;
		var contEspecial = 0;
		for (int i = 0; i < comprobante.getCiudadComprobante().length(); i++) {
			char letra = comprobante.getCiudadComprobante().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z') {
				ciudad = true;
			}else {
				if(letra!=' ') {
					contEspecial++;
				}
			}
		}
		if (!ciudad || contEspecial>0) {
			message("El campo Ciudad solo puede tener letras y espacios.");
		}
		if (comprobante.getCiudadComprobante().length() < 4) {
			message("Es obligatorio que el campo Ciudad contenga por lo menos 4 caracteres.");
		}
		if (comprobante.getCiudadComprobante().length() > 40) {
			message("El campo 'Ciudad' no puede exceder los 40 caracteres permitidos.");
		}

	}

	@Override
	public void validatorActualizar(Comprobante comprobante) throws ApiUnprocessableEntity {

		// Validamos que ninguno de los campos obligatorios quede vacío
		if (comprobante.getConceptoComprobante() == null || comprobante.getConceptoComprobante().isEmpty()) {
			message("El concepto del comprobante no puede estar vacío.");
		}
		if (comprobante.getIvaComprobante() == null || comprobante.getIvaComprobante().toString().isEmpty()) {
			message("El campo 'IVA' no puede quedar vacío.");
		}
		if (comprobante.getFechaComprobante() == null || comprobante.getFechaComprobante().toString().isEmpty()) {
			message("El campo 'Fecha' no puede quedar vacío, debes ingresar la fecha en la que se generó el comprobante.");
		}
		if (comprobante.getNFacturaComprobante() == null) {
			message("El número de la factura es obligatorio.");
		}
		
		
		/*
		if (comprobante.getIdentificacionComprobante() == null
				|| comprobante.getIdentificacionComprobante().isEmpty()) {
			message("No puedes dejar el campo de Identificación de la persona asociada al comprobante en blanco.");
		}
		*/
		
		if (comprobante.getNumeroComprobante() == null || comprobante.getNumeroComprobante().toString().isEmpty()) {
			message("No puedes dejar el campo de 'Número de comprobante' vacío.");
		}
		if (comprobante.getPorcentajeIvaComprobante() == null
				|| comprobante.getPorcentajeIvaComprobante().toString().isEmpty()) {
			message("El campo de Porcentaje de IVA no puede ir vacío.");
		}
		if (comprobante.getPorcentajeRetencionComprobante() == null
				|| comprobante.getPorcentajeRetencionComprobante().toString().isEmpty()) {
			message("El campo de Porcentaje de retención no puede ir vacío.");
		}
		if (comprobante.getRetencionComprobante() == null
				|| comprobante.getRetencionComprobante().toString().isEmpty()) {
			message("El campo Retención no puede ir vacío.");
		}
		if (comprobante.getSubTotalComprobante() == null || comprobante.getSubTotalComprobante().toString().isEmpty()) {
			message("No puedes dejar el campo de subtotal vacío.");
		}
		if (comprobante.getTipoPagoComprobante() == null || comprobante.getTipoPagoComprobante().isEmpty()) {
			message("No puedes dejar el Tipo de pago del comprobante vacío.");
		}
		if (comprobante.getValorNetoComprobante() == null
				|| comprobante.getValorNetoComprobante().toString().isEmpty()) {
			message("El Valor Neto del comprobante no puede ir vacío.");
		}
		if (comprobante.getTipoComprobante() == null || comprobante.getTipoComprobante().isEmpty()) {
			message("El campo de tipo de comprobante no puede ir vacío.");
		}
		if (comprobante.getCiudadComprobante() == null || comprobante.getCiudadComprobante().isEmpty()) {
			message("No puedes dejar el campo de ciudad vacío.");
		}
		if (comprobante.getEstadoComprobante() == null || comprobante.getEstadoComprobante().isEmpty()) {
			message("No puedes ingresar un estado de comprobante vacío.");
		}

		// Validaciones para el campo 'Concepto'
		if (comprobante.getConceptoComprobante().length() < 4) {
			message("El campo concepto debe tener por lo menos 4 caracteres.");
		}
		if (comprobante.getConceptoComprobante().length() > 254) {
			message("El campo concepto no puede exceder los 250 caracteres permitidos.");
		}

		// Validaciones para el campo 'IVA'
		if (comprobante.getIvaComprobante() < 0) {
			message("No puedes ingresar un valor negativo para el IVA.");
		}

		if (!(comprobante.getIvaComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo IVA.");
		}

		// Validaciones para el campo Número de Factura.
		if (comprobante.getNFacturaComprobante().toString().length() < 3) {
			message("Es obligatorio que el número de la factura tenga por lo menos 3 caracteres.");
		}
		if (comprobante.getNFacturaComprobante() < 0) {
			message("El número de factura que ingresaste no puede ser una cantidad negativa.");
		}
		if (!(comprobante.getNFacturaComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo Número de Factura.");
		}
		for (Comprobante c : comprobanteDao.findAll()) {
			if (!(comprobante.getIdComprobante().equals(c.getIdComprobante())))
				if (c.getNFacturaComprobante().equals(comprobante.getNFacturaComprobante()))
					message("Ya existe un comprobante en el sistema con este Número de Factura.");
		}

		/*
		// Validaciones para el campo Identificación
		if (comprobante.getIdentificacionComprobante().length() < 4) {
			message("El campo Identificación debe tener como mínimo 4 caracteres.");
		}
		if (comprobante.getIdentificacionComprobante().length() > 50) {
			message("El campo Identificación no puede exceder los 50 caracteres permitidos.");
		}
		
		int guion = 0;
		int cont = 0;
		for (int i = 0; i < comprobante.getIdentificacionComprobante().length(); i++) {
			char letra = comprobante.getIdentificacionComprobante().charAt(i);
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
		if(comprobante.getIdentificacionComprobante().charAt(0)=='-' || comprobante.getIdentificacionComprobante().charAt(comprobante.getIdentificacionComprobante().length()-1)=='-')
			message("El campo identificación no puede empezar o terminar con el guión.");
		if (guion > 1 || cont > 0) {
			message("El campo Identificación no puede tener letras. Solo números, un guión y sin espacios.");
		}
		*/
		
		
		// Validaciones para el campo de Número de comprobante
		if (comprobante.getNumeroComprobante() < 0) {
			message("El número de comprobante no puede ser una cantidad negativa.");
		}
		if (!(comprobante.getNumeroComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo Número de Comprobante.");
		}
		for (Comprobante c : comprobanteDao.findAll()) {
			if (!(comprobante.getIdComprobante().equals(c.getIdComprobante())))
				if (c.getNumeroComprobante().equals(comprobante.getNumeroComprobante()))
					message("Ya existe un comprobante en el sistema con este mismo Número de Comprobante.");
		}

		// Validaciones para el campo Porcentaje de IVA
		try {
			Double.parseDouble(comprobante.getPorcentajeIvaComprobante().toString());
		} catch (Exception e) {
			message("Es obligatorio ingresar un número decimal en el campo Porcentaje de IVA");
		}
		if (!(comprobante.getPorcentajeIvaComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo Porcentaje de IVA.");
		}

		// validaciones para el campo Porcentaje de Retención
		try {
			Double.parseDouble(comprobante.getPorcentajeRetencionComprobante().toString());
		} catch (Exception e) {
			message("Es obligatorio ingresar un número decimal en el campo Porcentaje de Retención");
		}
		if (!(comprobante.getPorcentajeRetencionComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo números en el campo Porcentaje de Retención.");
		}

		
		/*
		// Validaciones para el campo Recibido de y pagado A
		if (comprobante.getRecibidoDeComprobante() == "" && comprobante.getPagadoAComprobante() == "") {
			message("Es obligatorio indicar de quién viene el comprobate económico o hacia quién va dirigido (Llenar el campo Pagado a o Recibido de).");
		}

		if (comprobante.getRecibidoDeComprobante().length() > 100
				|| comprobante.getPagadoAComprobante().length() > 100) {
			message("El campo 'Recibido de' y el campo 'Pagado A' no pueden exceder los 100 caracteres permitidos.");
		}
		
		*/

		// Validaciones para el campo Retención
		if (!(comprobante.getRetencionComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo caracteres numéricos en el campo Retención.");
		}
		if (comprobante.getPorcentajeRetencionComprobante() < 0) {
			message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo Retención.");
		}

		// Validaciones para el campo Subtotal
		if (!(comprobante.getSubTotalComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo caracteres numéricos en el campo Subtotal.");
		}
		if (comprobante.getSubTotalComprobante() < 0) {
			message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo subtotal.");
		}

		// Validaciones para el campo tipo de pago
		boolean pago = true;
		for (int i = 0; i < comprobante.getTipoPagoComprobante().length(); i++) {
			char letra = comprobante.getTipoPagoComprobante().charAt(i);
			if (!(letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z')) {
				pago = false;
			}
		}
		if (!pago) {
			message("El campo Tipo de Pago no puede contener números.");
		}
		if (comprobante.getTipoPagoComprobante().length() < 4) {
			message("Es obligatorio que el campo Tipo de Pago contenga por lo menos 4 caracteres.");
		}
		if (comprobante.getTipoPagoComprobante().length() > 40) {
			message("Es obligatorio que el campo Tipo de Pago no exceda los 40 caracteres permitidos.");
		}

		// Validaciones para el campo Valor Neto
		if (!(comprobante.getValorNetoComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))) {
			message("Es obligatorio ingresar solo caracteres numéricos en el campo Valor Neto.");
		}
		if (comprobante.getValorNetoComprobante() < 0) {
			message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo Valor Neto.");
		}

		// Validaciones para el campo Tipo Comprobante
		boolean tipo = true;
		for (int i = 0; i < comprobante.getTipoComprobante().length(); i++) {
			char letra = comprobante.getTipoComprobante().charAt(i);
			if (!(letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z')) {
				tipo = false;
			}
		}
		if (!tipo) {
			message("El campo Tipo de Comprobante no puede contener números.");
		}
		if (comprobante.getTipoComprobante().length() < 4) {
			message("Es obligatorio que el campo Tipo de Comprobante contenga por lo menos 4 caracteres.");
		}
		if (comprobante.getTipoComprobante().length() > 40) {
			message("Es obligatorio que el campo Tipo de Comprobante no exceda los 40 caracteres permitidos.");
		}

		// Validaciones para el campo Ciudad
		boolean ciudad = false;
		var contEspecial = 0;
		for (int i = 0; i < comprobante.getCiudadComprobante().length(); i++) {
			char letra = comprobante.getCiudadComprobante().charAt(i);
			if (letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z') {
				ciudad = true;
			}else {
				if(letra!=' ') {
					contEspecial++;
				}
			}
		}
		if (!ciudad || contEspecial>0) {
			message("El campo Ciudad solo puede tener letras y espacios.");
		}
		if (comprobante.getCiudadComprobante().length() < 4) {
			message("Es obligatorio que el campo Ciudad contenga por lo menos 4 caracteres.");
		}
		if (comprobante.getCiudadComprobante().length() > 40) {
			message("El campo 'Ciudad' no puede exceder los 40 caracteres permitidos.");
		}

	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}
}
