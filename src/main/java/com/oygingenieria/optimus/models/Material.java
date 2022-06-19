package com.oygingenieria.optimus.models;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="materiales")
public class Material 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMaterial;

    @Column(name = "nombreMaterial", length=50, nullable=false, unique=false)
    private String nombreMaterial;

    @Column(name = "cantidadMaterial", nullable=false, unique=false)
    private Integer cantidadMaterial;

    @Column(name = "estadoMaterial", length=30, nullable=false, unique=false)
    private String estadoMaterial = "Activo";
}
