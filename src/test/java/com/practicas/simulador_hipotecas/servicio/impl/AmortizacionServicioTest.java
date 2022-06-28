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

class AmortizacionServicioTest {

	AmortizacionServicio amortizacionServicio;
	Hipoteca hipoteca;
	int nCuotas;
	float tasaInteres;
	Date fecha;
	Amortizacion amortizacion;
	List<Amortizacion> amortizaciones = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		amortizacionServicio = new AmortizacionServicio();
		fecha = new Date();
		hipoteca = new Hipoteca(25000.0, 200000.0, 40000.0, 80000.0, 1000.0, 1, 12, 1.0f, InteresTipo.fijo,
				amortizaciones, fecha, 15000.0, 2500.0, 1000.0, true);
		amortizacion = new Amortizacion(1,1000.0,0.0,1000.0,1000.0,79000.0);
	}

	@Test
	void testCrearAmortizacion() {
		nCuotas = 1;
		Amortizacion a = amortizacionServicio.crearAmortizacion(hipoteca, nCuotas, tasaInteres);
		assertEquals(amortizacion.getCapitalPorAmortizar(), a.getCapitalPorAmortizar());
		assertEquals(amortizacion.getCuota(), a.getCuota());
		assertEquals(amortizacion.getCuotaAmortizacion(), a.getCuotaAmortizacion());
		assertEquals(amortizacion.getInteres(), a.getInteres());
		assertEquals(amortizacion.getNumeroCuota(), a.getNumeroCuota());
		assertEquals(amortizacion.getTotalAmortizado(), a.getTotalAmortizado());
	}

	@Test
	void testCrearAmortizacion2() {
		nCuotas = 3;
		Amortizacion a = amortizacionServicio.crearAmortizacion(hipoteca, nCuotas, tasaInteres);
		amortizacion.setNumeroCuota(nCuotas);
		amortizacion.setCapitalPorAmortizar(-1000.0);
		assertEquals(amortizacion.getCapitalPorAmortizar(), a.getCapitalPorAmortizar());
		assertEquals(amortizacion.getCuota(), a.getCuota());
		assertEquals(amortizacion.getCuotaAmortizacion(), a.getCuotaAmortizacion());
		assertEquals(amortizacion.getInteres(), a.getInteres());
		assertEquals(amortizacion.getNumeroCuota(), a.getNumeroCuota());
		assertEquals(amortizacion.getTotalAmortizado(), a.getTotalAmortizado());
	}

}
