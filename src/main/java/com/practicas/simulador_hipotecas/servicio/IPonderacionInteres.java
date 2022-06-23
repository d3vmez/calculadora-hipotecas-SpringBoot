package com.practicas.simulador_hipotecas.servicio;

import com.practicas.simulador_hipotecas.modelo.Hipoteca;

public interface IPonderacionInteres {
	
	public static final float INTERESMAX = 4.0f;
	public static final float INTERESMIN = 2.0f;
	
	public static final float PESOTOTAL = 100f;
	public static final float PESONOMINA = 0.4f * PESOTOTAL;
	public static final float PESOCUOTA = 0.3f * PESOTOTAL;
	public static final float PESOAHORRO = 0.05f * PESOTOTAL;
	public static final float PESOPRESTAMOS = 0.2f * PESOTOTAL;
	public static final float PESOVIVIENDA = 0.05f * PESOTOTAL;
	
	/**
	 * Método para calcular lo que supone al interés un único peso, es decir, 1 peso = 0.04%
	 * Este valor se calcula con la siguiente fórmula => valorPeso = (interesMax - interesMin) / pesoTotal
	 * 
	 * @param float pesoTotal, valor máximo que pueden sumar los pesos de cada parámetro
	 * @param float interesMax, valor del interés fijo máximo
	 * @param float interesMin, valor del interés fijo mínimo
	 * 
	 * @return float valor de un único peso
	 */
	public float calcularPeso(float pesoTotal, float interesMax, float interesMin);
	
	
	/**
	 * Métododo para calcular el peso que tiene la nomina sonbre el interés
	 * 
	 * @param double nomina, valor de la nómina del cliente
	 * @param float pesoMaximo, valor máximo que puede influir la nómina en el interés
	 */
	public void calcularPesoNonima(double nomina, float pesoMaximo);
	
	/**
	 * 
	 * Métododo para calcular el peso que tiene las cuotas sobre el interés
	 * 
	 * @param int nCuotas, número de cuotas que tiene la hipoteca
	 * @param float pesoMaximo, valor máximo que pueden influir las cuotas en el interés
	 */
	public void calcularPesoCuota(int nCuotas, float pesoMaximo);
	
	
	/**
	 * 
	 * Métododo para calcular el ahorro que tiene las cuotas sobre el interés
	 * 
	 * @param double ahorros, valor de los ahorros del cliente
	 * @param float pesoMaximo, valor máximo que pueden influir los ahorros sobre el interes
	 */
	public void calcularPesoAhorro(double ahorros, float pesoMaximo);
	public void calcularPesoOtrosPrestamos(double otrosPrestamos, float pesoMaximo);
	public void calcularPesoPrimeraVivienda(boolean esPrimeraVivienda, float pesoMaximo);
	public float calcularInteresTotal(Hipoteca hipoteca);

	
	
	
	
	
	


}
