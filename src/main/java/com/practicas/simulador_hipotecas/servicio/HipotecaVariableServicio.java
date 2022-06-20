package com.practicas.simulador_hipotecas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.modelo.InteresTipo;

@Service
public class HipotecaVariableServicio implements IHipotecaServicio{
	
	private static float EURIBOR = 0.5f/(100*12);
	private static final int EURIBOR_MIN=-1;
	private static final int EURIBOR_MAX=1;
	
	@Autowired
	private AmortizacionServicio amortizacionServicio;

	@Override
	public double calcularCuota(Hipoteca hipoteca) {
		double cuota = 0.0;
		
		inicializarPlazoRestante(hipoteca);
		int nCuotas = hipoteca.getPlazoRestante();
		double tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());
		
		double numerador = tasaInteres * Math.pow(1 + tasaInteres, nCuotas);
		double denominador = Math.pow(1 + tasaInteres, nCuotas) - 1;
		
		cuota =  hipoteca.getPrestamo() * (numerador/denominador);
		hipoteca.setCuota(cuota);
		
		return cuota;

	}

	@Override
	public void calcularAmortizaciones(Hipoteca hipoteca) {
		//Obtener numero de cuotas
		int nCuotas = hipoteca.calcularNCuotas(hipoteca.getPlazo());
		//Obtener cuota (interes + amortizacion)
		double cuota = hipoteca.getCuota();
		//Obtener importe a devolver
		double prestamo = hipoteca.getPrestamo();
		
		//Obtener el porcentaje del interes a pagar en cada cuota
		 float tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());

		 
		int acumuladorPlazos = 1;
		for (int i = 1; i <= nCuotas; i++) {
			
			// Si el acumulador de plazos llega a 12, es decir, se ha cumplido un año con la hipoteca
			// se tiene que recalcular la hipoteca
			if(acumuladorPlazos == 13 && hipoteca.getTipoInteres().equals(InteresTipo.variable)) {
				
				cuota = recalcularHipoteca(hipoteca);
				tasaInteres = calcularTasaInteres(hipoteca.getTasaInteres());
				prestamo = hipoteca.getPrestamo();
				acumuladorPlazos = 1;	
			}
			acumuladorPlazos++;
			
			Amortizacion amortizacion = amortizacionServicio.crearAmortizacion(i, cuota, tasaInteres, prestamo);
			hipoteca.anadirAmortizacion(amortizacion);
			
		}
		AmortizacionServicio.capitalPorAmortizar = 0.0;
		AmortizacionServicio.totalPorAmortizar = 0.0;
		System.out.println(AmortizacionServicio.capitalPorAmortizar);
	}

	@Override
	public float calcularTasaInteres(float tasaInteres) {
		return (tasaInteres/(100*Hipoteca.NMENSUALIDADES))+EURIBOR;
	}
	
	private void obtenerEURIBOR() {
		EURIBOR = 0.5f/(100*12);
		float variacionEURIBOR = (float) ((Math.random() * ((EURIBOR_MAX - EURIBOR_MIN) + 1)) + EURIBOR_MAX);
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
	
	private double recalcularHipoteca(Hipoteca hipoteca) {
		
		//(Se recalcula cada año = cada 12 cuotas)
		obtenerEURIBOR();
		// Setear el plazo con las cuotas restantes
		hipoteca.recalcularPlazoRestante(hipoteca);
		// Obtener el capital por amortizar
		double capitalPorAmortizar = Amortizacion.totalCapitalPorAmortizar;
		// Calcular la cuota mensual
		hipoteca.setPrestamo(capitalPorAmortizar);
		return calcularCuota(hipoteca);
		
	}

}