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
import com.oygingenieria.optimus.models.Material;

@Component("/optimus/materiales/materiales")
public class ListarPdfMateriales extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Material> listaMateriales = (List<Material>) model.get("materiales");
		PdfPTable tablaMateriales = new PdfPTable(3);
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase("LISTADO DE MATERIALES"));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40, 190,138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		document.setPageSize(PageSize.LETTER.rotate());
		document.open();
		tablaMateriales.addCell("MATERIAL");
		tablaMateriales.addCell("CANTIDAD");
		tablaMateriales.addCell("ESTADO");
		listaMateriales.forEach(mat -> {
			tablaMateriales.addCell(mat.getNombreMaterial());
			tablaMateriales.addCell(mat.getCantidadMaterial().toString());
			tablaMateriales.addCell(mat.getEstadoMaterial());
		});
		document.add(tablaTitulo);
		document.add(tablaMateriales);
	}

}
