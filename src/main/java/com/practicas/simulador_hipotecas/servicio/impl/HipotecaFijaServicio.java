package com.practicas.simulador_hipotecas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.servicio.IHipotecaServicio;

@Service
public class HipotecaFijaServicio implements IHipotecaServicio{
	
	private static final float INTERES_MINIMO = 2.0f;
	
	@Autowired
	private AmortizacionServicio amortizacionServicio;
	
	@Autowired
	private PonderacionInteresServicio ponderacionInteresServicio;

	@Override
	public void calcularCuota(Hipoteca hipoteca) {
		
		double cuota = 0.0;
		
		double prestamo = hipoteca.calcularValorDelPrestamo(hipoteca);
		int nAnos = hipoteca.getPlazo();
		int nCuotas = hipoteca.calcularNCuotas(nAnos);
		double tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());
		
		
		double numerador = tasaInteres * Math.pow(1 + tasaInteres, nCuotas);
		double denominador = Math.pow(1 + tasaInteres, nCuotas) - 1;
		
		cuota =  prestamo * (numerador/denominador);
		
		hipoteca.setCuota(cuota);
		
	}

	@Override
	public void calcularAmortizaciones(Hipoteca hipoteca) {
		
		//Obtener numero de cuotas
		int nCuotas = hipoteca.calcularNCuotas(hipoteca.getPlazo());

		//Obtener el porcentaje del interes a pagar en cada cuota
		float tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());
		
		for (int i = 1; i <= nCuotas; i++) {
			
			Amortizacion amortizacion = amortizacionServicio.crearAmortizacion(hipoteca, i, tasaInteres);
			hipoteca.anadirAmortizacion(amortizacion);
		
		}
		
	}

	@Override
	public float calcularTasaInteres(float tasaInteres) {
		return tasaInteres/(100*Hipoteca.NMENSUALIDADES);
	}

	@Override
	public float calcularTasaInteres(Hipoteca hipoteca) {
		
		float tasaInteres =  ponderacionInteresServicio.calcularInteresTotal(hipoteca);
		return tasaInteres;
	}
	
}
