package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.models.Proveedor;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.IComprobanteValidator;
import com.oygingenieria.optimus.dao.CmpEmpleadoDao;
import com.oygingenieria.optimus.dao.CmpProveedorDao;
import com.oygingenieria.optimus.dao.ComprobanteDao;
import com.oygingenieria.optimus.dao.EmpleadoDao;
import com.oygingenieria.optimus.dao.ProveedorDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteImp implements IComprobante
{
    @Autowired
    private ComprobanteDao comprobanteDao;
    
    @Autowired 
    private EmpleadoDao empleadoDao;
    
    @Autowired
    private ProveedorDao proveedorDao;
    
    @Autowired
	private IComprobanteValidator comprobanteValidator;
    
    @Autowired
	private CmpEmpleadoDao cmpEmpleadoDao;
    
    @Autowired
	private CmpProveedorDao cmpProveedorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Comprobante> listarComprobantes()
	{
		return (List<Comprobante>) comprobanteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Comprobante> ComprobantesActivos() 
	{
		return (List<Comprobante>) comprobanteDao.findByEstadoComprobante("Activo");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comprobante> ComprobantesInactivos() 
	{
		return (List<Comprobante>) comprobanteDao.findByEstadoComprobante("Inactivo");
	}
	
	@Override
	@Transactional
	public List<Comprobante> listarComprobantesActivos() {
		List<Comprobante> cActivos = new ArrayList<Comprobante>();
		boolean asignado = false;
		for (Comprobante c:comprobanteDao.findAll()) {
			for(CmpEmpleado ce:cmpEmpleadoDao.findAll()) {
				if(ce.getComprobante().getIdComprobante().equals(c.getIdComprobante())) asignado=true;
			}
			for(CmpProveedor cp:cmpProveedorDao.findAll()) {
				if(cp.getComprobante().getIdComprobante().equals(c.getIdComprobante())) asignado=true;
			}
			
			if(!asignado) {
				if (c.getEstadoComprobante().equals("Activo")) cActivos.add(c);
			}
			asignado=false;
		}
		return cActivos;
	}
	

	@Override
	@Transactional
	public void guardarComprobante(Comprobante comprobante) throws ApiUnprocessableEntity {
		comprobante.setFechaComprobante(new Date());
		comprobanteValidator.validator(comprobante);
		comprobanteDao.save(comprobante);
	}

	@Override
	@Transactional
	public void eliminarComprobante(Comprobante comprobante) 
	{
		comprobanteDao.delete(comprobante);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Comprobante buscarComprobante(Comprobante comprobante) 
	{
		return comprobanteDao.findById(comprobante.getIdComprobante()).orElse(null);
	}

	@Override
	@Transactional
	public void cambiarEstadoComprobante(Comprobante comprobante)
	{
		Comprobante comprobanteModificar = comprobanteDao.findById(comprobante.getIdComprobante()).orElse(null);
		if(comprobanteModificar != null) 
		{			
			String estado = (comprobanteModificar.getEstadoComprobante().equals("Activo")) ? "Inactivo":"Activo";
			cambiarEstadoCascada(comprobanteModificar);
			comprobanteModificar.setEstadoComprobante(estado);
		}		
	}
	
	private void cambiarEstadoCascada(Comprobante comprobante) {
		for(CmpEmpleado asignacion : cmpEmpleadoDao.findAll()) {
			if(asignacion.getComprobante().getIdComprobante().equals(comprobante.getIdComprobante())) {
				if(comprobante.getEstadoComprobante().equals(asignacion.getEstado())) {
					
					Empleado empleado = empleadoDao.findById(asignacion.getEmpleado().getIdEmpleado()).orElse(null);
					if(empleado.getEstadoEmpleado().equals("Activo")) {
						String estado = (asignacion.getEstado().equals("Activo")) ? "Inactivo" : "Activo";
						asignacion.setEstado(estado);
					}				
				}
			}
		}
		
		for(CmpProveedor asignacion : cmpProveedorDao.findAll()) {
			if(asignacion.getComprobante().getIdComprobante().equals(comprobante.getIdComprobante())) {
				if(comprobante.getEstadoComprobante().equals(asignacion.getEstado())) {
					 
					Proveedor proveedor = proveedorDao.findById(asignacion.getProveedor().getIdProveedor()).orElse(null);
					if(proveedor.getEstadoProveedor().equals("Activo")) {
						String estado = (asignacion.getEstado().equals("Activo")) ? "Inactivo" : "Activo";
						asignacion.setEstado(estado);
					}
				}
			}
		}
	}
	
	@Override
	@Transactional
	public void actualizarComprobante(Comprobante comprobante) throws ApiUnprocessableEntity {
		Optional<Comprobante> c = comprobanteDao.findById(comprobante.getIdComprobante());
		Comprobante c2 = null;
		if(c.isPresent()) {
			c2 = c.get();
			comprobante.setFechaComprobante(c2.getFechaComprobante());
		}
		comprobanteValidator.validatorActualizar(comprobante);
		comprobanteDao.save(comprobante);
	}

}