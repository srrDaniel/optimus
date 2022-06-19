package com.oygingenieria.optimus.models;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proveedores")
public class Proveedor 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProveedor;

    @Column(name = "nitProveedor", length=50, nullable=false, unique=true)
    private String nitProveedor;

    @Column(name = "nombreProveedor", length=50, nullable=false, unique=false)
    private String nombreProveedor;

    @Column(name = "telefonoProveedor", length=100, nullable=false, unique=false)
    private String telefonoProveedor;

    @Column(name = "contactoProveedor", length=100, nullable=false, unique=false)
    private String contactoProveedor;

    @Column(name = "correoProveedor", length=100, nullable=false, unique=false)
    private String correoProveedor;

    @Column(name = "direccionProveedor", length=100, nullable=false, unique=false)
    private String direccionProveedor;

    @Column(name = "estadoProveedor", length=50, nullable=false, unique=false)
    private String estadoProveedor = "Activo";

}