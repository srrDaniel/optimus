package com.oygingenieria.optimus.models.dto;

import lombok.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpleadoDTO 
{
    private String nombreEmpleado;
    private Date vencimientoCursoAltura;
    private String documentoEmpleado;
    private String apellidoEmpleado;
}
