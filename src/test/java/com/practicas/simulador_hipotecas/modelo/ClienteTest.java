package com.practicas.simulador_hipotecas.modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {

	Cliente cliente;
	Rol rol;
	Date fecha1, fecha2;
	
	@BeforeEach
	void setUp() throws Exception {
		fecha1 = new Date(1);
		fecha2 = new Date(2);
		rol = new Rol(1, RolTipo.CLIENTE);
		cliente = new Cliente(rol,1,"email","clave","nombre","apellido1","apellido2","direccion","provincia","localidad","telefono","dni",fecha1,fecha2,"imagen");		
	}


	@Test
	void testClienteIntStringStringStringStringStringStringStringStringStringStringDateDateString() {
		assertAll("Constructor cliente",
				()-> assertEquals(rol, cliente.getRol()),
				()-> assertEquals(1, cliente.getId()),
				()-> assertEquals("email", cliente.getEmail()),
				()-> assertEquals("clave", cliente.getClave()),
				()-> assertEquals("nombre", cliente.getNombre()),
				()-> assertEquals("apellido1", cliente.getApellido1()),
				()-> assertEquals("apellido2", cliente.getApellido2()),
				()-> assertEquals("direccion", cliente.getDireccion()),
				()-> assertEquals("provincia", cliente.getProvincia()),
				()-> assertEquals("localidad", cliente.getLocalidad()),
				()-> assertEquals("telefono", cliente.getTelefono()),
				()-> assertEquals("dni", cliente.getDni()),
				()-> assertEquals(fecha1, cliente.getFechaAlta()),
				()-> assertEquals(fecha2, cliente.getFechaBaja()),
				()-> assertEquals("imagen", cliente.getImagen())
				);
	}

	@Test
	void testCliente() {
		Cliente cliente2 = new Cliente();
	}
	
//	@Test
//	void testGetRol() {
//		String r = rol.getRol().name();
//		String r2 = cliente.getRol().getRol().name();
//		
//		assertEquals(r, r2);
//	}

	@Test
	void testSetRol() {
		Rol rol2 = new Rol(2, RolTipo.ADMIN);
		cliente.setRol(rol2);
		assertEquals(rol2, cliente.getRol());
	}

	@Test
	void testGetId() {
		assertEquals(1, cliente.getId());;
	}

	@Test
	void testSetId() {
		cliente.setId(2);
		assertEquals(2, cliente.getId());
	}

	@Test
	void testGetEmail() {
		assertEquals("email", cliente.getEmail());
	}

	@Test
	void testSetEmail() {
		cliente.setEmail("e");
		assertEquals("e", cliente.getEmail());
	}

	@Test
	void testGetClave() {
		assertEquals("clave", cliente.getClave());
	}

	@Test
	void testSetClave() {
		cliente.setClave("c");
		assertEquals("c", cliente.getClave());
	}

	@Test
	void testGetNombre() {
		assertEquals("nombre", cliente.getNombre());
	}

	@Test
	void testSetNombre() {
		cliente.setNombre("n");
		assertEquals("n", cliente.getNombre());
	}

	@Test
	void testGetApellido1() {
		assertEquals("apellido1", cliente.getApellido1());
	}

	@Test
	void testSetApellido1() {
		cliente.setApellido1("ape1");
		assertEquals("ape1", cliente.getApellido1());
	}

	@Test
	void testGetApellido2() {
		assertEquals("apellido2", cliente.getApellido2());
	}

	@Test
	void testSetApellido2() {
		cliente.setApellido2("ape2");
		assertEquals("ape2", cliente.getApellido2());
	}

	@Test
	void testGetDireccion() {
		assertEquals("direccion", cliente.getDireccion());
	}

	@Test
	void testSetDireccion() {
		cliente.setDireccion("d");
		assertEquals("d", cliente.getDireccion());
	}

	@Test
	void testGetProvincia() {
		assertEquals("provincia", cliente.getProvincia());
	}

	@Test
	void testSetProvincia() {
		cliente.setProvincia("p");
		assertEquals("p", cliente.getProvincia());
	}

	@Test
	void testGetLocalidad() {
		assertEquals("localidad", cliente.getLocalidad());
	}

	@Test
	void testSetLocalidad() {
		cliente.setLocalidad("l");
		assertEquals("l", cliente.getLocalidad());
	}

	@Test
	void testGetTelefono() {
		assertEquals("telefono", cliente.getTelefono());
	}

	@Test
	void testSetTelefono() {
		cliente.setTelefono("t");
		assertEquals("t", cliente.getTelefono());
	}

	@Test
	void testGetDni() {
		assertEquals("dni", cliente.getDni());
	}

	@Test
	void testSetDni() {
		cliente.setDni("d");
		assertEquals("d", cliente.getDni());
	}

	@Test
	void testGetFechaAlta() {
		assertEquals(fecha1, cliente.getFechaAlta());
	}

	@Test
	void testSetFechaAlta() {
		Date f = new  Date(10);
		cliente.setFechaAlta(f);
		assertEquals(f, cliente.getFechaAlta());
	}

	@Test
	void testGetFechaBaja() {
		assertEquals(fecha2, cliente.getFechaBaja());
	}

	@Test
	void testSetFechaBaja() {
		Date f = new  Date(20);
		cliente.setFechaBaja(f);
		assertEquals(f, cliente.getFechaBaja());
	}

	@Test
	void testGetImagen() {
		assertEquals("imagen", cliente.getImagen());
	}

	@Test
	void testSetImagen() {
		cliente.setImagen("i");
		assertEquals("i", cliente.getImagen());
	}

	@Test
	void testToString() {
		
	}

}
