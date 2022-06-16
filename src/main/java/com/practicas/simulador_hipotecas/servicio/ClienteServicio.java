package com.practicas.simulador_hipotecas.servicio;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Cliente;
import com.practicas.simulador_hipotecas.repositorio.ClienteRepositorio;

@Service
public class ClienteServicio {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	

	public List<Cliente> obtenerTodos() {
		
		return (List<Cliente>) clienteRepositorio.findAll();
		
	}

	public Optional<Cliente> obtener(Integer id) {
		
		return clienteRepositorio.findById(id);
		
	}


	public void guardar(Cliente cliente, boolean esAlta) {
		
		if(esAlta) {
			String clave = cliente.getClave();
			//String claveEncode = passwordEncoder.encode(clave);
			//cliente.setClave(claveEncode);
		}

		cliente = clienteRepositorio.save(cliente);
		
	}

	public void eliminar(Cliente cliente) {
		
		clienteRepositorio.delete(cliente);
		
	}

	public Optional<Cliente> obtenerPorEmail(String email) {

		return clienteRepositorio.findByEmail(email);
		
	}

	public void eliminarLogicamente(Integer id) {

			//Establecer una fecha de baja
			Optional<Cliente> clienteOp  = clienteRepositorio.findById(id);
			Cliente cliente = new Cliente();
			
			if(clienteOp.isPresent()) {
				
				cliente = clienteOp.get();
				java.util.Date fecha = new java.util.Date();
				java.sql.Date fechaSQl = new Date(fecha.getTime());
				cliente.setFechaBaja(fechaSQl);
				clienteRepositorio.save(cliente);
				
			}
				
	}
	
	public boolean existeEmail(String email) {
        Optional<Cliente> clienteOp = clienteRepositorio.findByEmail(email);
        if(clienteOp.isEmpty()) {
            return false;
        }
        return true;
    }
	

}
