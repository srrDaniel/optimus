package com.oygingenieria.optimus.models.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UsuarioDTO 
{
    private Long idUsuario;
    private String nombreUsuario;
    private String estadoUsuario;
}
