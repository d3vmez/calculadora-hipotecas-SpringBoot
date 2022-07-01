package com.practicas.simulador_hipotecas.servicio.impl;

import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Hipoteca;

/**
 * 
 * Este servicio se encarga de generar un valor para el interés
 * de una hipoteca a tipo fijo según unos parámetros
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Service
public class PonderacionInteresServicio {
	
	private static int acumuladorPesos;
	
	public static final float INTERESMAX = 4.0f;
	public static final float INTERESMIN = 2.0f;
	
	public static final float PESOTOTAL = 100f;
	
	// Peso máximo que pueden tener cada uno de los parámetros
	public static final float PESONOMINA = 0.4f * PESOTOTAL;
	public static final float PESOCUOTA = 0.3f * PESOTOTAL;
	public static final float PESOAHORRO = 0.05f * PESOTOTAL;
	public static final float PESOPRESTAMOS = 0.2f * PESOTOTAL;
	public static final float PESOVIVIENDA = 0.05f * PESOTOTAL;

	/**
	 * 
	 * Método para calcular el interés de la hipoteca a tipo fijo
	 * 
	 * @param Hipoteca hipoteca
	 * @return float interés de la hipoteca fija
	 */
	public float calcularInteresTotal(Hipoteca hipoteca) {
		
		// Obtener valor de los parámetros
		double ahorros = hipoteca.getAhorros();
		double nomina = hipoteca.getNomina();
		int nCuotas = hipoteca.calcularNCuotas();
		double otrosPrestamos = hipoteca.getOtrosPrestamos();
		boolean primeraVivienda = hipoteca.isPrimeraVivienda();
		
		// Calcular cada uno de los pesos de los parámetros
		calcularPesoAhorro(ahorros, PESOAHORRO);
		calcularPesoCuota(nCuotas, PESOCUOTA);
		calcularPesoNonima(nomina, PESONOMINA);
		calcularPesoOtrosPrestamos(otrosPrestamos, PESOPRESTAMOS);
		calcularPesoPrimeraVivienda(primeraVivienda, PESOVIVIENDA);
		
		// Calcular el valor en porcentaje de un único peso
		float valorPeso = calcularPeso();
		
		// Número de pesos totales
		int nPesos = acumuladorPesos;
		
		// Resetear acumulador a 0
		acumuladorPesos = 0;
		
		// Calcular interés
		float interesTotal = (valorPeso * nPesos) + INTERESMIN;
		
		return interesTotal;
		
	}
	
	// Métodos auxiliares
	/////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * Método para calcular el valor de un único peso
	 * 
	 * @return float peso
	 */
	private float calcularPeso() {
		
		float peso = (INTERESMAX-INTERESMIN)/PESOTOTAL;
		return peso;
		
	}

	/**
	 * 
	 * Método para calcular el peso que tiene la nómina sobre
	 * el interés
	 * 
	 * @param double nomina
	 * @param float pesoMaximo
	 */
	private void calcularPesoNonima(double nomina, float pesoMaximo) {
		
		if(nomina<=1000) {
			acumuladorPesos+=pesoMaximo;
		}else if(nomina>1000 && nomina<2000) {
			acumuladorPesos+=20.0f;
		}else if(nomina>=2000) {
			acumuladorPesos+=5.0f;
		}
		
	}

	/**
	 * 
	 * Método para calcular el peso que tienen el número de cuotas 
	 * sobre el interés
	 * 
	 * @param int nCuotas (en meses)
	 * @param float pesoMaximo
	 */
	private void calcularPesoCuota(int nCuotas, float pesoMaximo) {
		
		if(nCuotas<=24) {
			acumuladorPesos+=10.0f;
		}else if(nCuotas>24 && nCuotas<48) {
			acumuladorPesos+=20.0f;
		}else if(nCuotas>=48) {
			acumuladorPesos+=pesoMaximo;
		}
	}

	/**
	 * 
	 * Método para calcular el peso que tienen los ahorros del cliente 
	 * sobre el interés
	 * 
	 * @param double ahorros 
	 * @param float pesoMaximo
	 */
	private void calcularPesoAhorro(double ahorros, float pesoMaximo) {
		
		if(ahorros<10000) {
			acumuladorPesos+=pesoMaximo;
		}
		
	}

	/**
	 * 
	 * Método para calcular el peso que tienen las deudas del cliente 
	 * sobre el interés
	 * 
	 * @param double total de las deudas 
	 * @param float pesoMaximo
	 */
	private void calcularPesoOtrosPrestamos(double otrosPrestamos, float pesoMaximo) {

		if(otrosPrestamos>=10000 && otrosPrestamos<20000) {
			acumuladorPesos+=10.0f;
		}else if(otrosPrestamos>=20000) {
			acumuladorPesos+=pesoMaximo;
		}
		
	}

	/**
	 * 
	 * Método para calcular el peso que tiene si es primera vivienda 
	 * sobre el interés
	 * 
	 * @param double ahorros 
	 * @param float pesoMaximo
	 */
	private void calcularPesoPrimeraVivienda(boolean esPrimeraVivienda, float pesoMaximo) {
		
		if(esPrimeraVivienda) {
			acumuladorPesos+=pesoMaximo;
		}
		
	}

}
