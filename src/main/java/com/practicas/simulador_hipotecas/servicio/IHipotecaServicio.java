package com.practicas.simulador_hipotecas.servicio;

import com.practicas.simulador_hipotecas.modelo.Hipoteca;

/**
 * 
 * Interfaz para la implementación de los servicios Hipoteca
 * 
 * @author mgomezgarrote
 *
 */

public interface IHipotecaServicio {
	
	// Métodos abstractos
	
	/**
	 * 
	 * Método para calcular la cuota que se deberá pagar al mes, incluyendo los intereses
	 * 
	 * @param Hipoteca hipoteca, se recibe una instancia de este objeto
	 * @return double cuota de la mensualidad
	 */
	public void calcularCuota(Hipoteca hipoteca);
	
	
	/**
	 * 
	 * Método para calcular cada una de las amortizaciones que conforman una hipoteca
	 * 
	 * @param Hipoteca hipoteca
	 */
	public void calcularAmortizaciones(Hipoteca hipoteca);
	
	/**
	 * 
	 * Método calcular la tasa de interes en base a las mensualidades
	 * 
	 * @param float tasaInteres
	 * @return float tasaInteres
	 */
	public float calcularTasaInteres(float tasaInteres);
	
	/**
	 * @param hipoteca
	 * @return
	 */
	public void calcularTasaInteres(Hipoteca hipoteca);

}
