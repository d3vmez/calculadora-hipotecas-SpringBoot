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

class HipotecaVariableServicioTest {

	HipotecaVariableServicio hipotecaVariableServicio;
	Hipoteca hipoteca;
	List<Amortizacion> amortizaciones = new ArrayList<>();
	Date fecha;
	float interes;
	
	@BeforeEach
	void setUp() throws Exception {
		hipotecaVariableServicio = new HipotecaVariableServicio();
		fecha = new Date();
		interes = 2.0f;
		hipoteca = new Hipoteca(25000.0,200000.0,40000.0,80000.0,1000.0,1,12,1.0f,InteresTipo.fijo, amortizaciones, fecha,15000.0,2500.0,1000.0,true);
	}

	@Test
	void testCalcularCuota() {
		hipotecaVariableServicio.calcularCuota(hipoteca);
	}

	@Test
	void testCalcularAmortizaciones() {
		hipotecaVariableServicio.calcularAmortizaciones(hipoteca);
	}

	@Test
	void testCalcularTasaInteresFloat() {
		assertEquals(0.0023318841122090816, hipotecaVariableServicio.calcularTasaInteres(interes));
	}

	@Test
	void testCalcularTasaInteresHipoteca() {
		hipotecaVariableServicio.calcularTasaInteres(hipoteca);
	}
	
	@Test
	void testObtenerEuribor() {
		hipotecaVariableServicio.obtenerEURIBOR();;
	}
	
	@Test
	void testInicializarPlazoRestanteHipoteca() {
		hipotecaVariableServicio.inicializarPlazoRestante(hipoteca);
	}
	
	@Test
	void recalcularHipotecaHipoteca() {
		hipotecaVariableServicio.recalcularHipoteca(hipoteca);
	}

}
