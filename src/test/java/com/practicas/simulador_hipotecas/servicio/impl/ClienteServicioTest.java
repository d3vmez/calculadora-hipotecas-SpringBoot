package com.practicas.simulador_hipotecas.servicio.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.practicas.simulador_hipotecas.modelo.Cliente;
import com.practicas.simulador_hipotecas.repositorio.ClienteRepositorio;

class ClienteServicioTest {

	ClienteServicio clienteServicio;
	
	@Autowired
	ClienteRepositorio clienteRepositorio;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	int id;
	
	@BeforeEach
	void setUp() throws Exception {
		clienteServicio = new ClienteServicio();
		//ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
	}

	@Test
	void testObtenerTodos() {
		List<Cliente> lista = clienteRepositorio.findAll();
		assertEquals(lista, clienteServicio.obtenerTodos());
	}

	@Test
	void testObtener() {
		id = 1;
		Optional<Cliente> lista = clienteServicio.obtener(id);
		assertEquals(lista, clienteRepositorio.findById(id));
	}

	@Test
	void testGuardar() {
		fail("Not yet implemented");
	}

	@Test
	void testEliminar() {
		fail("Not yet implemented");
	}

	@Test
	void testObtenerPorEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testEliminarLogicamente() {
		fail("Not yet implemented");
	}

	@Test
	void testExisteEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testLoadUserByUsername() {
		fail("Not yet implemented");
	}

}
