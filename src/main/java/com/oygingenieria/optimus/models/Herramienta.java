package com.oygingenieria.optimus.models;

import lombok.*;

import java.util.List;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "herramientas")
public class Herramienta 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHerramienta;
	
	@Column(name = "nombreHerramienta", length=50, nullable=false, unique=false)
	private String nombreHerramienta;
		
    @Column(name = "cantidadHerramienta", nullable=false, unique=false)
    private Integer cantidadHerramienta;
    
    @Column(name = "cantidadDisponible", nullable=false, unique=false)
    private Integer cantidadDisponible;

	@Column(name = "estadoHerramienta", length=30, nullable=false, unique=false)
	private String estadoHerramienta = "Activo";
	
	@OneToMany(mappedBy = "herramienta")
	private List<Prestamo> prestamos;

}
