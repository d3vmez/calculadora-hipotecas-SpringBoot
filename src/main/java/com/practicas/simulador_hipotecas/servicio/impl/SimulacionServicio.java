package com.practicas.simulador_hipotecas.servicio.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.controlador.HipotecaControlador;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.modelo.Simulacion;

/**
 * 
 * Este servicio se encarga de obtener la probabilidad de que una
 * hipoteca variable sea más barata que una de tipo fijo
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Service
public class SimulacionServicio {
	
	Logger log = Logger.getLogger(HipotecaControlador.class);
	
	private static final int NSIMULACIONES = 100;
	
	@Autowired
	private HipotecaFijaServicio hipotecaFijaServicio;
	
	@Autowired
	private HipotecaVariableServicio hipotecaVariableServicio;
	
	/**
	 * 
	 * Método para generar una hipoteca fija y una lista de hipotecas variables
	 * 
	 * @param Hipoteca hipoteca, objeto que viene con los datos del formulario
	 * @param Simulacion simulacion
	 */
	public void generarHipotecas(Hipoteca hipoteca, Simulacion simulacion) {
		
		// Generar hipoteca fija
		// Se clona un objeto de hipoteca para no interferir en los datos
		// de la hipoteca que se muestran por pantalla
		Hipoteca hipotecaFija = new Hipoteca(hipoteca);

			// Se generán las amortizaciones
			hipotecaFijaServicio.calcularAmortizaciones(hipotecaFija);
			// Se guarda la hipoteca fija en el objeto Simulacion
			simulacion.setHipotecaFija(hipotecaFija);
			
		// Generar hipotecas variable, tantas como se indiquen en la constante
		// NSIMULACIONES
		for (int i = 0; i < NSIMULACIONES; i++) {
			
			Hipoteca hipotecaVariable = new Hipoteca(hipoteca);
			
				hipotecaVariableServicio.calcularAmortizaciones(hipotecaVariable);
				simulacion.setHipotecasVariables(hipotecaVariable);
				
		}
	
	}
	
	/**
	 * 
	 * Método para calcular la probabilidad de que una hipoteca a tipo variable
	 * sea más barata que una de tipo fijo
	 * 
	 * @param Simulacion simulacion
	 */
	public void calcularProbabilidad(Simulacion simulacion) {
		
		// Variable para acumular las veces que el interés total de una
		// hipoteca variable es menor que una de tipo fijo
		int acumulador = 0;
		
		// Obtener los intereses totales de la hipoteca a tipo fijo
		double interesFijo = simulacion.getHipotecaFija().getTotalIntereses();
		
		// Por cada hipoteca variable, se comprueba los intereses totales de cada
		// una de ellas con el interes total de la hipoteca fija
		for (int i = 0; i < simulacion.getHipotecasVariables().size(); i++) {
			
			// Obtener los intereses totales de la hipoteca a tipo variable
			double interesVariable = simulacion.getHipotecasVariables().get(i).getTotalIntereses();

			// Si el interes de la hipoteca fija es mayor se aumenta el acumulador
			if(interesFijo > interesVariable) {
				acumulador ++;
			}
				
		}
		
		//Porcentaje de que el interés fijo sea más caro que el variable
		float porcentaje = (acumulador / (float)NSIMULACIONES) * 100;	
		simulacion.setPorcentaje(porcentaje);
		
	}
	
}
