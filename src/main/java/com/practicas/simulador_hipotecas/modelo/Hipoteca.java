package com.practicas.simulador_hipotecas.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Clase para contener los datos de una hipoteca
 * 
 * @author Marcos
 *
 */
public class Hipoteca {
	
	private static double totalIntereses;

	// Atributos
	private double capitalInmueble;
	private double capitalAportado;
	private double prestamo;
	private double cuota;
	private int plazo;
	private float tasaInteres;
	private InteresTipo tipoInteres;
	public List<Amortizacion> amortizaciones = new ArrayList<>();

	// Constructores

	public Hipoteca() {
		super();
	}

	public Hipoteca(double capitalInmueble, double capitalAportado, double prestamo, double cuota, int plazo,
			float tasaInteres, InteresTipo tipoInteres, List<Amortizacion> amortizaciones) {
		super();
		this.capitalInmueble = capitalInmueble;
		this.capitalAportado = capitalAportado;
		this.prestamo = prestamo;
		this.cuota = cuota;
		this.plazo = plazo;
		this.tasaInteres = tasaInteres;
		this.tipoInteres = tipoInteres;

	}

	// Métodos de acceso

	public double getCapitalInmueble() {
		return capitalInmueble;
	}

	public void setCapitalInmueble(double capitalInmueble) {
		this.capitalInmueble = capitalInmueble;
	}

	public double getCapitalAportado() {
		return capitalAportado;
	}

	public void setCapitalAportado(double capitalAportado) {
		this.capitalAportado = capitalAportado;
	}

	public double getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(double prestamo) {
		this.prestamo = prestamo;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public float getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(float tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public InteresTipo getTipoInteres() {
		return tipoInteres;
	}

	public void setTipoInteres(InteresTipo tipoInteres) {
		this.tipoInteres = tipoInteres;
	}

	public List<Amortizacion> getAmortizaciones() {
		return amortizaciones;
	}

	public void setAmortizaciones(List<Amortizacion> amortizaciones) {
		this.amortizaciones = amortizaciones;
	}
	
	public static double getTotalIntereses() {
		return totalIntereses;
	}

	public static void setTotalIntereses(double totalIntereses) {
		Hipoteca.totalIntereses = totalIntereses;
	}

	// Métodos

	/**
	 * Método para añadir una amortizacion a la hipoteca
	 * 
	 * @param Amortizacion amortizacion
	 */
	public void anadirAmortizacion(Amortizacion amortizacion) {
		this.amortizaciones.add(amortizacion);
	}

	@Override
	public String toString() {
		return "Hipoteca [capitalInmueble=" + capitalInmueble + ", capitalAportado=" + capitalAportado + ", prestamo="
				+ prestamo + ", cuota=" + cuota + ", plazo=" + plazo + ", interes=" + tasaInteres + ", tipoInteres="
				+ tipoInteres + ", amortizaciones=" + amortizaciones + "]";
	}



}
