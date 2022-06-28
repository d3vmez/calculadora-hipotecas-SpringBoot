package com.practicas.simulador_hipotecas.servicio.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.practicas.simulador_hipotecas.modelo.Cliente;
import com.practicas.simulador_hipotecas.modelo.Rol;
import com.practicas.simulador_hipotecas.modelo.RolTipo;
import com.practicas.simulador_hipotecas.repositorio.RolRepositorio;

class RolServicioTest {
	
	RolRepositorio rolRepositorio = Mockito.mock(RolRepositorio.class);
	
	@InjectMocks
	RolServicio rolServicio;
	
	int id;
	Rol rol;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = 1;
		rol = new Rol(1,RolTipo.CLIENTE);
	}

	@Test
	void testObtener() {
		Optional<Rol> lista = rolServicio.obtener(id);
		assertEquals(lista, rolRepositorio.findById(id));
	}

	@Test
	void testObtenerTodos() {
		List<Rol> lista = rolServicio.obtenerTodos();
		assertEquals(lista,rolRepositorio.findAll());
	}

	@Test
	void testGuardar() {
		rolServicio.guardar(rol);
	}

	@Test
	void testEliminar() {
		rolServicio.eliminar(rol);
	}

}
