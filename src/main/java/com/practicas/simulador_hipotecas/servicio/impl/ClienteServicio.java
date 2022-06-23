package com.practicas.simulador_hipotecas.servicio.impl;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Cliente;
import com.practicas.simulador_hipotecas.repositorio.ClienteRepositorio;

@Service
public class ClienteServicio implements UserDetailsService{
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	public List<Cliente> obtenerTodos() {
		
		return (List<Cliente>) clienteRepositorio.findAll();
		
	}

	public Optional<Cliente> obtener(Integer id) {
		
		return clienteRepositorio.findById(id);
		
	}


	public void guardar(Cliente cliente, boolean esAlta) {
		
		if(esAlta) {
			String clave = cliente.getClave();
			String claveEncode = passwordEncoder.encode(clave);
			cliente.setClave(claveEncode);
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
	
	/**
	 *
	 * Metodo para cargar un usuario en la sesion ya signar rol despues de comprobar
	 * sus credenciales
	 *
	 * @return {@link User}
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Obtener al usuario de la BD por email
		Optional<Cliente> opCliente = obtenerPorEmail(email);

		// Crear usuario para la sesion de la aplicacion
		User springUser = null;

		if (opCliente.isPresent()) {

			Cliente cliente = opCliente.get();

//			String clave = empleado.getClave();
//			String claveEncode = passwordEncoder.encode(clave);
//			empleado.setClave(claveEncode);
//			
			// Dar rol al usuario de la sesion
			Set<GrantedAuthority> rol = new HashSet<>();
			rol.add(new SimpleGrantedAuthority(cliente.getRol().getRol().toString()));

			springUser = new User(email, cliente.getClave(), rol);

		} else {

			throw new UsernameNotFoundException("Usuario con email: " + email + " no encontrado");

		}

		return springUser;
	}
	

}
