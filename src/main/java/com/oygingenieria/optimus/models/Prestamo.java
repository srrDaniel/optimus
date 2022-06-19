package com.oygingenieria.optimus.models;

import lombok.*;
import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prestamo")
public class Prestamo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "estado",nullable = false)
	private String estado = "Activo";
	
	 @Column(name="fechaPrestamo", nullable = false)
	 @Temporal(TemporalType.DATE)
	 private Date fechaPrestamo;
	 
	 @Column(name="fechaEntrega")
	 @Temporal(TemporalType.DATE)
	 private Date fechaEntrega;
	 
	 @Column(name = "cantidad_herramientas", nullable=false, unique=false)
	 private Integer cantidad_herramientas;
	
	@ManyToOne
	private Herramienta herramienta;
	
	@ManyToOne
	private Empleado empleado;
}
