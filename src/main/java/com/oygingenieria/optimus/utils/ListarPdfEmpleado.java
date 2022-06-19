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
import com.oygingenieria.optimus.models.Empleado;

@Component("/optimus/empleados/empleados")
public class ListarPdfEmpleado extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Empleado> listaEmpleados = (List<Empleado>) model.get("empleados");
		PdfPTable tablaEmpleados = new PdfPTable(5);
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase("LISTADO DE EMPLEADOS"));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40, 190,138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		document.setPageSize(PageSize.LETTER.rotate());
		document.open();
		tablaEmpleados.addCell("DOCUMENTO");
		tablaEmpleados.addCell("NOMBRE");
		tablaEmpleados.addCell("APELLIDOS");
		tablaEmpleados.addCell("ESTADO");
		tablaEmpleados.addCell("FECHA VENCIMIENTO CA");
		listaEmpleados.forEach(empleado -> {
			tablaEmpleados.addCell(empleado.getDocumentoEmpleado());
			tablaEmpleados.addCell(empleado.getNombreEmpleado());
			tablaEmpleados.addCell(empleado.getApellidoEmpleado());
			tablaEmpleados.addCell(empleado.getEstadoEmpleado());
			tablaEmpleados.addCell(empleado.getVencimientoCursoAltura().toString());
		});
		document.add(tablaTitulo);
		document.add(tablaEmpleados);
	}

}
