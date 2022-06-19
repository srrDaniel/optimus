package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.dao.CmpEmpleadoDao;
import com.oygingenieria.optimus.dao.ComprobanteDao;
import com.oygingenieria.optimus.dao.EmpleadoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.ICmpEmpleadoValidator;

import java.util.List;

@Service
public class CmpEmpleadoImp implements ICmpEmpleado
{
	
	@Autowired
	private ICmpEmpleadoValidator cmpEmpleadoValidator;
	
	@Autowired
	private ComprobanteDao comprobanteDao;
	
	@Autowired
	private CmpEmpleadoDao cmp_empleadoDao;
	 
	@Autowired
	private EmpleadoDao empleadoDao;

	@Override
	@Transactional(readOnly = true)
	public List<CmpEmpleado> listarCmpEmpleado() 
	{
		return (List<CmpEmpleado>) cmp_empleadoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CmpEmpleado> listarCmpEmpleado(Empleado empleado) 
	{
		return (List<CmpEmpleado>) cmp_empleadoDao.findByEmpleado(empleado);
	}

	@Override
	@Transactional
	public void guardarCmpEmpleado(CmpEmpleado cmp_empleado) throws ApiUnprocessableEntity 
	{
		cmpEmpleadoValidator.validator(cmp_empleado);
		cmp_empleadoDao.save(cmp_empleado);	
	}

	@Override
	@Transactional
	public void eliminarCmpEmpleado(CmpEmpleado cmp_empleado) 
	{
		cmp_empleadoDao.delete(cmp_empleado);	
	}

	@Override
	@Transactional
	public void cambiarEstadoCmpEmpleado(CmpEmpleado cmp_empleado) throws Exception 
	{
		CmpEmpleado comprobanteEmpleadoModificar = cmp_empleadoDao.findById(cmp_empleado.getId()).orElse(null);
		if(comprobanteEmpleadoModificar != null) 
		{			
			Empleado empleado = empleadoDao.findById(comprobanteEmpleadoModificar.getEmpleado().getIdEmpleado()).orElse(null);
			Comprobante comprobante = comprobanteDao.findById(comprobanteEmpleadoModificar.getComprobante().getIdComprobante()).orElse(null);
			if(comprobante.getEstadoComprobante().equals("Inactivo")) {
				if(comprobanteEmpleadoModificar.getEstado().equals("Inactivo")) {
					throw new ApiUnprocessableEntity("No puedes activar la asignación porque el comprobante se encuentra inactivo.");
				}
			}
			if(empleado.getEstadoEmpleado().equals("Activo")){
				var estado = (comprobanteEmpleadoModificar.getEstado().equals("Activo")) ? "Inactivo":"Activo";
				comprobanteEmpleadoModificar.setEstado(estado);
			}
			else {
				throw new ApiUnprocessableEntity("No puedes activar la asignaci�n porque el empleado se encuenta inactivo.");
			}
		}	
	}

	@Override
	@Transactional(readOnly = true)
	public CmpEmpleado buscarCmpEmpleado(CmpEmpleado cmp_empleado) 
	{
		return cmp_empleadoDao.findById(cmp_empleado.getId()).orElse(null);
	}

}
