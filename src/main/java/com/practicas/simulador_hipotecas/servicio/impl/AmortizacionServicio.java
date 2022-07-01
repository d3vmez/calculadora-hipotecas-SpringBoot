package com.practicas.simulador_hipotecas.servicio.impl;

import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;

/**
 * 
 * Servicio para la clase Amortizacion
 * Este servicio se encarga de crear las amortizaciones para la hipoteca
 * 
 * @author Marcos
 * @author Pablo
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
		
		// Si es la primera cuota se inicalizan los valores para 
		// el capital por amortizar, que se corresponde con el préstamo inicial,
		// y total amortizado, que será 0
		if(nCuotas == 1) {
			AmortizacionServicio.capitalPorAmortizar = hipoteca.getPrestamo();
			AmortizacionServicio.totalPorAmortizar = 0.0;
	
		}
		
		// Calcular intereses
		double intereses = calcularIntereses(tasaInteres, capitalPorAmortizar);
		// Actualizar la cantidad de intereses que se han pagado
		actualizarInteresesTotal(intereses, hipoteca);
		
		// Calcular cuota de amortizacion
		double cuotaAmortizacion = calcularCuotaAmortizacion(hipoteca.getCuota(), intereses);
		
		// Calcular el total amortizado
		actualizarTotalAmortizado(cuotaAmortizacion);
		
		// Calcular capital por amortizar
		actualizarCapitalPorAmortizar(cuotaAmortizacion);
		
		// Se devuelve un objeto de Amortizacion para que después se añada a la hipoteca
		return new Amortizacion(nCuotas, hipoteca.getCuota(), intereses, cuotaAmortizacion, AmortizacionServicio.totalPorAmortizar, AmortizacionServicio.capitalPorAmortizar);
		
	}
	
	// Métodos auxiliares
	/////////////////////////////////////////////////////////////////
		
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
	 * Método para calcular el importe de la cuota, sin contar con los intereses
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
	 * @param double cuota, importe de la cuota mensual
	 */
	private void actualizarTotalAmortizado(double cuota) {
		
		AmortizacionServicio.totalPorAmortizar += cuota;
		
	}
	
	/**
	 * 
	 * Método para actualizar el capital por amortizar
	 * 
	 * @param DOUBLE cuota, importe de la cuota mensual
	 */
	private void actualizarCapitalPorAmortizar(double cuota) {
		
		AmortizacionServicio.capitalPorAmortizar -= cuota;
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
