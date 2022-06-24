package com.practicas.simulador_hipotecas.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AmortizacionTest {

	Amortizacion amortizacion;
	
	@BeforeEach
	void setUp() throws Exception {
		amortizacion = new Amortizacion(5,1000.0,200.0,500.0,2000.0,10000.0);
	}

	@Test
	void testAmortizacion() {
		assertNotEquals(null, amortizacion);	
	}

	@Test
	void testAmortizacionIntDoubleDoubleDoubleDoubleDouble() {
		assertAll("Constructor amortizacion",
				() -> assertEquals(5, amortizacion.getNumeroCuota()),
				() -> assertEquals(1000.0, amortizacion.getCuota()),
				() -> assertEquals(200.0, amortizacion.getInteres()),
				() -> assertEquals(500.0, amortizacion.getCuotaAmortizacion()),
				() -> assertEquals(2000.0, amortizacion.getTotalAmortizado()),
				() -> assertEquals(10000.0, amortizacion.getCapitalPorAmortizar())
				);
	}

	@Test
	void testGetNumeroCuota() {
		assertEquals(5, amortizacion.getNumeroCuota());
	}

	@Test
	void testSetNumeroCuota() {
		amortizacion.setNumeroCuota(1);
		assertEquals(1, amortizacion.getNumeroCuota());
	}

	@Test
	void testGetCuota() {
		assertEquals(1000.0, amortizacion.getCuota());
	}

	@Test
	void testSetCuota() {
		amortizacion.setCuota(2000.0);
		assertEquals(2000.0, amortizacion.getCuota());
	}

	@Test
	void testGetInteres() {
		assertEquals(200.0, amortizacion.getInteres());
	}

	@Test
	void testSetInteres() {
		amortizacion.setInteres(500.0);
		assertEquals(500.0, amortizacion.getInteres());
	}

	@Test
	void testGetCuotaAmortizacion() {
		assertEquals(500.0, amortizacion.getCuotaAmortizacion());
	}

	@Test
	void testSetCuotaAmortizacion() {
		amortizacion.setCuotaAmortizacion(5000.0);
		assertEquals(5000.0, amortizacion.getCuotaAmortizacion());
	}

	@Test
	void testGetTotalAmortizado() {
		assertEquals(2000.0, amortizacion.getTotalAmortizado());
	}

	@Test
	void testSetTotalAmortizado() {
		amortizacion.setTotalAmortizado(1000.0);
		assertEquals(1000.0, amortizacion.getTotalAmortizado());
	}

	@Test
	void testGetCapitalPorAmortizar() {
		assertEquals(10000.0, amortizacion.getCapitalPorAmortizar());
	}

	@Test
	void testSetCapitalPorAmortizar() {
		amortizacion.setCapitalPorAmortizar(2000.0);
		assertEquals(2000.0, amortizacion.getCapitalPorAmortizar());
	}

	@Test
	void testToString() {

	}

}
