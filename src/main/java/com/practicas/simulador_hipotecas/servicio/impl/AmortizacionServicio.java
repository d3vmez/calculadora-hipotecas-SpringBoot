package com.practicas.simulador_hipotecas.servicio.impl;

import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;

/**
 * 
 * Servicio para la clase Amortizacion
 * 
 * @author Marcos
 *
 */
@Service
public class AmortizacionServicio{
	
	public static double capitalPorAmortizar;
	public static double totalPorAmortizar;
	
	/**
	 * 
	 * Método para generar cada una de las amortizaciones de la hipoteca
	 * 
	 * @param int nCuotas, numero de cuotas
	 * @param double cuota, importe de cada cuota
	 * @param float tasaInteres
	 * @param double prestamo, importe a devolver
	 * @return Amortizacion amortizacion
	 */
	public Amortizacion crearAmortizacion(Hipoteca hipoteca, int nCuotas, float tasaInteres) {
		
		if(nCuotas == 1) {
			AmortizacionServicio.capitalPorAmortizar = hipoteca.getPrestamo();
			AmortizacionServicio.totalPorAmortizar = 0.0;
			//return new Amortizacion(0, 0.0 ,0.0 ,0.0 ,0.0 , prestamo);
		}
		
		//Calcular intereses
		double intereses = calcularIntereses(tasaInteres, capitalPorAmortizar);
		actualizarInteresesTotal(intereses, hipoteca);
		
		//Calcular cuota de amortizacion
		double cuotaAmortizacion = calcularCuotaAmortizacion(hipoteca.getCuota(), intereses);
		
		//Calcular el total amortizado
		actualizarTotalAmortizado(cuotaAmortizacion);
		
		//Calcular capital por amortizar
		actualizarCapitalPorAmortizar(cuotaAmortizacion);
		
		return new Amortizacion(nCuotas, hipoteca.getCuota(), intereses, cuotaAmortizacion, AmortizacionServicio.totalPorAmortizar, AmortizacionServicio.capitalPorAmortizar);
		
	}
	
		
	/**
	 * 
	 * Método para calcular los intereses que se pagan en cada cuota
	 * 
	 * @param float tasaInteres
	 * @param double capitalPorAmortizar
	 * @return double intereses
	 */
	private double calcularIntereses(float tasaInteres, double capitalPorAmortizar) {
	
		
		return capitalPorAmortizar * tasaInteres;
		
	}
	
	/**
	 * 
	 * Método para calcular el importe de la amortizacion, sin contar con los intereses
	 * 
	 * @param double cuota
	 * @param double interes
	 * @return double importe de la amortizacion
	 */
	private double calcularCuotaAmortizacion(double cuota, double interes) {
		
		return cuota - interes;
		
	}
	
	/**
	 * 
	 * Método para actualizar el total amortizado
	 * 
	 * @param double totalAmortizado
	 */
	private void actualizarTotalAmortizado(double totalAmortizado) {
		
		AmortizacionServicio.totalPorAmortizar += totalAmortizado;
		
	}
	
	/**
	 * 
	 * Método para actualizar el capital por amortizar
	 * 
	 * @param DOUBLE totalAmortizado
	 */
	private void actualizarCapitalPorAmortizar(double totalAmortizado) {
		
		AmortizacionServicio.capitalPorAmortizar -= totalAmortizado;
		Amortizacion.totalCapitalPorAmortizar = AmortizacionServicio.capitalPorAmortizar;
		
	}
	
	/**
	 * 
	 * Método para acumular los intereses de cada una de las amortizaciones de la hipoteca
	 * 
	 * @param double interes
	 */
	private void actualizarInteresesTotal(double interes, Hipoteca hipoteca) {
		hipoteca.setTotalIntereses(interes);
	}
	
}
