package com.practicas.simulador_hipotecas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.modelo.InteresTipo;


/**
 * 
 * Servicio para la clase Servicio
 * 
 * @author mgomezgarrote
 *
 */
@Service
public class HipotecaServicio {
	
	private static final int NMENSUALIDADES = 12;
	//TODO pasar obtención del euribor a una API
	private static float EURIBOR = 0.5f/(100*12);
	
	@Autowired
	private AmortizacionServicio amortizacionServicio;

	/**
	 * 
	 * Método para calcular la cuota que se deberá pagar al mes, incluyendo los intereses
	 * 
	 * @param Hipoteca hipoteca, se recibe una instancia de este objeto
	 * @return double cuota de la mensualidad
	 */
	public double calcularCuota(Hipoteca hipoteca) {
		
		double cuota = 0.0;

		if(hipoteca.getPlazoRestante() == 0) {
			
			int nAnos = hipoteca.getPlazo();
			int cuotas = calcularNCuotas(nAnos);
			hipoteca.setPlazoRestante(cuotas);
		}
		
		int nCuotas = hipoteca.getPlazoRestante();
		System.out.println(hipoteca.getTasaInteres());
		double tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres(), hipoteca);
		
		double numerador = tasaInteres * Math.pow(1 + tasaInteres, nCuotas);
		double denominador = Math.pow(1 + tasaInteres, nCuotas) - 1;
		
		cuota = hipoteca.getPrestamo() * (numerador/denominador);
		
		// Setear valor de la cuota
		hipoteca.setCuota(cuota);
		return cuota;
		
	}
	
	
	public void calcularValorDelPrestamo(Hipoteca hipoteca) {
		
		double capitalInmueble = hipoteca.getCapitalInmueble();
		double capitalAportado = hipoteca.getCapitalAportado();
		
		// Setear valor del préstamo
		double prestamo = capitalInmueble-capitalAportado;
		hipoteca.setPrestamo(prestamo);
	}
	
	/**
	 * 
	 * Método para transformar el plazo en años en mensualidades
	 * 
	 * @param int nAnos, número de años que durará la hipoteca
	 * @return
	 */
	private int calcularNCuotas (int nAnos) {
		
		return nAnos * NMENSUALIDADES;
		
	}
	
	/**
	 * 
	 * Método calcular la tasa de interes en base a las mensualidades
	 * 
	 * @param float tasaInteres
	 * @return
	 */
	private float calcularTasaInteres(float tasaInteres, Hipoteca hipoteca) {
		
		//El interes es fijo o es variable
		boolean esInteresFijo = true;
		if(hipoteca.getTipoInteres().name().equals(InteresTipo.variable.name())) esInteresFijo = false;
		
		if(esInteresFijo)	return tasaInteres/(100*NMENSUALIDADES);
		return (tasaInteres/(100*NMENSUALIDADES))+EURIBOR;
	}
	
	/**
	 * 
	 * Método para calcular cada una de las amortizaciones que conforman una hipoteca
	 * 
	 * @param Hipoteca hipoteca
	 */
	public void calcularAmortizaciones(Hipoteca hipoteca){
				
		//Obtener numero de cuotas
		int nCuotas = calcularNCuotas(hipoteca.getPlazo());
		//Obtener cuota (interes + amortizacion)
		double cuota = hipoteca.getCuota();
		//Obtener importe a devolver
		double prestamo = hipoteca.getPrestamo();
		
		//Obtener el porcentaje del interes a pagar en cada cuota
		 float tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres(), hipoteca);

		 
		int acumuladorPlazos = 1;
		for (int i = 1; i <= nCuotas; i++) {
			
			// Si el acumulador de plazos llega a 12, es decir, se ha cumplido un año con la hipoteca
			// se tiene que recalcular la hipoteca
			if(acumuladorPlazos == 13 && hipoteca.getTipoInteres().equals(InteresTipo.variable)) {
				
				cuota = recalcularHipoteca(hipoteca);
				tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres(), hipoteca);
				prestamo = hipoteca.getPrestamo();
				acumuladorPlazos = 1;	
			}
			acumuladorPlazos++;
			
			Amortizacion amortizacion = amortizacionServicio.crearAmortizacion(i, cuota, tasaInteres, prestamo);
			hipoteca.anadirAmortizacion(amortizacion);
			
		}
			
	}
	
	private double recalcularHipoteca(Hipoteca hipoteca) {
		
//		//TODO 
//		//(Se recalcula cada año = cada 12 cuotas)
//		obtenerEURIBOR();
//		// Setear el plazo con las cuotas restantes
//		recalcularPlazoRestante(hipoteca);
//		// Obtener el capital por amortizar
//		double capitalPorAmortizar = Amortizacion.totalCapitalPorAmortizar;
//		System.out.println("capital por amortizar: " + capitalPorAmortizar);
//		// Calcular la cuota mensual
//		hipoteca.setPrestamo(capitalPorAmortizar);
		return calcularCuota(hipoteca);
		
	}
	
	public void obtenerEURIBOR() {
		float variacionEURIBOR = (float) ((Math.random() * ((1 - (-1)) + 1)) + (-1));
		EURIBOR += variacionEURIBOR/(100*12);
	}
	
	public void recalcularPlazoRestante(Hipoteca hipoteca) {
		
		int plazoRestante = hipoteca.getPlazoRestante();
		hipoteca.setPlazoRestante(plazoRestante-NMENSUALIDADES);
		
		
	}
		
}
