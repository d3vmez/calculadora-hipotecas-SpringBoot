package com.practicas.simulador_hipotecas.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * Clase para contener los datos de una hipoteca
 * 
 * @author Marcos
 *
 */
public class Hipoteca implements Cloneable{
	
	private double totalIntereses;
	public static final int NMENSUALIDADES = 12;

	// Atributos
	private double capitalInmueble;
	private double capitalAportado;
	private double prestamo;
	private double cuota;
	private int plazo;
	private int plazoRestante;
	private float tasaInteres;
	private InteresTipo tipoInteres;
	public List<Amortizacion> amortizaciones = new ArrayList<>();
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edad;
	private double ahorros;
	private double nomina;
	private double otrosPrestamos;
	private boolean primeraVivienda;

	// Constructores

	public Hipoteca(double capitalInmueble, double capitalAportado, double prestamo, double cuota, int plazo,
			float tasaInteres, InteresTipo tipoInteres, List<Amortizacion> amortizaciones, int plazoRestante) {
		super();
		this.capitalInmueble = capitalInmueble;
		this.capitalAportado = capitalAportado;
		this.prestamo = prestamo;
		this.cuota = cuota;
		this.plazo = plazo;
		this.plazoRestante = plazoRestante;
		this.tasaInteres = tasaInteres;
		this.tipoInteres = tipoInteres;

	}
	
	public Hipoteca(double totalIntereses, double capitalInmueble, double capitalAportado, double prestamo,
			double cuota, int plazo, int plazoRestante, float tasaInteres, InteresTipo tipoInteres,
			List<Amortizacion> amortizaciones, Date edad, double ahorros, double nomina, double otrosPrestamos,
			boolean primeraVivienda) {
		super();
		this.totalIntereses = totalIntereses;
		this.capitalInmueble = capitalInmueble;
		this.capitalAportado = capitalAportado;
		this.prestamo = prestamo;
		this.cuota = cuota;
		this.plazo = plazo;
		this.plazoRestante = plazoRestante;
		this.tasaInteres = tasaInteres;
		this.tipoInteres = tipoInteres;
		this.amortizaciones = amortizaciones;
		this.edad = edad;
		this.ahorros = ahorros;
		this.nomina = nomina;
		this.otrosPrestamos = otrosPrestamos;
		this.primeraVivienda = primeraVivienda;
	}



	public Hipoteca(Hipoteca hipoteca) {
		this.capitalInmueble = hipoteca.getCapitalInmueble();
		this.capitalAportado = hipoteca.getCapitalAportado();
		this.prestamo = hipoteca.getPrestamo();
		this.cuota = hipoteca.getCuota();
		this.plazo = hipoteca.getPlazo();
		this.plazoRestante = hipoteca.getPlazoRestante();
		this.tasaInteres = hipoteca.getTasaInteres();
		this.tipoInteres = hipoteca.getTipoInteres();
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
	
	public double getTotalIntereses() {
		return totalIntereses;
	}

	public void setTotalIntereses(double totalIntereses) {
		this.totalIntereses += totalIntereses;
	}
	
	public int getPlazoRestante() {
		return plazoRestante;
	}

	public void setPlazoRestante(int plazoRestante) {
		this.plazoRestante = plazoRestante;
	}
	

	public Hipoteca() {
		super();
	}

	public Date getEdad() {
		return edad;
	}

	public void setEdad(Date edad) {
		this.edad = edad;
	}

	public double getAhorros() {
		return ahorros;
	}

	public void setAhorros(double ahorros) {
		this.ahorros = ahorros;
	}

	public double getNomina() {
		return nomina;
	}

	public void setNomina(double nomina) {
		this.nomina = nomina;
	}

	public double getOtrosPrestamos() {
		return otrosPrestamos;
	}

	public void setOtrosPrestamos(double otrosPrestamos) {
		this.otrosPrestamos = otrosPrestamos;
	}

	public boolean isPrimeraVivienda() {
		return primeraVivienda;
	}

	public void setPrimeraVivienda(boolean primeraVivienda) {
		this.primeraVivienda = primeraVivienda;
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
	
	public boolean esTipoFijo() {
		if(this.tipoInteres == (InteresTipo.fijo)) return true;
		return false;
	}

	/**
	 * 
	 * Método para calcular el importe final del préstamo
	 * 
	 * @param Hipoteca hipoteca
	 */
	public double calcularValorDelPrestamo(Hipoteca hipoteca) {
		
		double capitalInmueble = hipoteca.getCapitalInmueble();
		double capitalAportado = hipoteca.getCapitalAportado();
		
		double prestamo = capitalInmueble-capitalAportado;
		hipoteca.setPrestamo(prestamo);
		
		return prestamo;
	}
	
	/**
	 * 
	 * Método para transformar el plazo en años en mensualidades
	 * 
	 * @param int nAnos, número de años que durará la hipoteca
	 * @return int número de mensualidades
	 */
	public int calcularNCuotas (int nAnos) {
		
		return nAnos * Hipoteca.NMENSUALIDADES;
		
	}
	
	
	/**
	 * 
	 * Método para recalcular los plazos restantes de la hipoteca
	 * 
	 * @param Hipoteca hipoteca
	 */
	public void recalcularPlazoRestante(Hipoteca hipoteca) {
		
		hipoteca.setPlazoRestante(hipoteca.getPlazoRestante()-Hipoteca.NMENSUALIDADES);
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}


	@Override
	public String toString() {
		return "Hipoteca [totalIntereses=" + totalIntereses + ", capitalInmueble=" + capitalInmueble
				+ ", capitalAportado=" + capitalAportado + ", prestamo=" + prestamo + ", cuota=" + cuota + ", plazo="
				+ plazo + ", plazoRestante=" + plazoRestante + ", tasaInteres=" + tasaInteres + ", tipoInteres="
				+ tipoInteres + ", amortizaciones=" + amortizaciones + ", edad=" + edad + ", ahorros=" + ahorros
				+ ", nomina=" + nomina + ", otrosPrestamos=" + otrosPrestamos + ", primeraVivienda=" + primeraVivienda
				+ "]";
	}
	




}
