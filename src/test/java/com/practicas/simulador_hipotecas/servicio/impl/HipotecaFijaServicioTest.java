package com.practicas.simulador_hipotecas.servicio.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.practicas.simulador_hipotecas.configuracion.ConfiguracionTest;
import com.practicas.simulador_hipotecas.modelo.Amortizacion;
import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.modelo.InteresTipo;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfiguracionTest.class })
class HipotecaFijaServicioTest {

	@Autowired
	AmortizacionServicio amortizacionServicio;
	
	@Autowired
	private PonderacionInteresServicio ponderacionInteresServicio;
	
	HipotecaFijaServicio hipotecaFijaServicio;
	Hipoteca hipoteca;
	List<Amortizacion> amortizaciones = new ArrayList<>();
	Date fecha;
	float tasaInteres;
	
	@BeforeEach
	void setUp() throws Exception {
		hipotecaFijaServicio = new HipotecaFijaServicio();
		fecha = new Date();
		tasaInteres = 2.0f;
		hipoteca = new Hipoteca(25000.0,200000.0,40000.0,80000.0,1000.0,1,12,1.0f,InteresTipo.fijo, amortizaciones, fecha,15000.0,2500.0,1000.0,true);
	}

	@Test
	void testCalcularCuota() {
		hipotecaFijaServicio.calcularCuota(hipoteca);
	}

	@Test
	void testCalcularAmortizaciones() {
		this.amortizacionServicio = new AmortizacionServicio();
		hipotecaFijaServicio.calcularAmortizaciones(hipoteca);
	}

	@Test
	void testCalcularTasaInteresFloat() {	
		assertEquals(tasaInteres/1200, hipotecaFijaServicio.calcularTasaInteres(tasaInteres));
	}

	@Test
	void testCalcularTasaInteresHipoteca() {
		this.ponderacionInteresServicio = new PonderacionInteresServicio();
		hipotecaFijaServicio.calcularTasaInteres(hipoteca);
	}

}
