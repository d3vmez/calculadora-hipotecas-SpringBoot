package com.practicas.simulador_hipotecas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.servicio.IHipotecaServicio;

/**
 * 
 * Implementación de la interfaz IHipotecaServicio Este servicio se encarga de
 * calcular la cuota y amortizaciones de una hipoteca de interés fijo
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Service
public class HipotecaFijaServicio implements IHipotecaServicio {

	@Autowired
	private AmortizacionServicio amortizacionServicio;

	@Autowired
	private PonderacionInteresServicio ponderacionInteresServicio;

	@Override
	public void calcularCuota(Hipoteca hipoteca) {

		double cuota = 0.0;

		// Calcular el valor del préstamo
		hipoteca.calcularValorDelPrestamo();
		double prestamo = hipoteca.getPrestamo();
		int nCuotas = hipoteca.calcularNCuotas();
		double tasaInteres = calcularTasaInteres(hipoteca);

		// Numerador y denominador con la fórmula para el cálculo de la cuota
		double numerador = tasaInteres * Math.pow(1 + tasaInteres, nCuotas);
		double denominador = Math.pow(1 + tasaInteres, nCuotas) - 1;

		cuota = prestamo * (numerador / denominador);

		hipoteca.setCuota(cuota);

	}

	@Override
	public void calcularAmortizaciones(Hipoteca hipoteca) {

		// Calcular cuota mensual de la hipoteca
		calcularCuota(hipoteca);

		// Obtener numero de cuotas
		int nCuotas = hipoteca.calcularNCuotas();

		// Obtener el porcentaje del interes a pagar en cada cuota
		float tasaInteres = calcularTasaInteres(hipoteca);

		for (int i = 1; i <= nCuotas; i++) {

			// Se crea la nueva amortización para la hipoteca fija
			Amortizacion amortizacion = amortizacionServicio.crearAmortizacion(hipoteca, i, tasaInteres);
			hipoteca.anadirAmortizacion(amortizacion);

		}

	}

	@Override
	public float calcularTasaInteres(Hipoteca hipoteca) {

		float tasaInteres = hipoteca.getTasaInteres();
		// Si la hipoteca no tiene una tasa de interés, se cálcula
		// mediante una ponderación sobre los parámetros que afectan
		// al valor del interés.
		// (SINGLETON)
		if (tasaInteres == 0.0f) {
			tasaInteres = ponderacionInteresServicio.calcularInteresTotal(hipoteca);
			hipoteca.setTasaInteres(tasaInteres);
		}

		tasaInteres = tasaInteres / (100 * Hipoteca.NMENSUALIDADES);

		// Se devuelve el interes calculado
		return tasaInteres;

	}

}
