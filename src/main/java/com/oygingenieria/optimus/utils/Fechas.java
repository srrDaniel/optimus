package com.oygingenieria.optimus.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.time.temporal.ChronoUnit.DAYS;

public class Fechas 
{

	public static Long calculateDaysBetweenDates(Date date1, Date date2) throws ParseException 
	{
		//Comentario
		return Math.abs(DAYS.between(devolverFecha(date1).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
									devolverFecha(date2).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
	}

	public static Date devolverFecha(Date fechaEntrada)
	{
		try
		{
			DateFormat formato = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
			String fechaString = formato.format(fechaEntrada); // Convierte Date a String
			Date miFecha = formato.parse(fechaString); // convierte String a Date
			return miFecha;
		}
		catch (Exception e)
		{
			System.out.println("No se le pudo dar un formato a la fecha.");
			return null;
		}
	}
	
	private static final Logger LOGGER = Logger.getLogger("app.demo.domain.util.Fechas");

	private Fechas() {}

	/**
	 * 
	 * @param strFecha
	 * @param patron
	 * @return
	 * @throws ParseException 
	 */
	public static Date strToDate(String strFecha, String patron)
	{
		Date convertDate = null;
		try
		{
			convertDate = (new SimpleDateFormat(patron)).parse(strFecha);
			return convertDate;
		} 
		catch (ParseException pex)
		{
			LOGGER.log(Level.WARNING, "Error parseando la fecha");
		}
		return null;
	}

	/**
	 * 
	 * @param fecha
	 * @param patron
	 * @return
	 */
	public static String dateToStr(Date fecha, String patron) 
	{
		try 
		{
			Format formatter = new SimpleDateFormat(patron);
			if (fecha != null) 
			{
				return formatter.format(fecha);
			}
		} 
		catch (Exception e) 
		{
			System.out.println((new StringBuilder("Error en dateToStr (")).append(fecha).append(",").append(patron)
					.append("): ").append(e.toString()).toString());
		}
		return "";
	}

	/**
	 * 
	 * @param fechaNumerica
	 * @return
	 */
	public static Date numberToDate(String fechaNumerica)
	{
		Date convertDate = null;
		String fechaTotal = "";
		String patron = "yyyy/MM/dd";

		try 
		{
			if (fechaNumerica != null && fechaNumerica.equals("") == false && fechaNumerica.trim().length() == 8) 
			{
				String ano = fechaNumerica.substring(0, 4);
				String mes = fechaNumerica.substring(4, 6);
				String dia = fechaNumerica.substring(6, 8);

				if (ano.equals("") || mes.equals("") || dia.equals("")) 
				{
					return convertDate;
				} 
				else 
				{
					fechaTotal = ano + "/" + mes + "/" + dia;
					convertDate = (new SimpleDateFormat(patron)).parse(fechaTotal);
					return convertDate;
				}
			} 
			else 
			{
				System.out.println("Error en las validaciones de " + fechaNumerica);
			}
		} 
		catch (ParseException pex) 
		{
			System.out.println("Error tratando de obtener una fecha de " + fechaNumerica);
		}
		return convertDate;
	}

	/**
	 * 
	 * @param fecha
	 * @param dias
	 * @param meses
	 * @param aF1os
	 * @param semanas
	 * @return
	 */
	public static Date sumar(Date fecha, int dias, int meses, int aF1os, int semanas) 
	{
		try 
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			fecha = c.getTime();
			return fecha;
		} 
		catch (Exception e) 
		{
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new Date();
	}

	/**
	 * 
	 * @param fecha
	 * @return
	 */
	public static int getDiaDelMes(Date fecha) 
	{
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		return c.get(5);
	}

	/**
	 * 
	 * @param fecha
	 * @return
	 * @throws DatatypeConfigurationException
	 */

	/**
	 * 
	 * @param fch
	 * @param dias
	 * @return
	 */
	public static Date restarDias(Date fch, int dias) 
	{
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fch.getTime());
		cal.add(Calendar.DATE, -dias);
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * 
	 * @param fecha
	 * @return
	 */
	// Encuentra el dia de la semana segun la fecha actual
	// -----Lunes,martes,miercoles...
	public static int getMonthOfDate(Date fecha) 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */

	/**
	 * 
	 * @param calendar
	 * @return
	 */
	public static boolean isLastDayOfFebruary(Calendar calendar)
	{
		System.out.println("######### Inicio al metodo isLastDayOfFebruary ############");
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int realMonth = month + 1;
		int diaDelMes = calendar.get(Calendar.DAY_OF_MONTH);

		YearMonth yearMonthObject = YearMonth.of(year, realMonth);

		// Si el mes es 2 (Febrero) y el dia del mes es el ultimo (28 de Febrero) -->
		// retorno true
		boolean respuesta = (realMonth == 2) && (diaDelMes == yearMonthObject.lengthOfMonth());
		System.out.println("######### Finalizo al metodo isLastDayOfFebruary ############ " + respuesta);
		return respuesta;
	}

	/**
	 * 
	 * @param fecha
	 * @param diaPago
	 * @return
	 */
	public static Date setDayToDate(Date fecha, int diaPago) 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		calendar.set(year, month, diaPago, 0, 0);
		return calendar.getTime();
	}
}