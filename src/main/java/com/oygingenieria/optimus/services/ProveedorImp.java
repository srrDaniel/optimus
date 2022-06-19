package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.models.Proveedor;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.IProveedorValidator;
import com.oygingenieria.optimus.dao.CmpProveedorDao;
import com.oygingenieria.optimus.dao.ComprobanteDao;
import com.oygingenieria.optimus.dao.ProveedorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorImp implements IProveedor 
{
	@Autowired
	private CmpProveedorDao cmpProveedorDao;
	
    @Autowired
    private ProveedorDao proveedorDao;
    
    @Autowired
	private IProveedorValidator proveedorValidator;
    
    @Autowired
    private ComprobanteDao comprobanteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> listarProveedores()
	{
		return (List<Proveedor>) proveedorDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> ProveedoresActivos()
	{
		return (List<Proveedor>) proveedorDao.findByEstadoProveedor("Activo");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> ProveedoresInactivos()
	{
		return (List<Proveedor>) proveedorDao.findByEstadoProveedor("Inactivo");
	}


	@Override
	@Transactional
	public void guardarProveedor(Proveedor proveedor) throws ApiUnprocessableEntity 
	{
		proveedorValidator.validator(proveedor);
		proveedorDao.save(proveedor);
	}
	
	@Override
	@Transactional
	public void actualizarProveedor(Proveedor proveedor) throws ApiUnprocessableEntity {
		proveedorValidator.validatorActualizar(proveedor);
		proveedorDao.save(proveedor);
	}

	@Override
	@Transactional
	public void eliminarProveedor(Proveedor proveedor) 
	{
		proveedorDao.delete(proveedor);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Proveedor buscarProveedor(Proveedor proveedor) 
	{
		return proveedorDao.findById(proveedor.getIdProveedor()).orElse(null);
	}

	@Override
	@Transactional
	public void cambiarEstadoProveedor(Proveedor proveedor)
	{
		Proveedor proveedorModificar = proveedorDao.findById(proveedor.getIdProveedor()).orElse(null);
		if(proveedorModificar != null) 
		{			
			var estado = (proveedorModificar.getEstadoProveedor().equals("Activo")) ? "Inactivo":"Activo";
			cambiarEstadoCascada(proveedorModificar);
			proveedorModificar.setEstadoProveedor(estado);
		}		
	}
	
	/*
	 * Con este m�todo haremos el cambio de estado en cascada a los comprobantes
	 * Asignados a un proveedor, cuando al proveedor se le cambia el estado
	 * */
	
	private void cambiarEstadoCascada(Proveedor proveedor) {
		for(CmpProveedor asignacion : cmpProveedorDao.findAll()) {
			if(asignacion.getProveedor().getIdProveedor().equals(proveedor.getIdProveedor())) {
				
				if(proveedor.getEstadoProveedor().equals(asignacion.getEstado())) {
					var estado = (asignacion.getEstado().equals("Activo")) ? "Inactivo" : "Activo";
					asignacion.setEstado(estado);
				}
				Comprobante comprobante = comprobanteDao.findById(asignacion.getComprobante().getIdComprobante()).orElse(null);
				if(comprobante.getEstadoComprobante().equals(proveedor.getEstadoProveedor())) {
					var estado = (comprobante.getEstadoComprobante().equals("Activo")) ? "Inactivo":"Activo";
					comprobante.setEstadoComprobante(estado);
					
				}			
			}
		}
	}
	
	@Override
	@Transactional
	public List<Proveedor> listarProveedoresActivos() {
		List<Proveedor> p = new ArrayList<Proveedor>();
		for(Proveedor p1 : proveedorDao.findAll()) {
			if(p1.getEstadoProveedor().equals("Activo")) p.add(p1);
		}
		return p;
	}

}