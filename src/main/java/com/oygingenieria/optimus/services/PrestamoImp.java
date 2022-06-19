package com.oygingenieria.optimus.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oygingenieria.optimus.dao.HerramientaDao;
import com.oygingenieria.optimus.dao.PrestamoDao;
import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.models.Herramienta;
import com.oygingenieria.optimus.models.Prestamo;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.IPrestamoValidator;

@Service
public class PrestamoImp implements IPrestamo
{
	@Autowired
	private IPrestamoValidator prestamoValidator;
	
	 @Autowired
	 private PrestamoDao prestamoDao;
	 
	 @Autowired
	 private HerramientaDao herramientaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> listarPrestamo() 
	{
		return (List<Prestamo>) prestamoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> listarPrestamo(Empleado empleado) 
	{
		return (List<Prestamo>) prestamoDao.findByEmpleado(empleado);
	}

	@Override
	@Transactional
	public void guardarPrestamo(Prestamo prestamo) throws Exception
	{
		prestamoValidator.validator(prestamo);
		prestamo.setFechaPrestamo(new Date());
		Optional<Herramienta> herramienta = herramientaDao.findById(prestamo.getHerramienta().getIdHerramienta());
		if(herramienta.isPresent()) {
			Herramienta her = herramienta.get();
			if(her.getCantidadDisponible()-prestamo.getCantidad_herramientas()<0) {
				Exception apiUnprocessableEntity = new ApiUnprocessableEntity("La cantidad de "+ her.getNombreHerramienta()+" disponibles no alcanza para prestar.");
				throw apiUnprocessableEntity;
			}
			her.setCantidadDisponible(her.getCantidadDisponible()-prestamo.getCantidad_herramientas());
			herramientaDao.save(her);
		}
		for(Prestamo p : prestamoDao.findAll()) {
			if(p.getEmpleado().getIdEmpleado().equals(prestamo.getEmpleado().getIdEmpleado())){
				if(p.getHerramienta().getIdHerramienta().equals(prestamo.getHerramienta().getIdHerramienta())) {
					prestamo.setId(p.getId());
					prestamo.setCantidad_herramientas(prestamo.getCantidad_herramientas()+p.getCantidad_herramientas());
				}
			}
		}
		prestamoDao.save(prestamo);	
	}

	@Override
	@Transactional
	public void eliminarPrestamo(Prestamo prestamo) 
	{
		Optional<Prestamo> p = prestamoDao.findById(prestamo.getId());
		Optional<Herramienta> h = null;
		Herramienta h2 = null;
		Prestamo p2 = null;
		if(p.isPresent()) {
			p2 = p.get();
			h = herramientaDao.findById(p2.getHerramienta().getIdHerramienta());
			if(h.isPresent()) {
				h2 = h.get();
				h2.setCantidadDisponible(h2.getCantidadDisponible()+p2.getCantidad_herramientas());
				herramientaDao.save(h2);
			}
		}
		prestamoDao.delete(prestamo);	
	}

	@Override
	@Transactional
	public void cambiarEstadoPrestamo(Prestamo prestamo) throws Exception 
	{
		Prestamo prestamoModificar = prestamoDao.findById(prestamo.getId()).orElse(null);
		if(prestamoModificar != null) 
		{			
			var estado = (prestamoModificar.getEstado().equals("Activo")) ? "Inactivo":"Activo";
			prestamoModificar.setEstado(estado);		
		}	
	}

	@Override
	@Transactional(readOnly = true)
	public Prestamo buscarPrestamo(Prestamo prestamo) 
	{
		return prestamoDao.findById(prestamo.getId()).orElse(null);
	}

}
