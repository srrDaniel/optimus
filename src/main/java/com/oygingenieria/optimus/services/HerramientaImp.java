package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.Herramienta;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.IHerramientaValidator;
import com.oygingenieria.optimus.dao.HerramientaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HerramientaImp implements IHerramienta
{
	@Autowired
	private HerramientaDao herramientaDao;
	
	@Autowired
	private IHerramientaValidator herramientaValidator;

	@Override
	@Transactional(readOnly = true)
	public List<Herramienta> listarHerramientas()
	{
		return (List<Herramienta>) herramientaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Herramienta> HerramientasActivas()
	{
		return (List<Herramienta>) herramientaDao.findByEstadoHerramienta("Activo");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Herramienta> HerramientasInactivas()
	{
		return (List<Herramienta>) herramientaDao.findByEstadoHerramienta("Inactivo");
	}

	@Override
	@Transactional
	public void guardarHerramienta(Herramienta herramienta) throws ApiUnprocessableEntity 
	{
		herramienta.setCantidadDisponible(herramienta.getCantidadHerramienta());
		herramientaValidator.validator(herramienta);
		herramientaDao.save(herramienta);
	}
	
	@Override
	@Transactional
	public void actualizarHerramienta(Herramienta herramienta) throws ApiUnprocessableEntity {
		Optional<Herramienta> h = herramientaDao.findById(herramienta.getIdHerramienta());
		Herramienta h2 = null;
		if(h.isPresent()) {
			h2 = h.get();
			herramienta.setCantidadDisponible(h2.getCantidadDisponible());
		}
		herramientaValidator.validatorActualizar(herramienta);
		herramientaDao.save(herramienta);
		
	}
	

	@Override
	@Transactional
	public void eliminarHerramienta(Herramienta herramienta) 
	{
		herramientaDao.delete(herramienta);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Herramienta buscarHerramienta(Herramienta herramienta) 
	{
		return herramientaDao.findById(herramienta.getIdHerramienta()).orElse(null);
	}

	@Override
	@Transactional
	public void cambiarEstadoHerramienta(Herramienta herramienta)
	{
		Herramienta herramientaModificar = herramientaDao.findById(herramienta.getIdHerramienta()).orElse(null);
		if(herramientaModificar != null) 
		{			
			String estado = (herramientaModificar.getEstadoHerramienta().equals("Activo")) ? "Inactivo":"Activo";
			herramientaModificar.setEstadoHerramienta(estado);		
		}		
	}
	
	@Override
	@Transactional
	public List<Herramienta> listarHerramientasActivas() {
		List<Herramienta> hActivas = new ArrayList<Herramienta>();
		for (Herramienta h:herramientaDao.findAll()) {
			if (h.getEstadoHerramienta().equals("Activo")) hActivas.add(h);
			
		}
		return hActivas;
	}
	

}
