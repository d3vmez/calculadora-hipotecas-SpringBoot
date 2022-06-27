package com.practicas.simulador_hipotecas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.servicio.IHipotecaServicio;


@Service
public class HipotecaVariableServicio implements IHipotecaServicio{
	
	private static float EURIBOR = 0.2f/(100*12);

	@Autowired
	private AmortizacionServicio amortizacionServicio;

	@Override
	public void calcularCuota(Hipoteca hipoteca) {
		
		double cuota = 0.0;
		
		inicializarPlazoRestante(hipoteca);
		int nCuotas = hipoteca.getPlazoRestante();
		double tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());
		
		double numerador = tasaInteres * Math.pow(1 + tasaInteres, nCuotas);
		double denominador = Math.pow(1 + tasaInteres, nCuotas) - 1;
		
		cuota =  hipoteca.getPrestamo() * (numerador/denominador);
		hipoteca.setCuota(cuota);
		
	}

	@Override
	public void calcularAmortizaciones(Hipoteca hipoteca) {
		
		//Calcular cuota de la hipoteca
		calcularCuota(hipoteca);

		//Obtener numero de cuotas
		int nCuotas = hipoteca.calcularNCuotas(hipoteca.getPlazo());
		//Obtener el porcentaje del interes a pagar en cada cuota
		 float tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());

		 
		int acumuladorPlazos = 1;
		for (int i = 1; i <= nCuotas; i++) {
			
			// Si el acumulador de plazos llega a 12, es decir, se ha cumplido un año con la hipoteca
			// se tiene que recalcular la hipoteca
			if(acumuladorPlazos == 13 ) {
				
				recalcularHipoteca(hipoteca);
				tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());
			
				acumuladorPlazos = 1;	
			}
			acumuladorPlazos++;
			
			Amortizacion amortizacion = amortizacionServicio.crearAmortizacion(hipoteca, i, tasaInteres);
			hipoteca.anadirAmortizacion(amortizacion);
			
		}

	}

	@Override
	public float calcularTasaInteres(float tasaInteres) {
		return (tasaInteres/(100*Hipoteca.NMENSUALIDADES))+EURIBOR;
	}
	
	private void obtenerEURIBOR() {
		EURIBOR = 0.2f/(100*12);
		float variacionEURIBOR = (float) ((Math.random() * ((1 - (-1)) + 1)) + (-1));
		EURIBOR += variacionEURIBOR/(100*Hipoteca.NMENSUALIDADES);
	
	}
	
	private void inicializarPlazoRestante(Hipoteca hipoteca) {
		if(hipoteca.getPlazoRestante() == 0) {
			
			int nAnos = hipoteca.getPlazo();
			int cuotas = hipoteca.calcularNCuotas(nAnos);
			hipoteca.setPlazoRestante(cuotas);
			hipoteca.calcularValorDelPrestamo(hipoteca);
		}
	}
	
	private void recalcularHipoteca(Hipoteca hipoteca) {
		
		//(Se recalcula cada año = cada 12 cuotas)
		obtenerEURIBOR();
		// Setear el plazo con las cuotas restantes
		hipoteca.recalcularPlazoRestante(hipoteca);
		// Obtener el capital por amortizar
		double capitalPorAmortizar = Amortizacion.totalCapitalPorAmortizar;
		// Calcular la cuota mensual
		hipoteca.setPrestamo(capitalPorAmortizar);
		calcularCuota(hipoteca);
		
	}

	@Override
	public void calcularTasaInteres(Hipoteca hipoteca) {
		// TODO Auto-generated method stub
		
	}

}