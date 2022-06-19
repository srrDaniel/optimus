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
import com.oygingenieria.optimus.models.Proveedor;

@Component("/optimus/proveedores/proveedores")
public class ListarPdfProveedores extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Proveedor> listaProveedores = (List<Proveedor>) model.get("proveedores");
		PdfPTable tablaProveedores = new PdfPTable(5);
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase("LISTADO DE PROVEEDORES"));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40, 190,138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		document.setPageSize(PageSize.LETTER.rotate());
		document.open();
		tablaProveedores.addCell("NIT");
		tablaProveedores.addCell("PROVEEDOR");
		tablaProveedores.addCell("EMAIL");
		tablaProveedores.addCell("ESTADO");
		tablaProveedores.addCell("TELÉFONO");
		listaProveedores.forEach(pro -> {
			tablaProveedores.addCell(pro.getNitProveedor());
			tablaProveedores.addCell(pro.getNombreProveedor());
			tablaProveedores.addCell(pro.getCorreoProveedor());
			tablaProveedores.addCell(pro.getEstadoProveedor());
			tablaProveedores.addCell(pro.getTelefonoProveedor().toString());
		});
		document.add(tablaTitulo);
		document.add(tablaProveedores);
	}

}
