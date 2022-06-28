package com.practicas.simulador_hipotecas.servicio.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.modelo.InteresTipo;
import com.practicas.simulador_hipotecas.modelo.Simulacion;

class SimulacionServicioTest {

	SimulacionServicio simulacionServicio;
	Hipoteca hipoteca;
	List<Amortizacion> amortizaciones = new ArrayList<>();
	Date fecha;
	private List<Hipoteca> hipotecasVariables = new ArrayList<>();
	Simulacion simulacion;
	
	@BeforeEach
	void setUp() throws Exception {
		simulacionServicio = new SimulacionServicio();
		fecha = new Date();
		hipoteca = new Hipoteca(25000.0,200000.0,40000.0,80000.0,1000.0,1,12,1.0f,InteresTipo.fijo, amortizaciones, fecha,15000.0,2500.0,1000.0,true);
		hipotecasVariables.add(hipoteca);
		simulacion = new Simulacion(hipotecasVariables, hipoteca,1);
	}

	@Test
	void testGenerarHipotecas() {
		simulacionServicio.generarHipotecas(hipoteca, simulacion);
	}

	@Test
	void testCalcularProbabilidad() {
		simulacionServicio.calcularProbabilidad(simulacion);
	}

}
