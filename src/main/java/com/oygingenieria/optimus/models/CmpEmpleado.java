package com.oygingenieria.optimus.models;

import lombok.*;
import javax.persistence.*;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comprobante_empleado")
public class CmpEmpleado 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "estado",nullable = false)
	private String estado = "Activo";
	
	@ManyToOne
	private Comprobante comprobante;
	
	@ManyToOne
	private Empleado empleado;
}
