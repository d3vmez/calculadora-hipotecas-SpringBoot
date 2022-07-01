package com.practicas.simulador_hipotecas.servicio.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.controlador.HipotecaControlador;
import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.servicio.IHipotecaServicio;


/**
 * 
 * Implementación de la interfaz IHipotecaServicio
 * Este servicio se encarga de calcular la cuota y amortizaciones
 * de una hipoteca de interés variable
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Service
public class HipotecaVariableServicio implements IHipotecaServicio{
	
	Logger log = Logger.getLogger(HipotecaControlador.class);
	
	private static float EURIBOR = 0.2f/(100*Hipoteca.NMENSUALIDADES);
	private static final float DIFERENCIAL = 2.5f;

	@Autowired
	private AmortizacionServicio amortizacionServicio;

	@Override
	public void calcularCuota(Hipoteca hipoteca) {
		
		double cuota = 0.0;
		
		// Si el plazo restante es igual a 0, quiere decir que nos encontramos en el 
		// primer cálculo de la cuota por ello se debe establecer 
		// el plazo restante = número de cuotas totales,
		// también se aprovecha a inicializar el valor del presteamo
		if(hipoteca.getPlazoRestante() == 0) {
			
			hipoteca.setPlazoRestante(hipoteca.calcularNCuotas());
			hipoteca.calcularValorDelPrestamo();
			
		}
		
		// Como es una hipoteca variable se juega con el plazo restante, ya que la cuota
		// necesita ser recalculada
		int nCuotas = hipoteca.getPlazoRestante();
		double tasaInteres = calcularTasaInteres(hipoteca);
	
		// Numerador y denominador con la fórmula para el cálculo de la cuota
		double numerador = tasaInteres * Math.pow(1 + tasaInteres, nCuotas);
		double denominador = Math.pow(1 + tasaInteres, nCuotas) - 1;
		
		// Si la hipoteca no tiene amortizaciones, el capital por amortizar 
		// es igual al préstamo,
		// ya que nos encontramos en el cáclulo de la cuota para el primer año.
		// En caso contrario, se tomará el capital por amortizar que tiene 
		// la última amortizacion  de la hipoteca
		double capitalPorAmortizar = 0.0;
		if(hipoteca.getAmortizaciones().size() != 0) {
			capitalPorAmortizar = hipoteca.getAmortizaciones().get(hipoteca.getAmortizaciones().size() - 1).getCapitalPorAmortizar();
		
		}else {
			capitalPorAmortizar = hipoteca.getPrestamo();
			
		}
		
		cuota =  capitalPorAmortizar * (numerador/denominador);
		
		hipoteca.setCuota(cuota);
		
	}

	@Override
	public void calcularAmortizaciones(Hipoteca hipoteca) {
		
		//Calcular cuota de la hipoteca
		calcularCuota(hipoteca);

		//Obtener numero de cuotas
		int nCuotas = hipoteca.getPlazoRestante();
		//Obtener el porcentaje del interes a pagar en cada cuota
		float tasaInteres = calcularTasaInteres(hipoteca);

		int acumuladorPlazos = 1;
		for (int i = 1; i <= nCuotas; i++) {
			
			// Si el acumulador de plazos llega a 13, es decir, se ha cumplido un año con la hipoteca
			// se tiene que recalcular la hipoteca
			if(acumuladorPlazos == 13 ) {
				
				recalcularHipoteca(hipoteca);
				// Se actualizan la nueva tasa de interés
				tasaInteres = calcularTasaInteres(hipoteca);
				acumuladorPlazos = 1;	
			}
			acumuladorPlazos++;
			
			// Se crea la nueva amortización para la hipoteca variable
			Amortizacion amortizacion = amortizacionServicio.crearAmortizacion(hipoteca, i, tasaInteres);
			hipoteca.anadirAmortizacion(amortizacion);
			
		}

	}
	
	@Override
	public float calcularTasaInteres(Hipoteca hipoteca) {
		
		float tasaInteres = hipoteca.getTasaInteres();
		// Si la hipoteca no tiene una tasa de interés, se le aplica
		// el diferencial (interés fijo en una hipoteca variable)
		// (SINGLETON)
		if (tasaInteres == 0.0f) {
			tasaInteres = DIFERENCIAL;
			hipoteca.setTasaInteres(tasaInteres);
		}
		
		// Se devuelve el interes calculado más el EURIBOR
		return (tasaInteres/(100*Hipoteca.NMENSUALIDADES))+EURIBOR;
			
	}

	// Métodos auxiliares
	/////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * Método para cambiar el valor del EURIBOR
	 * 
	 */
	private void variarEURIBOR() {
		
		EURIBOR = 0.2f/(100*Hipoteca.NMENSUALIDADES);
		float variacionEURIBOR = (float) ((Math.random() * ((1 - (-1)) + 1)) + (-1));
		EURIBOR += variacionEURIBOR/(100*Hipoteca.NMENSUALIDADES);
	
	}
	
	/**
	 * 
	 * Método para recalcular la cuota, con un nuevo valor para el euribor
	 * y un número diferente de cuotas restantes (plazo)
	 * 
	 * @param Hipoteca hipoteca
	 */
	private void recalcularHipoteca(Hipoteca hipoteca) {
		
		// Simular el cambio del Euribor
		variarEURIBOR();
		// Actualizar el número de plazos restantes para finalizar la hipoteca
		hipoteca.recalcularPlazoRestante(hipoteca);
		// Calcular la nueva cuota para el siguiente año
		calcularCuota(hipoteca);
		
	}

}