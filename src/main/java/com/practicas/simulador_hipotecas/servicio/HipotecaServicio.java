package com.practicas.simulador_hipotecas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;


/**
 * 
 * Servicio para la clase Servicio
 * 
 * @author mgomezgarrote
 *
 */
@Service
public class HipotecaServicio {
	
	private static final int NMENSUALIDADES = 12;
	
	@Autowired
	private AmortizacionServicio amortizacionServicio;

	/**
	 * 
	 * Método para calcular la cuota que se deberá pagar al mes, incluyendo los intereses
	 * 
	 * @param Hipoteca hipoteca, se recibe una instancia de este objeto
	 * @return double cuota de la mensualidad
	 */
	public double calcularCuota(Hipoteca hipoteca) {
		
		double cuota = 0.0;
		double capitalInmueble = hipoteca.getCapitalInmueble();
		double capitalAportado = hipoteca.getCapitalAportado();
		
		// Setear valor del préstamo
		double prestamo = capitalInmueble-capitalAportado;
		hipoteca.setPrestamo(prestamo);
		
		int nCuotas = calcularNCuotas(hipoteca.getPlazo());
		double tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());
		
		double numerador = tasaInteres * Math.pow(1 + tasaInteres, nCuotas);
		double denominador = Math.pow(1 + tasaInteres, nCuotas) - 1;
		
		cuota = prestamo * (numerador/denominador);
		
		// Setear valor de la cuota
		hipoteca.setCuota(cuota);
		System.out.println("aaa" +tasaInteres);
		System.out.println(cuota);
		return cuota;
		
	}
	
	/**
	 * 
	 * Método para transformar el plazo en años en mensualidades
	 * 
	 * @param int nAnos, número de años que durará la hipoteca
	 * @return
	 */
	private int calcularNCuotas (int nAnos) {
		
		return nAnos * NMENSUALIDADES;
		
	}
	
	/**
	 * 
	 * Método calcular la tasa de interes en base a las mensualidades
	 * 
	 * @param float tasaInteres
	 * @return
	 */
	private float calcularTasaInteres(float tasaInteres) {
		
		return tasaInteres/(100*NMENSUALIDADES);
	}
	
	/**
	 * 
	 * Método para calcular cada una de las amortizaciones que conforman una hipoteca
	 * 
	 * @param Hipoteca hipoteca
	 */
	public void calcularAmortizaciones(Hipoteca hipoteca){
		
		//Obtener numero de cuotas
		int nCuotas = calcularNCuotas(hipoteca.getPlazo());
		//Obtener cuota (interes + amortizacion)
		double cuota = hipoteca.getCuota();
		//Obtener importe a devolver
		double prestamo = hipoteca.getPrestamo();
		//Obtener el porcentaje del interes a pagar en cada cuota
		float tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());
		
		for (int i = 0; i <= nCuotas; i++) {
			
			Amortizacion amortizacion = amortizacionServicio.crearAmortizacion(i, cuota, tasaInteres, prestamo);
			hipoteca.anadirAmortizacion(amortizacion);
		
		}
			
	}
	
}
