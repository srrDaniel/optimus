package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.dao.MaterialDao;
import com.oygingenieria.optimus.models.Material;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import com.oygingenieria.optimus.validator.IMaterialValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.util.List;

@Service
public class MaterialImp implements IMaterial {
	@Autowired
	private MaterialDao materialDao;

	@Autowired
	private IMaterialValidator iMaterialValidator;

	@Override
	@Transactional(readOnly = true)
	public List<Material> listarMateriales() {
		return materialDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Material> listarMaterialesActivos() {
		return materialDao.findByEstadoMaterial("Activo");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Material> listarMaterialesInactivos() {
		return materialDao.findByEstadoMaterial("Inactivo");
	}

	@Override
	@Transactional
	public void guardarMaterial(Material material) throws ApiUnprocessableEntity {
		iMaterialValidator.validator(material);
		materialDao.save(material);
	}

	@Override
	@Transactional
	public void actualizarMaterial(Material material) throws ApiUnprocessableEntity {
		iMaterialValidator.validatorActualizar(material);
		materialDao.save(material);
	}

	@Override
	@Transactional
	public void eliminarMaterial(Material material) {
		materialDao.delete(material);
	}

	@Override
	@Transactional(readOnly = true)
	public Material buscarMaterial(Material material) {
		return materialDao.findById(material.getIdMaterial()).orElse(null);
	}

	@Override
	@Transactional
	public void cambiarEstadoMaterial(Material material) {
		Material materialModificar = materialDao.findById(material.getIdMaterial()).orElse(null);
		if (materialModificar != null) {
			var estado = (materialModificar.getEstadoMaterial().equals("Activo")) ? "Inactivo" : "Activo";
			materialModificar.setEstadoMaterial(estado);
		}
	}

	@Override
	@Transactional
	public List<Material> alertaMateriales() throws ParseException {
		try {
			return materialDao.findAll().stream().filter(
					material -> material.getCantidadMaterial() < 10 && material.getEstadoMaterial().equals("Activo"))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

}
