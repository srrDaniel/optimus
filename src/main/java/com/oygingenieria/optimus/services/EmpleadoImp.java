package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.CmpEmpleado;
import com.oygingenieria.optimus.models.Comprobante;
import com.oygingenieria.optimus.models.Empleado;
import com.oygingenieria.optimus.models.Prestamo;
import com.oygingenieria.optimus.models.dto.EmpleadoDTO;
import com.oygingenieria.optimus.models.dto.NombreEmpleadoDTO;
import com.oygingenieria.optimus.dao.CmpEmpleadoDao;
import com.oygingenieria.optimus.dao.ComprobanteDao;
import com.oygingenieria.optimus.dao.EmpleadoDao;
import com.oygingenieria.optimus.dao.PrestamoDao;
import com.oygingenieria.optimus.utils.Fechas;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.IEmpleadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmpleadoImp implements IEmpleado
{
	@Autowired
	private PrestamoDao prestamoDao;
	
	@Autowired
	private CmpEmpleadoDao cmpEmpleadoDao;
	
    @Autowired
    private EmpleadoDao empleadoDao;
    
    @Autowired
	private IEmpleadoValidator empleadoValidator;
    
    @Autowired
    private ComprobanteDao comprobanteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> listarEmpleados()
	{
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> EmpleadosActivos() 
	{
		return (List<Empleado>) empleadoDao.findByEstadoEmpleado("Activo");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> EmpleadosInactivos() 
	{
		return (List<Empleado>) empleadoDao.findByEstadoEmpleado("Inactivo");
	}
	
	@Override
	@Transactional
	public void guardarEmpleado(Empleado empleado) throws ApiUnprocessableEntity 
	{
		empleadoValidator.validator(empleado);
		empleadoDao.save(empleado);
	}
	
	@Override
	@Transactional
	public void actualizarEmpleado(Empleado empleado) throws ApiUnprocessableEntity {
		empleadoValidator.validatorActualizar(empleado);
		empleadoDao.save(empleado);

	}

	@Override
	@Transactional
	public void eliminarEmpleado(Empleado empleado) 
	{
		empleadoDao.delete(empleado);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Empleado buscarEmpleado(Empleado empleado) 
	{
		return empleadoDao.findById(empleado.getIdEmpleado()).orElse(null);
	}

	@Override
	@Transactional
	public void cambiarEstadoEmpleado(Empleado empleado) throws ApiUnprocessableEntity
	{
		Empleado empleadoModificar = empleadoDao.findById(empleado.getIdEmpleado()).orElse(null);
		if(empleadoModificar != null) 
		{			
			List<Prestamo> prestamo = prestamoDao.findByEmpleado(empleado);
					
			if(!prestamo.isEmpty()) {
				throw new ApiUnprocessableEntity("No puedes cambiar el estado del empleado porque tiene herramientas prestadas.");
			}
			else {
				var estado = (empleadoModificar.getEstadoEmpleado().equals("Activo")) ? "Inactivo":"Activo";
				cambiarEstadoCascada(empleadoModificar);
				empleadoModificar.setEstadoEmpleado(estado);		
			}			
		}		
	}
	
	/*
	  	Con este m�todo, cuando un estado del empleado se inhabilite, entonces todos
		los comprobantes de pago asociados a �l, tambi�n se inhabilitar�n.
	*/
	private void cambiarEstadoCascada(Empleado empleado) {
		for(CmpEmpleado asignacion : cmpEmpleadoDao.findAll()) {
			if(asignacion.getEmpleado().getIdEmpleado().equals(empleado.getIdEmpleado())) {
				
				if(empleado.getEstadoEmpleado().equals(asignacion.getEstado())) {
					var estado = (asignacion.getEstado().equals("Activo")) ? "Inactivo" : "Activo";
					asignacion.setEstado(estado);
				}
				Comprobante comprobante = comprobanteDao.findById(asignacion.getComprobante().getIdComprobante()).orElse(null);
				if(comprobante.getEstadoComprobante().equals(empleado.getEstadoEmpleado())) {
					var estado2 = (comprobante.getEstadoComprobante().equals("Activo")) ? "Inactivo" : "Activo";
					comprobante.setEstadoComprobante(estado2);
				}
			}
		}
	}
	

	@Override
	@Transactional(readOnly = true)
    public List<EmpleadoDTO> alertas() throws ParseException 
	{
        List <EmpleadoDTO> empleadoDTOList = new ArrayList<EmpleadoDTO>();
        Date fechaActual = new Date();
        for (Empleado alertaEmpleado : empleadoDao.findAll()) 
        {
            if (Fechas.calculateDaysBetweenDates(alertaEmpleado.getVencimientoCursoAltura(), fechaActual)<30 && alertaEmpleado.getEstadoEmpleado().equals("Activo"))
            {
                EmpleadoDTO empleadoDTO1 = new EmpleadoDTO();
                empleadoDTO1.setNombreEmpleado(alertaEmpleado.getNombreEmpleado());
                empleadoDTO1.setVencimientoCursoAltura(alertaEmpleado.getVencimientoCursoAltura());
                empleadoDTO1.setDocumentoEmpleado(alertaEmpleado.getDocumentoEmpleado());
                empleadoDTO1.setApellidoEmpleado(alertaEmpleado.getApellidoEmpleado());
                empleadoDTOList.add(empleadoDTO1);
            }
        }
        return empleadoDTOList;
    }
	
	@Override
	@Transactional
	public List<NombreEmpleadoDTO> ListarEmpleadosActivos() {
		List<NombreEmpleadoDTO> eActivas = new ArrayList<NombreEmpleadoDTO>();
		for (Empleado e:empleadoDao.findAll()) {
			if (e.getEstadoEmpleado().equals("Activo")) {
				NombreEmpleadoDTO empleado = NombreEmpleadoDTO.builder()
														.id(e.getIdEmpleado())
														.nombreCompleto(e.getNombreEmpleado()+" "+e.getApellidoEmpleado())
														.build();
																
				eActivas.add(empleado);
			}
			
		}
		return eActivas;
	}	

}
	