package com.practicas.simulador_hipotecas.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RolTest {

	Rol rol;
	
	@BeforeEach
	void setUp() throws Exception {
		rol = new Rol(1,RolTipo.CLIENTE);
	}

	@Test
	void testRol() {
		Rol rol2 = new Rol();
	}

	@Test
	void testRolIntRolTipo() {
		assertAll("",
				()-> assertEquals(1, rol.getId()),
				()-> assertEquals(RolTipo.CLIENTE, rol.getRol())
				);
	}

	@Test
	void testGetId() {
		assertEquals(1, rol.getId());
	}

	@Test
	void testSetId() {
		rol.setId(2);
		assertEquals(2, rol.getId());
	}

	@Test
	void testGetRol() {
		assertEquals(RolTipo.CLIENTE, rol.getRol());
	}

	@Test
	void testSetRol() {
		rol.setRol(RolTipo.ADMIN);
		assertEquals(RolTipo.ADMIN, rol.getRol());
	}

	@Test
	void testToString() {

	}

}
