package com.oygingenieria.optimus.services;

import com.oygingenieria.optimus.models.Material;
import com.oygingenieria.optimus.utils.exceptions.ApiUnprocessableEntity;
import java.text.ParseException;
import java.util.List;

public interface IMaterial 
{
    List<Material> listarMateriales();
    void eliminarMaterial(Material material);
    void guardarMaterial(Material material) throws ApiUnprocessableEntity;
    void cambiarEstadoMaterial(Material material);
    public Material buscarMaterial(Material material);
    List<Material> alertaMateriales() throws ParseException;
    void actualizarMaterial(Material material) throws ApiUnprocessableEntity;
	List<Material> listarMaterialesActivos();
	List<Material> listarMaterialesInactivos();
}
