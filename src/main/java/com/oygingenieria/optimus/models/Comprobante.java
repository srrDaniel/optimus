package com.oygingenieria.optimus.models;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comprobantes")
public class Comprobante 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobante;

    @Column(name = "conceptoComprobante", length=255, nullable=false, unique=false)
    private String conceptoComprobante;

    @Column(name = "fechaComprobante", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaComprobante;

    @Column(name = "ivaComprobante", nullable = false)
    private Long ivaComprobante;

    @Column(name = "nFacturaComprobante", nullable = false)
    private Long nFacturaComprobante;

    //@Column(name = "identificacionComprobante", length=50, nullable=false, unique=false)
    //private String identificacionComprobante;

    @Column(name = "numeroComprobante", nullable = false, unique=true)
    private Long numeroComprobante;

    @Column(name = "observacionesComprobante", length=255, nullable=true, unique=false)
    private String observacionesComprobante;

    @Column(name = "porcentajeIvaComprobante", nullable = false)
    private Double porcentajeIvaComprobante;

    @Column(name = "porcentajeRetencionComprobante", nullable = false)
    private Double porcentajeRetencionComprobante;

    //@Column(name = "recibidoDeComprobante", length=150, nullable=false, unique=false)
    //private String recibidoDeComprobante;

    @Column(name = "retencionComprobante", nullable = false)
    private Long retencionComprobante;

    @Column(name = "subTotalComprobante", nullable = false)
    private Long subTotalComprobante;

    @Column(name = "tipoPagoComprobante", length=60, nullable=false, unique=false)
    private String tipoPagoComprobante;

    @Column(name = "valorNetoComprobante", nullable = false)
    private Long valorNetoComprobante;

    //@Column(name = "pagadoAComprobante", length=150, nullable=false, unique=false)
    //private String pagadoAComprobante;

    @Column(name = "tipoComprobante", length=50, nullable=false, unique=false)
    private String tipoComprobante;

    @Column(name = "ciudadComprobante", length=50, nullable=false, unique=false)
    private String ciudadComprobante;

    @Column(name = "estadoComprobante", length=50, nullable=false, unique=false)
    private String estadoComprobante = "Activo";

}
