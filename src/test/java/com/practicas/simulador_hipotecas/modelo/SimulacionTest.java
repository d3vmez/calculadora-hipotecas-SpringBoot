package com.practicas.simulador_hipotecas.modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.*;

class SimulacionTest {

	Simulacion simulacion,simulacion2;
	List<Hipoteca> hipotecas;
	Hipoteca hipoteca, hipoteca2;
	
	@BeforeEach
	void setUp() throws Exception{
		hipotecas = new ArrayList<>();
		hipoteca = new Hipoteca(25000.0,200000.0,40000.0,80000.0,1000.0,1,2,1.0f,InteresTipo.fijo, null, new Date(),15000.0,2500.0,1000.0,true);
		hipoteca2 = new Hipoteca(25000.0,200000.0,40000.0,80000.0,1000.0,1,2,1.0f,InteresTipo.variable, null, new Date(),15000.0,2500.0,1000.0,true);
		hipotecas.add(hipoteca);
		hipotecas.add(hipoteca2);
		simulacion = new Simulacion(hipotecas, hipoteca,5);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetPorcentaje() {
		simulacion.setPorcentaje(5.0f);
		assertEquals(5.0f, simulacion.getPorcentaje());
	}

	@Test
	void testSetPorcentaje() {
		simulacion.setPorcentaje(5.0f);
		assertEquals(5.0f, simulacion.getPorcentaje());
	}

	@Test
	void testSimulacion() {
		//COMO SE TESTEA ESTO ??
		assertNotEquals(simulacion, null);
	}

	@Test
	void testSimulacionListOfHipotecaHipotecaInt() {
		assertAll("Constructor simulacion",
				()-> assertEquals(hipotecas, simulacion.getHipotecasVariables()),
				()-> assertEquals(hipoteca, simulacion.getHipotecaFija()),
				()-> assertEquals(5, simulacion.getnSimulaciones())
				);
	}

	@Test
	void testGetHipotecasVariables() {
		assertEquals(hipotecas, simulacion.getHipotecasVariables());
	}

	@Test
	void testSetHipotecasVariables() {
		simulacion.setHipotecasVariables(hipoteca);
		assertEquals(3, simulacion.getHipotecasVariables().size());
	}

	@Test
	void testGetHipotecaFija() {
		assertEquals(hipoteca, simulacion.getHipotecaFija());
	}

	@Test
	void testSetHipotecaFija() {
		simulacion.setHipotecaFija(hipoteca2);
		assertEquals(hipoteca2, simulacion.getHipotecaFija());
	}

	@Test
	void testGetnSimulaciones() {
		assertEquals(5, simulacion.getnSimulaciones());
	}

	@Test
	void testSetnSimulaciones() {
		simulacion.setnSimulaciones(10);
		assertEquals(10, simulacion.getnSimulaciones());
	}

	@Test
	void testToString() {
		
	}

}
