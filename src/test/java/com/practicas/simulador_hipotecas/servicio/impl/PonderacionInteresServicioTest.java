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

class PonderacionInteresServicioTest {

	PonderacionInteresServicio ponderacionInteresServicio;
	Hipoteca hipoteca;
	List<Amortizacion> amortizaciones = new ArrayList<>();
	Date fecha;
	float interes;

	@BeforeEach
	void setUp() throws Exception {
		ponderacionInteresServicio = new PonderacionInteresServicio();
		ponderacionInteresServicio.acumuladorPesos = 0;
		fecha = new Date();
		interes = 2.0f;
		hipoteca = new Hipoteca(25000.0,200000.0,40000.0,80000.0,1000.0,1,12,1.0f,InteresTipo.fijo, amortizaciones, fecha,15000.0,2500.0,1000.0,true);
		
	}

	@Test
	void testCalcularPeso() {
		float resultado = ponderacionInteresServicio.calcularPeso();
		assertEquals(0.02f, resultado);
	}

	@Test
	void testCalcularPesoNonima1() {
		double nomina = 1000.0;
		float pesoMaximo = 40f;
		ponderacionInteresServicio.calcularPesoNonima(nomina, pesoMaximo);
		assertEquals(40.0f, ponderacionInteresServicio.acumuladorPesos);
	}

	@Test
	void testCalcularPesoNonima2() {
		double nomina = 1500.0;
		float pesoMaximo = 40f;
		ponderacionInteresServicio.calcularPesoNonima(nomina, pesoMaximo);
		assertEquals(20.0f, ponderacionInteresServicio.acumuladorPesos);
	}

	@Test
	void testCalcularPesoNonima3() {
		double nomina = 2500.0;
		float pesoMaximo = 40f;
		ponderacionInteresServicio.calcularPesoNonima(nomina, pesoMaximo);
		assertEquals(5.0f, ponderacionInteresServicio.acumuladorPesos);
	}

	@Test
	void testCalcularPesoNonima4() {
		double nomina = 2500.0;
		float pesoMaximo = 40f;
		ponderacionInteresServicio.calcularPesoNonima(nomina, pesoMaximo);
		assertEquals(5.0f, ponderacionInteresServicio.acumuladorPesos);
	}

	@Test
	void testCalcularPesoCuota1() {
		int nCuotas = 24;
		float pesoMaximo = 10.0f;
		ponderacionInteresServicio.calcularPesoCuota(nCuotas, pesoMaximo);
		assertEquals(10.0f, ponderacionInteresServicio.acumuladorPesos);
	}
	
	@Test
	void testCalcularPesoCuota2() {
		int nCuotas = 30;
		float pesoMaximo = 20.0f;
		ponderacionInteresServicio.calcularPesoCuota(nCuotas, pesoMaximo);
		assertEquals(20.0f, ponderacionInteresServicio.acumuladorPesos);
	}
	
	@Test
	void testCalcularPesoCuota3() {
		int nCuotas = 48;
		float pesoMaximo = 40.0f;
		ponderacionInteresServicio.calcularPesoCuota(nCuotas, pesoMaximo);
		assertEquals(40.0f, ponderacionInteresServicio.acumuladorPesos);
	}

	@Test
	void testCalcularPesoAhorro1() {
		double ahorros = 5000.0;
		float pesoMaximo = 10.0f;
		ponderacionInteresServicio.calcularPesoAhorro(ahorros, pesoMaximo);
		assertEquals(10.0f, ponderacionInteresServicio.acumuladorPesos);
	}
	
	@Test
	void testCalcularPesoAhorro2() {
		double ahorros = 12000.0;
		float pesoMaximo = 10.0f;
		ponderacionInteresServicio.calcularPesoAhorro(ahorros, pesoMaximo);
		assertEquals(0.0f, ponderacionInteresServicio.acumuladorPesos);
	}

	@Test
	void testCalcularPesoOtrosPrestamos1() {
		double otrosPrestamos = 5000.0;
		float pesoMaximo = 10.0f;
		ponderacionInteresServicio.calcularPesoOtrosPrestamos(otrosPrestamos, pesoMaximo);
		assertEquals(0.0f,  ponderacionInteresServicio.acumuladorPesos);
	}
	
	@Test
	void testCalcularPesoOtrosPrestamos2() {
		double otrosPrestamos = 12000.0;
		float pesoMaximo = 15.0f;
		ponderacionInteresServicio.calcularPesoOtrosPrestamos(otrosPrestamos, pesoMaximo);
		assertEquals(10.0f, ponderacionInteresServicio.acumuladorPesos);
	}
	
	@Test
	void testCalcularPesoOtrosPrestamos3() {
		double otrosPrestamos = 25000.0;
		float pesoMaximo = 25.0f;
		ponderacionInteresServicio.calcularPesoOtrosPrestamos(otrosPrestamos, pesoMaximo);
		assertEquals(25.0f,ponderacionInteresServicio.acumuladorPesos);
	}

	@Test
	void testCalcularPesoPrimeraVivienda1() {
		boolean esPrimeraVivienda = true;
		float pesoMaximo = 10.0f;
		ponderacionInteresServicio.calcularPesoPrimeraVivienda(esPrimeraVivienda, pesoMaximo);
		assertEquals(10.0f, ponderacionInteresServicio.acumuladorPesos);
	}
	
	@Test
	void testCalcularPesoPrimeraVivienda2() {
		boolean esPrimeraVivienda = false;
		float pesoMaximo = 10.0f;
		ponderacionInteresServicio.calcularPesoPrimeraVivienda(esPrimeraVivienda, pesoMaximo);
		assertEquals(0.0f, ponderacionInteresServicio.acumuladorPesos);
	}

	@Test
	void testCalcularInteresTotal() {
		assertEquals(2.4f, ponderacionInteresServicio.calcularInteresTotal(hipoteca));
	}
}
