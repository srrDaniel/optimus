package com.oygingenieria.optimus.models.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponseDTO 
{
    private String codError;
    private String descripcionError;
}