package com.practicas.simulador_hipotecas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;

@Service
public class HipotecaFijaServicio implements IHipotecaServicio{
	
	private static final float INTERES_MINIMO = 2.0f;
	
	@Autowired
	private AmortizacionServicio amortizacionServicio;

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
		
		float acumulador = 0.0f;
		
		double ahorros = hipoteca.getAhorros();
		double nomina = hipoteca.getNomina();
		int nCuotas = hipoteca.calcularNCuotas(hipoteca.getPlazo());
		double otrosPrestamos = hipoteca.getOtrosPrestamos();
		boolean primeraVivienda = hipoteca.isPrimeraVivienda();
		
		if(ahorros<10000) {
			acumulador+=5.0f;
		}
		
		if(nomina<=1000) {
			acumulador+=40.0f;
		}else if(nomina>1000 && nomina<2000) {
			acumulador+=20.0f;
		}else if(nomina>=2000) {
			acumulador+=5.0f;
		}
		
		if(nCuotas<=24) {
			acumulador+=10.0f;
		}else if(nCuotas>24 && nCuotas<48) {
			acumulador+=20.0f;
		}else if(nCuotas>=48) {
			acumulador+=30.0f;
		}
		
		if(otrosPrestamos>=10000 && otrosPrestamos<20000) {
			acumulador+=10.0f;
		}else if(otrosPrestamos>=20000) {
			acumulador+=20.0f;
		}
		
		if(primeraVivienda) {
			acumulador+=5.0f;
		}
		System.out.println(INTERES_MINIMO+(INTERES_MINIMO*(acumulador/100)));
		return INTERES_MINIMO+(INTERES_MINIMO*(acumulador/100));
	}
	
}
