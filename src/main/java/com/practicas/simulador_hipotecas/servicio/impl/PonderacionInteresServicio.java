package com.practicas.simulador_hipotecas.servicio.impl;

import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Hipoteca;

@Service
public class PonderacionInteresServicio {
	
	public static int acumuladorPesos;
	
	public static final float INTERESMAX = 4.0f;
	public static final float INTERESMIN = 2.0f;
	
	public static final float PESOTOTAL = 100f;
	public static final float PESONOMINA = 0.4f * PESOTOTAL;
	public static final float PESOCUOTA = 0.3f * PESOTOTAL;
	public static final float PESOAHORRO = 0.05f * PESOTOTAL;
	public static final float PESOPRESTAMOS = 0.2f * PESOTOTAL;
	public static final float PESOVIVIENDA = 0.05f * PESOTOTAL;

	public float calcularPeso() {
		
		float peso = (INTERESMAX-INTERESMIN)/PESOTOTAL;
		return peso;
		
	}

	public void calcularPesoNonima(double nomina, float pesoMaximo) {
		
		if(nomina<=1000) {
			acumuladorPesos+=pesoMaximo;
		}else if(nomina>1000 && nomina<2000) {
			acumuladorPesos+=20.0f;
		}else if(nomina>=2000) {
			acumuladorPesos+=5.0f;
		}
		
	}

	public void calcularPesoCuota(int nCuotas, float pesoMaximo) {
		
		if(nCuotas<=24) {
			acumuladorPesos+=10.0f;
		}else if(nCuotas>24 && nCuotas<48) {
			acumuladorPesos+=20.0f;
		}else if(nCuotas>=48) {
			acumuladorPesos+=pesoMaximo;
		}
	}

	public void calcularPesoAhorro(double ahorros, float pesoMaximo) {
		
		if(ahorros<10000) {
			acumuladorPesos+=pesoMaximo;
		}
		
	}

	public void calcularPesoOtrosPrestamos(double otrosPrestamos, float pesoMaximo) {

		if(otrosPrestamos>=10000 && otrosPrestamos<20000) {
			acumuladorPesos+=10.0f;
		}else if(otrosPrestamos>=20000) {
			acumuladorPesos+=pesoMaximo;
		}
		
	}

	public void calcularPesoPrimeraVivienda(boolean esPrimeraVivienda, float pesoMaximo) {
		
		if(esPrimeraVivienda) {
			acumuladorPesos+=pesoMaximo;
		}
		
	}

	public float calcularInteresTotal(Hipoteca hipoteca) {
		
		double ahorros = hipoteca.getAhorros();
		double nomina = hipoteca.getNomina();
		int nCuotas = hipoteca.calcularNCuotas(hipoteca.getPlazo());
		double otrosPrestamos = hipoteca.getOtrosPrestamos();
		boolean primeraVivienda = hipoteca.isPrimeraVivienda();
		
		calcularPesoAhorro(ahorros, PESOAHORRO);
		calcularPesoCuota(nCuotas, PESOCUOTA);
		calcularPesoNonima(nomina, PESONOMINA);
		calcularPesoOtrosPrestamos(otrosPrestamos, PESOPRESTAMOS);
		calcularPesoPrimeraVivienda(primeraVivienda, PESOVIVIENDA);
		
		
		
		float valorPeso = calcularPeso();
		int nPesos = acumuladorPesos;
		//resetear acumulador a 0
		acumuladorPesos = 0;
		
		float interesTotal = (valorPeso * nPesos) + INTERESMIN;
		
		return interesTotal;
		
	}

}
