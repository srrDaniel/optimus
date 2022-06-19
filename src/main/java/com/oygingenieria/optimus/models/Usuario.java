package com.oygingenieria.optimus.models;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario
{    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    
    @Column(name="correoUsuario", length=100, nullable=false, unique=true)
    private String correoUsuario;

    @Column(name = "nombreUsuario", length=30, nullable=false, unique=true)
    private String nombreUsuario;
    
    @Column(name = "passwordUsuario", length=128, nullable=false, unique=false)
    private String passwordUsuario;
    
    @Column(name = "estadoUsuario", length=50, nullable=false, unique=false)
    private String estadoUsuario = "Activo";
    
    @Column(name = "tokenPassword", length=128, nullable=true, unique=true)
    private String tokenPassword;
    
    
    
}
