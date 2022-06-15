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
	
	// Métodos comunes
	
	/**
	 * 
	 * Método para calcular el importe final del préstamo
	 * 
	 * @param Hipoteca hipoteca
	 */
	default double calcularValorDelPrestamo(Hipoteca hipoteca) {
		
		double capitalInmueble = hipoteca.getCapitalInmueble();
		double capitalAportado = hipoteca.getCapitalAportado();
		
		double prestamo = capitalInmueble-capitalAportado;
		hipoteca.setPrestamo(prestamo);
		
		return prestamo;
	}
	
	/**
	 * 
	 * Método para transformar el plazo en años en mensualidades
	 * 
	 * @param int nAnos, número de años que durará la hipoteca
	 * @return int número de mensualidades
	 */
	default int calcularNCuotas (int nAnos) {
		
		return nAnos * Hipoteca.NMENSUALIDADES;
		
	}
	
	
	/**
	 * 
	 * Método para recalcular los plazos restantes de la hipoteca
	 * 
	 * @param Hipoteca hipoteca
	 */
	default void recalcularPlazoRestante(Hipoteca hipoteca) {
		
		hipoteca.setPlazoRestante(hipoteca.getPlazoRestante()-Hipoteca.NMENSUALIDADES);
		
	}
	
	/////////////////////////////////////////////////////////////////
	
	// Métodos abstractos
	
	/**
	 * 
	 * Método para calcular la cuota que se deberá pagar al mes, incluyendo los intereses
	 * 
	 * @param Hipoteca hipoteca, se recibe una instancia de este objeto
	 * @return double cuota de la mensualidad
	 */
	public double calcularCuota(Hipoteca hipoteca);
	
	
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

}
