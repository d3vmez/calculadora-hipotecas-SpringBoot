package com.practicas.simulador_hipotecas.servicio.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.practicas.simulador_hipotecas.modelo.Cliente;
import com.practicas.simulador_hipotecas.modelo.Rol;
import com.practicas.simulador_hipotecas.modelo.RolTipo;
import com.practicas.simulador_hipotecas.repositorio.ClienteRepositorio;

class ClienteServicioTest {
	
	ClienteRepositorio clienteRepositorio = Mockito.mock(ClienteRepositorio.class);
	
	BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
	
	@InjectMocks
	ClienteServicio clienteServicio;
	
	Cliente cliente;
	Rol rol;
	Date fecha1, fecha2;
	int id;
	String email;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		rol = new Rol(1, RolTipo.CLIENTE);
		fecha1 = new Date(1);
		fecha2 = new Date(2);
		cliente = new Cliente(rol,1,"email","clave","nombre","apellido1","apellido2","direccion","provincia","localidad","telefono","dni",fecha1,fecha2,"imagen");		
		id = 1;
		email = "email";
	}

	@Test
	void testObtenerTodos() {
		List<Cliente> lista = clienteServicio.obtenerTodos();
		assertEquals(lista,clienteRepositorio.findAll());
	}

	@Test
	void testObtener() {
		Optional<Cliente> lista = clienteServicio.obtener(id);
		assertEquals(lista, clienteRepositorio.findById(id));
	}

	@Test
	void testGuardar1() {
		boolean esAlta = true;
		clienteServicio.guardar(cliente, esAlta);
	}
	
	@Test
	void testGuardar2() {
		boolean esAlta = false;
		clienteServicio.guardar(cliente, esAlta);
	}

	@Test
	void testEliminar() {
		clienteServicio.eliminar(cliente);
	}

	@Test
	void testObtenerPorEmail() {
		Optional<Cliente> lista = clienteServicio.obtenerPorEmail(email);
		assertEquals(lista, clienteRepositorio.findByEmail(email));
	}

	@Test
	void testEliminarLogicamente() {
		clienteServicio.eliminarLogicamente(id);
	}

	@Test
	void testExisteEmail() {
		boolean existe = clienteServicio.existeEmail(email);
		assertEquals(false, existe);
	}

	@Test
	void testLoadUserByUsername() {
		//assertEquals("asd", clienteServicio.loadUserByUsername(email));
	}

}
