package com.practicas.simulador_hipotecas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.modelo.Simulacion;

@Service
public class SimulacionServicio {
	
	private static final int NSIMULACIONES = 100;
	
	@Autowired
	private HipotecaFijaServicio hipotecaFijaServicio = new HipotecaFijaServicio();
	
	@Autowired
	private HipotecaVariableServicio hipotecaVariableServicio = new HipotecaVariableServicio();
	
	public void generarHipotecas(Hipoteca hipoteca, Simulacion simulacion) {
		
		// Generar hipoteca fija
		Hipoteca hipotecaFija = new Hipoteca(hipoteca);

			hipotecaFijaServicio.calcularCuota(hipotecaFija);
			hipotecaFijaServicio.calcularAmortizaciones(hipotecaFija);
			simulacion.setHipotecaFija(hipotecaFija);

		// Generar hipotecas variable
		for (int i = 0; i < NSIMULACIONES; i++) {
			
			Hipoteca hipotecaVariable = new Hipoteca(hipoteca);
			
				hipotecaVariableServicio.calcularCuota(hipotecaVariable);
				hipotecaVariableServicio.calcularAmortizaciones(hipotecaVariable);
				simulacion.setHipotecasVariables(hipotecaVariable);
			
		}
		
	}
	
	public void calcularProbabilidad(Simulacion simulacion) {
			
		int acumulador = 0;
		double interesFijo = calcularTotalIntereses(simulacion.getHipotecaFija());
	
		for (int i = 0; i < simulacion.getHipotecasVariables().size(); i++) {
			
			double interesVariable = calcularTotalIntereses(simulacion.getHipotecasVariables().get(i));
	
			if(interesFijo > interesVariable) {
				acumulador ++;
			}
				
		}
		
		//Porcentaje de que el interés fijo sea más caro que el variable
		float porcentaje = (acumulador / (float)NSIMULACIONES) * 100;	
		simulacion.setPorcentaje(porcentaje);
		
//		System.out.println("Acumulador = " + acumulador);
//		System.out.println("Porcentaje = " + porcentaje + "%");
	}
	
	private double calcularTotalIntereses(Hipoteca hipoteca) {
		
		double totalIntereses = 0.0;
		for (Amortizacion amortizacion : hipoteca.getAmortizaciones()) {
			totalIntereses += amortizacion.getInteres();
		}
		
		return totalIntereses;
	}

}
