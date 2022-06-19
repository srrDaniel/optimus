package com.oygingenieria.optimus.utils;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.oygingenieria.optimus.models.Herramienta;

@Component("/optimus/herramientas/herramientas")
public class ListarPdfHerramientas extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Herramienta> listaEmpleados = (List<Herramienta>) model.get("herramientas");
		PdfPTable tablaherramientas = new PdfPTable(4);
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase("LISTADO DE HERRAMIENTAS"));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40, 190,138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		document.setPageSize(PageSize.LETTER.rotate());
		document.open();
		tablaherramientas.addCell("HERRAMIENTA");
		tablaherramientas.addCell("CANTIDAD");
		tablaherramientas.addCell("CANTIDAD DISPONIBLES");
		tablaherramientas.addCell("ESTADO");
		listaEmpleados.forEach(her -> {
			tablaherramientas.addCell(her.getNombreHerramienta());
			tablaherramientas.addCell(her.getCantidadHerramienta().toString());
			tablaherramientas.addCell(her.getCantidadDisponible().toString());
			tablaherramientas.addCell(her.getEstadoHerramienta());
		});
		document.add(tablaTitulo);
		document.add(tablaherramientas);
	}

}
