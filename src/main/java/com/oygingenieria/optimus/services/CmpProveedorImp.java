package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.CmpProveedor;
import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.models.Proveedor;
import com.oygingenieria.optimus.dao.CmpProveedorDao;
import com.oygingenieria.optimus.dao.ComprobanteDao;
import com.oygingenieria.optimus.dao.ProveedorDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.ICmpProveedorValidator;

import java.util.List;

@Service
public class CmpProveedorImp implements ICmpProveedor
{

	@Autowired
	private ICmpProveedorValidator cmpProveedorValidator;
	
	@Autowired
	private ComprobanteDao comprobanteDao;
	
	@Autowired
	private CmpProveedorDao cmp_proveedorDao;
	
	@Autowired
	private ProveedorDao proveedorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CmpProveedor> listarCmpProveedor() 
	{
		return (List<CmpProveedor>) cmp_proveedorDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CmpProveedor> listarCmpProveedor(Proveedor proveedor) 
	{
		return (List<CmpProveedor>) cmp_proveedorDao.findByProveedor(proveedor);
	}

	@Override
	@Transactional
	public void guardarCmpProveedor(CmpProveedor cmp_proveedor) throws ApiUnprocessableEntity 
	{
		cmpProveedorValidator.validator(cmp_proveedor);
		cmp_proveedorDao.save(cmp_proveedor);		
	}

	@Override
	@Transactional
	public void eliminarCmpProveedor(CmpProveedor cmp_proveedor) 
	{
		cmp_proveedorDao.delete(cmp_proveedor);	
	}

	@Override
	@Transactional
	public void cambiarEstadoCmpProveedor(CmpProveedor cmp_proveedor) throws Exception 
	{
		CmpProveedor comprobanteProveedorModificar = cmp_proveedorDao.findById(cmp_proveedor.getId()).orElse(null);
		if(comprobanteProveedorModificar != null) 
		{			
			Proveedor proveedor = proveedorDao.findById(comprobanteProveedorModificar.getProveedor().getIdProveedor()).orElse(null);
			Comprobante comprobante = comprobanteDao.findById(comprobanteProveedorModificar.getComprobante().getIdComprobante()).orElse(null);
			if(comprobante.getEstadoComprobante().equals("Inactivo")) {
				if(comprobanteProveedorModificar.getEstado().equals("Inactivo")) {
					throw new ApiUnprocessableEntity("No puedes activar la asignación porque el comprobante se encuentra inactivo.");
				}
			}			
			if(proveedor.getEstadoProveedor().equals("Activo")) {
				var estado = (comprobanteProveedorModificar.getEstado().equals("Activo")) ? "Inactivo":"Activo";
				comprobanteProveedorModificar.setEstado(estado);		
			}else {
				throw new ApiUnprocessableEntity("No se puede cambiar el estado de la asignaci�n del comprobante porque el proveedor est� inactivo.");
			}
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public CmpProveedor buscarCmpProveedor(CmpProveedor cmp_proveedor) 
	{
		return cmp_proveedorDao.findById(cmp_proveedor.getId()).orElse(null);
	}

}
