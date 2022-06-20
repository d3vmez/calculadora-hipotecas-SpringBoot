package com.practicas.simulador_hipotecas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.modelo.Simulacion;

@Service
public class SimulacionServicio {
	
	@Autowired
	private HipotecaFijaServicio hipotecaFijaServicio;
	
	@Autowired
	private HipotecaVariableServicio hipotecaVariableServicio;
	
	public void generarHipotecas(Hipoteca hipoteca, Simulacion simulacion) {
		
		// Generar hipoteca fija
		Hipoteca hipotecaFija;
		try {
			hipotecaFija = (Hipoteca) hipoteca.clone();
			hipotecaFijaServicio.calcularCuota(hipotecaFija);
			hipotecaFijaServicio.calcularAmortizaciones(hipotecaFija);
			
			simulacion.setHipotecaFija(hipotecaFija);
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		// Generar hipotecas variable
		for (int i = 0; i < 5; i++) {
			
			Hipoteca hipotecaVariable;
			try {
				hipotecaVariable = (Hipoteca) hipoteca.clone();
				hipotecaVariableServicio.calcularCuota(hipotecaVariable);
				hipotecaVariableServicio.calcularAmortizaciones(hipotecaVariable);
				
				simulacion.setHipotecasVariables(hipotecaVariable);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}

	}

}
