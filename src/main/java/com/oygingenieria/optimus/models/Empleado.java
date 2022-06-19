package com.oygingenieria.optimus.models;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleado 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmpleado;

    @Column(name="nombreEmpleado", length=30, nullable=false, unique=false)
    private String nombreEmpleado;

    @Column(name="apellidoEmpleado", length=50, nullable=false, unique=false)
    private String apellidoEmpleado;

    @Column(name = "edadEmpleado", nullable=false, unique=false)
    private Integer edadEmpleado;

    @Column(name = "estadoEmpleado", length=50, nullable=false, unique=false)
    private String estadoEmpleado = "Activo";

    @Column(name="correoEmpleado", length=100, nullable=false, unique=false)
    private String correoEmpleado;

    @Column(name="direccionEmpleado", length=100, nullable=false, unique=false)
    private String direccionEmpleado;

    @Column(name="documentoEmpleado", length=15, nullable=false, unique=true)
    private String documentoEmpleado;

    @Column(name="fechaIngresoEmpleado", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoEmpleado;

    @Column(name="vencimientoCursoAltura", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date vencimientoCursoAltura;

    @Column(name="tipoDocumentoEmpleado", length=50, nullable=false, unique=false)
    private String tipoDocumentoEmpleado;

    @Column(name="salarioEmpleado", nullable=false, unique=false)
    private Double salarioEmpleado;

    @Column(name="telefonoEmpleado", length=100, nullable=false, unique=false)
    private String telefonoEmpleado;

    @Column(name="arlEmpleado", length=50, nullable=false, unique=false)
    private String arlEmpleado;

    @Column(name="epsEmpleado", length=50, nullable=false, unique=false)
    private String epsEmpleado;
    
    @OneToMany(mappedBy = "empleado")
    private List<Prestamo> prestamos;
}