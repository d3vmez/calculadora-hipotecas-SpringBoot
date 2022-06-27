package com.practicas.simulador_hipotecas.modelo;

/**
 * 
 * Clase para una amortización
 * 
 * @author Marcos
 *
 */
public class Amortizacion {
	
	public static double totalCapitalPorAmortizar;
	
	private int numeroCuota;
	private double cuota;
	private double interes;
	private double cuotaAmortizacion;
	private double totalAmortizado;
	private double capitalPorAmortizar;
	
	// Constructores
	public Amortizacion() {
	
	}

	public Amortizacion(int numeroCuota, double cuota, double interes, double cuotaAmortizacion, double totalAmortizado,
			double capitalPorAmortizar) {
		super();
		this.numeroCuota = numeroCuota;
		this.cuota = cuota;
		this.interes = interes;
		this.cuotaAmortizacion = cuotaAmortizacion;
		this.totalAmortizado = totalAmortizado;
		this.capitalPorAmortizar = capitalPorAmortizar;
	}
	
	// Métodos de acceso
	
	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public double getCuotaAmortizacion() {
		return cuotaAmortizacion;
	}

	public void setCuotaAmortizacion(double cuotaAmortizacion) {
		this.cuotaAmortizacion = cuotaAmortizacion;
	}

	public double getTotalAmortizado() {
		return totalAmortizado;
	}

	public void setTotalAmortizado(double totalAmortizado) {
		this.totalAmortizado = totalAmortizado;
	}

	public double getCapitalPorAmortizar() {
		return capitalPorAmortizar;
	}

	public void setCapitalPorAmortizar(double capitalPorAmortizar) {
		this.capitalPorAmortizar = capitalPorAmortizar;
		totalCapitalPorAmortizar = capitalPorAmortizar;
	}

//	@Override
//	public String toString() {
//		return "Amortizacion [numeroCuota=" + numeroCuota + ", cuota=" + cuota + ", interes=" + interes
//				+ ", cuotaAmortizacion=" + cuotaAmortizacion + ", totalAmortizado=" + totalAmortizado
//				+ ", capitalPorAmortizar=" + capitalPorAmortizar + "]";
//	}

}
